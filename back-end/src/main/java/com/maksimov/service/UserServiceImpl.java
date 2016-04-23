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
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created on 20.03.2016.
 */
@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserPersistence userPersistence;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private RoleServiceImpl roleServiceImpl;

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
    public User registerNewUser(String username, String password) throws NoUniqueException {
        final User existedUser = userPersistence.getByUsername(username);

        if (existedUser != null) {
            logger.error("User {} already exist", username);
            throw new NoUniqueException();
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRoles(Sets.newHashSet(roleServiceImpl.getUserRole()));

        userPersistence.save(user);

        logger.info("User {} created", username);

        return user;
    }


    @Override
    public User changeUser(User user) {
        return userPersistence.save(user);
    }

    @Override
    @Transactional
    public void blockUser(Long id) {
        User user = userPersistence.getOne(id);
        if (!user.isEnabled()) {
            //TODO create disabled exception
            logger.info("User {} already disabled", user.getUsername());
        }
        user.setEnabled(false);
    }

    @Override
    public User getUser(Long id) {
        return userPersistence.getOne(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userPersistence.findAll();
    }
}
