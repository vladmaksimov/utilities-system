package com.maksimov.service;

import com.maksimov.persistence.UserPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created on 20.03.2016.
 */
@Service
public class UserService implements UserDetailsService {

    @Autowired
    @Qualifier(value = "userPersistence")
    private UserPersistence userPersistence;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userPersistence.getByUsername(username);
    }

    public void setUserPersistence(UserPersistence userPersistence) {
        this.userPersistence = userPersistence;
    }
}
