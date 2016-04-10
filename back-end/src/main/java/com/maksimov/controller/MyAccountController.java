package com.maksimov.controller;

import com.maksimov.entity.User;
import com.maksimov.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

/**
 * Created on 02.04.2016.
 */
@RestController
@RequestMapping("api/me")
public class MyAccountController {

    @Autowired
    private UserService userService;

    @RequestMapping("")
    @Secured("IS_AUTHENTICATED_FULLY")
    public User me(Principal principal) {
        return (User) userService.loadUserByUsername(principal.getName());
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public void logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
    }

    @RequestMapping("/csrf")
    public CsrfToken csrf(CsrfToken token) {
        return token;
    }

}
