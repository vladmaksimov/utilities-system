package com.maksimov.web.service;

import com.maksimov.service.UserServiceImpl;
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
    private UserServiceImpl userServiceImpl;

    @Autowired
    private UserTransformer userTransformer;

    public UserView getUserByUsername(String username) {
        return userTransformer.transform(userServiceImpl.loadUserByUsername(username));
    }

}
