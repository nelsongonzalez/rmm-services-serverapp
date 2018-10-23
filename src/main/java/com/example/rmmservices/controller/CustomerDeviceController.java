package com.example.rmmservices.controller;

import com.example.rmmservices.domain.Customer;
import com.example.rmmservices.domain.CustomerDevice;
import com.example.rmmservices.repository.CustomerDeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;
import java.util.List;

@RestController
public class CustomerDeviceController {

    private final CustomerDeviceRepository customerDeviceRepository;

    @Autowired
    public CustomerDeviceController(CustomerDeviceRepository customerDeviceRepository) {
        this.customerDeviceRepository = customerDeviceRepository;
    }

    @GetMapping("/customer/{customerId}/devices")
    public List<CustomerDevice> get(@PathVariable Long customerId) {
        Customer customer = new Customer(customerId);
        CustomerDevice customerDeviceExample = new CustomerDevice(customer);
        Example<CustomerDevice> example = Example.of(customerDeviceExample);
        return customerDeviceRepository.findAll(example);
    }

    @PostMapping("/customer/devices/add")
    public CustomerDevice add(CustomerDevice customerDevice) {
        return customerDeviceRepository.save(customerDevice);
    }

    @PostMapping("/customer/devices/update")
    public CustomerDevice update(CustomerDevice customerDevice) {
        return customerDeviceRepository.save(customerDevice);
    }

    @DeleteMapping("/customer/devices/delete")
    public Response delete(CustomerDevice customerDevice) {
        customerDeviceRepository.delete(customerDevice);
        return Response.ok().build();
    }
}
