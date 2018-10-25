package com.example.rmmservices.service;

import com.example.rmmservices.entity.Device;

import java.util.List;

public interface DeviceQueryService {

    List<Device> get(Long customerId);
}
