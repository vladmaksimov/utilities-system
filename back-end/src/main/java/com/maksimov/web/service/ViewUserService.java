package com.maksimov.web.service;

import com.maksimov.service.UserService;
import com.maksimov.web.entity.UserView;
import com.maksimov.web.transformer.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created on 11.04.2016.
 */
@Component
public class ViewUserService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserTransformer userTransformer;

    public UserView getUserByUsername(String username) {
        return userTransformer.transform(userService.loadUserByUsername(username));
    }

}
