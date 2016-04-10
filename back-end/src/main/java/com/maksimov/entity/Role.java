package com.maksimov.entity;

import javax.persistence.Entity;

/**
 * Created on 20.03.2016.
 */
@Entity
public class Role extends Base {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
