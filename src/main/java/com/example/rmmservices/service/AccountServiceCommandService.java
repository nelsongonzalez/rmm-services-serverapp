package com.example.rmmservices.service;

import com.example.rmmservices.entity.AccountService;

public interface AccountServiceCommandService {

    AccountService add(Long customerId, Long serviceId);

    void delete(Long customerId, Long serviceId);
}
