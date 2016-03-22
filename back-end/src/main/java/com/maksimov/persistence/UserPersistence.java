package com.maksimov.persistence;

import com.maksimov.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created on 20.03.2016.
 */
@Repository
public interface UserPersistence extends JpaRepository<User, Long> {

}
