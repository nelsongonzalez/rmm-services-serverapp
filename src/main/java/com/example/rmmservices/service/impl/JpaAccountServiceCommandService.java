package com.example.rmmservices.service.impl;

import com.example.rmmservices.entity.AccountService;
import com.example.rmmservices.entity.Customer;
import com.example.rmmservices.entity.Service;
import com.example.rmmservices.repository.AccountServiceRepository;
import com.example.rmmservices.repository.CustomerRepository;
import com.example.rmmservices.repository.ServiceRepository;
import com.example.rmmservices.service.AccountServiceCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@org.springframework.stereotype.Service
public class JpaAccountServiceCommandService implements AccountServiceCommandService {

    private final ServiceRepository serviceRepository;

    private final AccountServiceRepository accountServiceRepository;

    private final CustomerRepository customerRepository;


    @Autowired
    public JpaAccountServiceCommandService(ServiceRepository serviceRepository,
                                           AccountServiceRepository accountServiceRepository,
                                           CustomerRepository customerRepository) {
        this.serviceRepository = serviceRepository;
        this.accountServiceRepository = accountServiceRepository;
        this.customerRepository = customerRepository;
    }


    @Override
    public AccountService add(Long customerId, Long serviceId) {
        try {
            Example<AccountService> serviceAddedBy = serviceAddedByExample(serviceId, customerId);
            Optional<AccountService> accountServiceOptional = accountServiceRepository.findOne(serviceAddedBy);
            if (accountServiceOptional.isPresent()) {
                throw new IllegalArgumentException("Can't select the service.");
            }
            Service service = serviceRepository.getOne(serviceId);
            Customer customer = customerRepository.getOne(customerId);
            AccountService accountService = new AccountService(service, customer);
            return accountServiceRepository.save(accountService);
        } catch (EntityNotFoundException e) {
            throw new IllegalArgumentException("Can't select the device.", e);
        }
    }

    @Override
    public void delete(Long customerId, Long serviceId) {
        Example<AccountService> serviceAddedBy = serviceAddedByExample(serviceId, customerId);
        Optional<AccountService> accountServiceOptional = accountServiceRepository.findOne(serviceAddedBy);
        if (accountServiceOptional.isPresent()) {
            accountServiceRepository.delete(accountServiceOptional.get());
        } else {
            throw new IllegalArgumentException("Can't delete the service.");
        }
    }

    private Example<AccountService> serviceAddedByExample(Long serviceId, Long customerId) {
        Service serviceExample = new Service(serviceId);
        Customer customerExample = new Customer(customerId);
        AccountService accountServiceExample = new AccountService(serviceExample, customerExample);
        return Example.of(accountServiceExample);
    }
}
