package com.maksimov.service;

import com.maksimov.entity.Role;
import com.maksimov.persistence.RolePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created on 10.04.2016.
 */

@Service
public class RoleService {

    @Autowired
    private RolePersistence rolePersistence;

    public Role getUserRole() {
        return rolePersistence.getUserRole();
    }
}
