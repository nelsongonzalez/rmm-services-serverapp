package com.example.rmmservices.controller;

import com.example.rmmservices.controller.bean.DeviceSaveCommand;
import com.example.rmmservices.entity.Device;
import com.example.rmmservices.service.DeviceCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.Response;

@RestController
public class DeviceCommandController {

    private final DeviceCommandService deviceQueryService;

    @Autowired
    public DeviceCommandController(DeviceCommandService deviceQueryService) {
        this.deviceQueryService = deviceQueryService;
    }

    @PostMapping("/customer/{customerId}/devices/new")
    public Long add(@PathVariable Long customerId, @RequestBody DeviceSaveCommand newDevice) {
        Device device = deviceQueryService.add(customerId, newDevice.getSystemName(), newDevice.getDeviceTypeId());
        return device.getId();
    }

    @PostMapping("/customer/{customerId}/devices/{deviceId}/update")
    public Response update(@PathVariable Long customerId, @PathVariable Long deviceId,
                           @RequestBody DeviceSaveCommand updatedDevice) {
        deviceQueryService.update(customerId, deviceId, updatedDevice.getSystemName(), updatedDevice.getDeviceTypeId());
        return Response.ok().build();
    }

    @PostMapping("/customer/{customerId}/devices/{deviceId}/delete")
    public Response delete(@PathVariable Long customerId, @PathVariable Long deviceId) {
        deviceQueryService.delete(customerId, deviceId);
        return Response.ok().build();
    }
}
