package com.maksimov.controller;

import com.maksimov.persistence.UserPersistence;
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
    private UserPersistence userPersistence;

    @RequestMapping(value = "me")
    @ResponseBody
    public void me(){
        System.out.printf("test");
    }

}
