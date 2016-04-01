package com.maksimov.controller;

import com.maksimov.entity.User;
import com.maksimov.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 20.03.2016.
 */
@RestController
@RequestMapping(value = "api/")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "me")
    @ResponseBody
//    @Secured("IS_AUTHENTICATED_FULLY")
    public User me() {
        return (User) userService.loadUserByUsername("admin");
    }

}
