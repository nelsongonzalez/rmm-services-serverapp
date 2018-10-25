package com.example.rmmservices.service;

import com.example.rmmservices.entity.Device;

public interface DeviceCommandService {

    Device add(Long customerId, String systemName, Long deviceTypeId);

    void update(Long customerId, Long deviceId, String systemName, Long deviceTypeId);

    void delete(Long customerId, Long deviceId);
}
