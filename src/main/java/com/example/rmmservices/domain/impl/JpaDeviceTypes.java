package com.example.rmmservices.domain.impl;

import com.example.rmmservices.repository.DeviceTypeEntries;
import com.example.rmmservices.domain.DeviceType;
import com.example.rmmservices.domain.DeviceTypes;

import java.util.List;
import java.util.stream.Collectors;

public final class JpaDeviceTypes implements DeviceTypes {

    private final DeviceTypeEntries deviceTypeEntries;

    public JpaDeviceTypes(final DeviceTypeEntries deviceTypeEntries) {
        this.deviceTypeEntries = deviceTypeEntries;
    }

    @Override
    public List<DeviceType> all() {
        return deviceTypeEntries.findAll().stream()
                .map(e -> new JpaDeviceType(e.getId(), e.getName()))
                .collect(Collectors.toList());
    }
}
