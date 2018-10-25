package com.example.rmmservices.controller;

import com.example.rmmservices.entity.DeviceType;
import com.example.rmmservices.repository.DeviceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DeviceTypeController {

    private final DeviceTypeRepository deviceTypeRepository;

    @Autowired
    public DeviceTypeController(DeviceTypeRepository deviceTypeRepository) {
        this.deviceTypeRepository = deviceTypeRepository;
    }

    @GetMapping("/deviceTypes")
    public List<DeviceType> get() {
        return deviceTypeRepository.findAll();
    }
}
