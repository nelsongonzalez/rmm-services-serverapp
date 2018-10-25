package com.example.rmmservices.service.impl;

import com.example.rmmservices.entity.Customer;
import com.example.rmmservices.entity.Device;
import com.example.rmmservices.repository.DeviceRepository;
import com.example.rmmservices.service.DeviceQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JpaDeviceQueryService implements DeviceQueryService {

    private final DeviceRepository deviceRepository;

    @Autowired
    public JpaDeviceQueryService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @Override
    public List<Device> get(Long customerId) {
        Customer customer = new Customer(customerId);
        Device deviceExample = new Device(customer);
        Example<Device> example = Example.of(deviceExample);
        return deviceRepository.findAll(example);
    }
}
