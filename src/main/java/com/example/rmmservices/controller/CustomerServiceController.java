package com.example.rmmservices.controller;

import com.example.rmmservices.domain.Customer;
import com.example.rmmservices.domain.CustomerService;
import com.example.rmmservices.repository.CustomerServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerServiceController {

    private final CustomerServiceRepository customerServiceRepository;

    @Autowired
    public CustomerServiceController(CustomerServiceRepository customerServiceRepository) {
        this.customerServiceRepository = customerServiceRepository;
    }

    @GetMapping("/customer/{customerId}/services")
    public List<CustomerService> get(@PathVariable Long customerId) {
        Customer customer = new Customer(customerId);
        CustomerService customerDeviceExample = new CustomerService(customer);
        Example<CustomerService> example = Example.of(customerDeviceExample);
        return customerServiceRepository.findAll(example);
    }

    @PostMapping("/customer/service/add")
    public CustomerService check(CustomerService customerDevice) {
        return customerServiceRepository.save(customerDevice);
    }

    @PostMapping("/customer/service/update")
    public CustomerService uncheck(CustomerService customerDevice) {
        return customerServiceRepository.save(customerDevice);
    }

}
