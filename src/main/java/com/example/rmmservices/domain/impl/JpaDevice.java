package com.example.rmmservices.domain.impl;

import com.example.rmmservices.domain.Device;
import com.example.rmmservices.repository.CustomerEntries;
import com.example.rmmservices.repository.CustomerEntries.CustomerEntry;
import com.example.rmmservices.repository.DeviceEntries;
import com.example.rmmservices.repository.DeviceEntries.DeviceEntry;
import com.example.rmmservices.repository.DeviceTypeEntries;
import com.example.rmmservices.repository.DeviceTypeEntries.DeviceTypeEntry;
import com.example.rmmservices.repository.ServiceCostEntries;
import org.springframework.data.domain.Example;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

public final class JpaDevice implements Device {

    private final Long id;

    private final String systemName;

    private final Long deviceTypeId;

    private final Long customerId;

    private final DeviceEntries deviceEntries;

    private final DeviceTypeEntries deviceTypeEntries;

    private final CustomerEntries customerEntries;

    private final ServiceCostEntries serviceCostEntries;

    public JpaDevice(final Long id,
                     final String systemName,
                     final Long deviceTypeId,
                     final Long customerId,
                     final DeviceEntries deviceEntries,
                     final DeviceTypeEntries deviceTypeEntries,
                     final CustomerEntries customerEntries,
                     final ServiceCostEntries serviceCostEntries) {
        this.id = id;
        this.systemName = systemName;
        this.deviceTypeId = deviceTypeId;
        this.customerId = customerId;
        this.deviceEntries = deviceEntries;
        this.deviceTypeEntries = deviceTypeEntries;
        this.customerEntries = customerEntries;
        this.serviceCostEntries = serviceCostEntries;
    }

    @Override
    public Device register() {
        try {
            final DeviceTypeEntry deviceTypeEntry = deviceTypeEntries.getOne(deviceTypeId);
            final CustomerEntry customerEntry = customerEntries.getOne(customerId);
            final DeviceEntry deviceEntry = deviceEntries.save(new DeviceEntry(systemName, deviceTypeEntry, customerEntry));
            return new JpaDevice(deviceEntry.getId(),
                    deviceEntry.getSystemName(),
                    deviceEntry.getType().getId(),
                    deviceEntry.getOwner().getId(),
                    deviceEntries,
                    deviceTypeEntries,
                    customerEntries,
                    serviceCostEntries);
        } catch (final EntityNotFoundException e) {
            throw new IllegalArgumentException("Can't register the device.", e);
        }
    }

    @Override
    public void update() {
        final Example<DeviceEntry> deviceEntryExample = deviceOwnedByExample();
        final Optional<DeviceEntry> optionalDeviceEntry = deviceEntries.findOne(deviceEntryExample);
        if (optionalDeviceEntry.isPresent()) {
            final DeviceEntry deviceEntry = optionalDeviceEntry.get();
            deviceEntries.save(new DeviceEntry(deviceEntry.getId(),
                    deviceEntry.getSystemName(),
                    deviceEntry.getType(),
                    deviceEntry.getOwner()));
        } else {
            throw new IllegalArgumentException("Can't update the device.");
        }
    }

    @Override
    public void unregister() {
        final Example<DeviceEntry> deviceEntryExample = deviceOwnedByExample();
        final Optional<DeviceEntry> optionalDeviceEntry = deviceEntries.findOne(deviceEntryExample);
        if (optionalDeviceEntry.isPresent()) {
            deviceEntries.delete(optionalDeviceEntry.get());
        } else {
            throw new IllegalArgumentException("Can't delete the device.");
        }
    }

    private Example<DeviceEntry> deviceOwnedByExample() {
        final CustomerEntry customerEntry = new CustomerEntry(customerId);
        final DeviceEntry deviceEntry = new DeviceEntry(id, customerEntry);
        return Example.of(deviceEntry);
    }

    @Override
    public Data data() {
        return new Data(id, systemName, deviceTypeId, customerId);
    }
}
