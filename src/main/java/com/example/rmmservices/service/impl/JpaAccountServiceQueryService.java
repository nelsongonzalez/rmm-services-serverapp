package com.example.rmmservices.service.impl;

import com.example.rmmservices.entity.AccountService;
import com.example.rmmservices.entity.Customer;
import com.example.rmmservices.repository.AccountServiceRepository;
import com.example.rmmservices.service.AccountServiceQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JpaAccountServiceQueryService implements AccountServiceQueryService {

    private final AccountServiceRepository accountServiceRepository;

    @Autowired
    public JpaAccountServiceQueryService(AccountServiceRepository accountServiceRepository) {
        this.accountServiceRepository = accountServiceRepository;
    }

    @Override
    public List<AccountService> get(Long customerId) {
        Customer customerExample = new Customer(customerId);
        AccountService accountServiceExample = new AccountService(customerExample);
        Example<AccountService> accountServicesByCustomer = Example.of(accountServiceExample);
        return accountServiceRepository.findAll(accountServicesByCustomer);
    }
}
