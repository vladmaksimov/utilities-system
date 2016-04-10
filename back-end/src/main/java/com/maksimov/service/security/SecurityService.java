package com.maksimov.service.security;

import com.maksimov.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * Created on 10.04.2016.
 */
@Service
public class SecurityService {

    private Authentication getAuthentication() {
        final SecurityContext context = SecurityContextHolder.getContext();

        if (context != null) {
            return context.getAuthentication();
        }

        return null;
    }

    public User getCurrentUser() {
        Authentication authentication = getAuthentication();

        if (authentication != null) {
            Object principal = authentication.getPrincipal();

            if (principal instanceof User) {
                return (User) principal;
            }
        }

        return null;
    }
}
