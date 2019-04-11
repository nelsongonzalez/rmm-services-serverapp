package com.example.rmmservices.domain.impl;

import com.example.rmmservices.domain.Device;
import com.example.rmmservices.domain.Devices;
import com.example.rmmservices.repository.CustomerEntries;
import com.example.rmmservices.repository.CustomerEntries.CustomerEntry;
import com.example.rmmservices.repository.DeviceEntries;
import com.example.rmmservices.repository.DeviceEntries.DeviceEntry;
import com.example.rmmservices.repository.DeviceTypeEntries;
import com.example.rmmservices.repository.ServiceCostEntries;
import org.springframework.data.domain.Example;

import java.util.List;
import java.util.stream.Collectors;

public final class JpaDevices implements Devices {

    private final DeviceEntries deviceEntries;

    private final DeviceTypeEntries deviceTypeEntries;

    private final CustomerEntries customerEntries;

    private final ServiceCostEntries serviceCostEntries;

    public JpaDevices(final DeviceEntries deviceEntries,
                      final DeviceTypeEntries deviceTypeEntries,
                      final CustomerEntries customerEntries,
                      final ServiceCostEntries serviceCostEntries) {
        this.deviceEntries = deviceEntries;
        this.deviceTypeEntries = deviceTypeEntries;
        this.customerEntries = customerEntries;
        this.serviceCostEntries = serviceCostEntries;
    }

    @Override
    public List<Device> get(final Long customerId) {
        final CustomerEntry customerEntry = new CustomerEntry(customerId);
        final DeviceEntry deviceEntry = new DeviceEntry(customerEntry);
        final Example<DeviceEntry> deviceEntryExample = Example.of(deviceEntry);
        return deviceEntries.findAll(deviceEntryExample).stream()
                .map(entry -> new JpaDevice(entry.getId(),
                        entry.getSystemName(),
                        entry.getType().getId(),
                        entry.getOwner().getId(),
                        deviceEntries,
                        deviceTypeEntries,
                        customerEntries,
                        serviceCostEntries))
                .collect(Collectors.toList());
    }
}
