package com.maksimov.persistence;

import com.maksimov.entity.Role;
import com.maksimov.entity.constants.RoleConstants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created on 10.04.2016.
 */
public interface RolePersistence extends JpaRepository<Role, Long> {

    @Query("FROM Role WHERE name = '" + RoleConstants.ROLE_USER + "'")
    Role getUserRole();

    @Query("FROM Role WHERE name = '" + RoleConstants.ROLE_ADMIN + "'")
    Role getAdminRole();
}
