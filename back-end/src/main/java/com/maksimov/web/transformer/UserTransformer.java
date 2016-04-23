package com.maksimov.web.transformer;

import com.maksimov.entity.Role;
import com.maksimov.entity.User;
import com.maksimov.web.entity.UserView;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * Created on 11.04.2016.
 */
@Component
public class UserTransformer {

    public UserView transform(User user) {
        UserView u = new UserView();
        u.setId(user.getId());
        u.setUsername(user.getUsername());
        u.setFirstName(user.getFirstName());
        u.setLastName(user.getLastName());

        u.setRoles(user.getRoles().stream().map(Role::getName).collect(Collectors.toSet()));

        return u;
    }

}
