package com.example.rmmservices.controller;

import com.example.rmmservices.repository.ServiceEntries;
import com.example.rmmservices.domain.Service;
import com.example.rmmservices.domain.Services;
import com.example.rmmservices.domain.impl.JpaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ServiceController {

    private final ServiceEntries serviceEntries;

    @Autowired
    public ServiceController(final ServiceEntries serviceEntries) {
        this.serviceEntries = serviceEntries;
    }

    @GetMapping("/services")
    public List<Service> get() {
        final Services services = new JpaServices(serviceEntries);
        return services.all();
    }
}
