package com.maksimov.service;

import com.maksimov.entity.User;
import com.maksimov.exceptions.NoUniqueException;

import java.util.List;

/**
 * Created on 17.04.2016.
 */
public interface UserService {

    User registerNewUser(String username, String password) throws NoUniqueException;

    User changeUser(User user);

    void blockUser(Long id);

    User getUser(Long id);

    List<User> getAllUsers();

}
