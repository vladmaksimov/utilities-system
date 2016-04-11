package com.maksimov.service;

import com.google.common.collect.Sets;
import com.maksimov.entity.User;
import com.maksimov.exceptions.NoUniqueException;
import com.maksimov.persistence.UserPersistence;
import com.maksimov.service.security.SecurityService;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created on 20.03.2016.
 */
@Service
public class UserService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserPersistence userPersistence;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private RoleService roleService;

    @Override
    @Transactional
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userPersistence.getByUsername(username);

        Hibernate.initialize(user);
        Hibernate.initialize(user.getAuthorities());

        return user;
    }

    public User getCurrentUser() {
        return securityService.getCurrentUser();
    }

    @Transactional(readOnly = false)
    public void registerNewUser(String username, String password) throws NoUniqueException {
        final User existedUser = userPersistence.getByUsername(username);

        if (existedUser != null) {
            logger.error("User {} already exist", username);
            throw new NoUniqueException();
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRoles(Sets.newHashSet(roleService.getUserRole()));

        userPersistence.save(user);

        logger.info("User {} created", username);

    }

}
