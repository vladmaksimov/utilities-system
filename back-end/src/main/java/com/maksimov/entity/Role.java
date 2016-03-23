package com.maksimov.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created on 20.03.2016.
 */
@Entity
public class Role {

    @Id
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
