package com.example.rmmservices.controller;

import com.example.rmmservices.controller.bean.DeviceSaveCommand;
import com.example.rmmservices.domain.Device;
import com.example.rmmservices.domain.Device.Data;
import com.example.rmmservices.domain.impl.JpaDevice;
import com.example.rmmservices.repository.CustomerEntries;
import com.example.rmmservices.repository.DeviceEntries;
import com.example.rmmservices.repository.DeviceTypeEntries;
import com.example.rmmservices.repository.ServiceCostEntries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.Response;

@RestController
public class DeviceCommandController {

    private final DeviceEntries deviceEntries;

    private final DeviceTypeEntries deviceTypeEntries;

    private final CustomerEntries customerEntries;

    private final ServiceCostEntries serviceCostEntries;

    @Autowired
    public DeviceCommandController(final DeviceEntries deviceEntries,
                                   final DeviceTypeEntries deviceTypeEntries,
                                   final CustomerEntries customerEntries,
                                   final ServiceCostEntries serviceCostEntries) {
        this.deviceEntries = deviceEntries;
        this.deviceTypeEntries = deviceTypeEntries;
        this.customerEntries = customerEntries;
        this.serviceCostEntries = serviceCostEntries;
    }

    @PostMapping("/customer/{customerId}/devices/new")
    public Data add(@PathVariable final Long customerId, @RequestBody final DeviceSaveCommand newDevice) {
        final Device device = new JpaDevice(0L,
                newDevice.getSystemName(),
                newDevice.getDeviceTypeId(),
                customerId,
                deviceEntries,
                deviceTypeEntries,
                customerEntries,
                serviceCostEntries)
                .register();
        return device.data();
    }

    @PostMapping("/customer/{customerId}/devices/{deviceId}/update")
    public Response update(@PathVariable final Long customerId, @PathVariable final Long deviceId, @RequestBody final DeviceSaveCommand updatedDevice) {
        final Device device = new JpaDevice(deviceId,
                updatedDevice.getSystemName(),
                updatedDevice.getDeviceTypeId(),
                customerId,
                deviceEntries,
                deviceTypeEntries,
                customerEntries,
                serviceCostEntries);
        device.update();
        return Response.ok().build();
    }

    @PostMapping("/customer/{customerId}/devices/{deviceId}/delete")
    public Response delete(@PathVariable final Long customerId, @PathVariable final Long deviceId) {
        final Device device = new JpaDevice(deviceId,
                "",
                0L,
                customerId,
                deviceEntries,
                deviceTypeEntries,
                customerEntries,
                serviceCostEntries);
        device.unregister();
        return Response.ok().build();
    }
}
