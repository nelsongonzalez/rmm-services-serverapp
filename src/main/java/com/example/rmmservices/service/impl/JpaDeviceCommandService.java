package com.example.rmmservices.service.impl;

import com.example.rmmservices.entity.Customer;
import com.example.rmmservices.entity.Device;
import com.example.rmmservices.entity.DeviceType;
import com.example.rmmservices.repository.CustomerRepository;
import com.example.rmmservices.repository.DeviceRepository;
import com.example.rmmservices.repository.DeviceTypeRepository;
import com.example.rmmservices.service.DeviceCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class JpaDeviceCommandService implements DeviceCommandService {

    private final DeviceRepository deviceRepository;

    private final DeviceTypeRepository deviceTypeRepository;

    private final CustomerRepository customerRepository;


    @Autowired
    public JpaDeviceCommandService(DeviceRepository deviceRepository, DeviceTypeRepository deviceTypeRepository,
                                   CustomerRepository customerRepository) {
        this.deviceRepository = deviceRepository;
        this.deviceTypeRepository = deviceTypeRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public Device add(Long customerId, String systemName, Long deviceTypeId) {
        try {
            DeviceType deviceType = deviceTypeRepository.getOne(deviceTypeId);
            Customer customer = customerRepository.getOne(customerId);
            Device device = new Device(systemName, deviceType, customer);
            return deviceRepository.save(device);
        } catch (EntityNotFoundException e) {
            throw new IllegalArgumentException("Can't register the device.", e);
        }
    }

    @Override
    public void update(Long customerId, Long deviceId, String systemName, Long deviceTypeId) {
        Example<Device> deviceOwnedBy = deviceOwnedByExample(deviceId, customerId);
        Optional<Device> deviceOptional = deviceRepository.findOne(deviceOwnedBy);
        if (deviceOptional.isPresent()) {
            Device device = deviceOptional.get();
            device.setSystemName(systemName);
            device.setType(deviceTypeRepository.getOne(deviceTypeId));
            deviceRepository.save(device);
        } else {
            throw new IllegalArgumentException("Can't update the device.");
        }
    }

    @Override
    public void delete(Long customerId, Long deviceId) {
        Example<Device> deviceOwnedBy = deviceOwnedByExample(deviceId, customerId);
        Optional<Device> deviceOptional = deviceRepository.findOne(deviceOwnedBy);
        if (deviceOptional.isPresent()) {
            deviceRepository.delete(deviceOptional.get());
        } else {
            throw new IllegalArgumentException("Can't delete the device.");
        }
    }

    private Example<Device> deviceOwnedByExample(Long deviceId, Long customerId) {
        Customer customerExample = new Customer(customerId);
        Device deviceExample = new Device(deviceId, customerExample);
        return Example.of(deviceExample);
    }
}
