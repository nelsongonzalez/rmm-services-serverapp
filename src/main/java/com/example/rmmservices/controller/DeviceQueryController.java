package com.example.rmmservices.controller;

import com.example.rmmservices.domain.Device;
import com.example.rmmservices.domain.Device.Data;
import com.example.rmmservices.domain.Devices;
import com.example.rmmservices.domain.impl.JpaDevices;
import com.example.rmmservices.repository.CustomerEntries;
import com.example.rmmservices.repository.DeviceEntries;
import com.example.rmmservices.repository.DeviceTypeEntries;
import com.example.rmmservices.repository.ServiceCostEntries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class DeviceQueryController {

    private final DeviceEntries deviceEntries;

    private final DeviceTypeEntries deviceTypeEntries;

    private final CustomerEntries customerEntries;

    private final ServiceCostEntries serviceCostEntries;

    @Autowired
    public DeviceQueryController(final DeviceEntries deviceEntries,
                                 final DeviceTypeEntries deviceTypeEntries,
                                 final CustomerEntries customerEntries,
                                 final ServiceCostEntries serviceCostEntries) {
        this.deviceEntries = deviceEntries;
        this.deviceTypeEntries = deviceTypeEntries;
        this.customerEntries = customerEntries;
        this.serviceCostEntries = serviceCostEntries;
    }

    @GetMapping("/customer/{customerId}/devices")
    public List<Data> get(@PathVariable final Long customerId) {
        final Devices devices = new JpaDevices(deviceEntries,
                deviceTypeEntries,
                customerEntries,
                serviceCostEntries);
        final List<Device> deviceList = devices.get(customerId);
        return deviceList.stream().map(Device::data).collect(Collectors.toList());
    }

}
