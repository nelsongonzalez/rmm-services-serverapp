package com.example.rmmservices.controller;

import com.example.rmmservices.repository.DeviceTypeEntries;
import com.example.rmmservices.domain.DeviceType;
import com.example.rmmservices.domain.DeviceTypes;
import com.example.rmmservices.domain.impl.JpaDeviceTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DeviceTypeController {

    private final DeviceTypeEntries deviceTypeEntries;

    @Autowired
    public DeviceTypeController(final DeviceTypeEntries deviceTypeEntries) {
        this.deviceTypeEntries = deviceTypeEntries;
    }

    @GetMapping("/deviceTypes")
    public List<DeviceType> get() {
        final DeviceTypes deviceTypes = new JpaDeviceTypes(deviceTypeEntries);
        return deviceTypes.all();
    }
}
