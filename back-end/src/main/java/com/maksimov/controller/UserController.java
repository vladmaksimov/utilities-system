package com.maksimov.controller;

import com.maksimov.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created on 20.03.2016.
 */
@Controller
@RequestMapping(value = "api/")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "me")
    @ResponseBody
//    @Secured("IS_AUTHENTICATED_FULLY")
    public void me() {
        userService.loadUserByUsername("admin");
        System.out.printf("test");
    }

}
