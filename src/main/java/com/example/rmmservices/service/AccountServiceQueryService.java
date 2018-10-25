package com.example.rmmservices.service;

import com.example.rmmservices.entity.AccountService;

import java.util.List;

public interface AccountServiceQueryService {

    List<AccountService> get(Long customerId);

}
