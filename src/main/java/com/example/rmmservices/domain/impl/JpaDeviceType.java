package com.example.rmmservices.domain.impl;

import com.example.rmmservices.domain.DeviceType;

public final class JpaDeviceType implements DeviceType {

    private final Long id;

    private final String name;

    JpaDeviceType(final Long id, final String name) {
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
