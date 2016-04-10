package com.maksimov.controller;

import com.maksimov.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created on 20.03.2016.
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/get")
    @Secured("IS_AUTHENTICATED_FULLY")
    public UserDetails getUser() {
        return userService.getCurrentUser();
    }

    @RequestMapping("/registration")
    public void registerNewUser(@RequestBody Map<String, String> data) {
        String username = data.get("username");
        String password = data.get("password");

    }

}
