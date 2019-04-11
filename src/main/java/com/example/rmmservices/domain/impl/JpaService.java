package com.example.rmmservices.domain.impl;

import com.example.rmmservices.domain.Service;

public final class JpaService implements Service {

    private final Long id;

    private final String name;

    JpaService(final Long id, final String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
