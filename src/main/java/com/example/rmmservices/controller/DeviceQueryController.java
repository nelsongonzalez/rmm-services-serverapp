package com.example.rmmservices.controller;

import com.example.rmmservices.controller.bean.DeviceQuery;
import com.example.rmmservices.entity.Device;
import com.example.rmmservices.service.DeviceQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class DeviceQueryController {

    private final DeviceQueryService deviceQueryService;

    @Autowired
    public DeviceQueryController(DeviceQueryService deviceQueryService) {
        this.deviceQueryService = deviceQueryService;
    }

    @GetMapping("/customer/{customerId}/devices")
    public List<DeviceQuery> get(@PathVariable Long customerId) {
        List<Device> devices = deviceQueryService.get(customerId);
        return devices.stream().map((d) -> new DeviceQuery()).collect(Collectors.toList());
    }

}
