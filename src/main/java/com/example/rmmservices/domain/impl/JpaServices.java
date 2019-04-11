package com.example.rmmservices.domain.impl;

import com.example.rmmservices.repository.ServiceEntries;
import com.example.rmmservices.domain.Service;
import com.example.rmmservices.domain.Services;

import java.util.List;
import java.util.stream.Collectors;

public final class JpaServices implements Services {

    private final ServiceEntries serviceEntries;

    public JpaServices(final ServiceEntries serviceEntries) {
        this.serviceEntries = serviceEntries;
    }

    @Override
    public List<Service> all() {
        return serviceEntries.findAll().stream()
                .map(e -> new JpaService(e.getId(), e.getName()))
                .collect(Collectors.toList());
    }
}
