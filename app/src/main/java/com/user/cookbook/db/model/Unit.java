package com.user.cookbook.db.model;

import java.io.Serializable;

public class Unit implements Serializable {
    private long id;
    private String name;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Unit(long id, String name) {
        this.id = id;

        this.name = name;
    }

    public Unit(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return id +". "+ name;
    }
}
