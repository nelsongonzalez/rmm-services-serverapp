package com.example.rmmservices.controller;

import com.example.rmmservices.controller.bean.AccountServiceQuery;
import com.example.rmmservices.entity.AccountService;
import com.example.rmmservices.service.AccountServiceQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AccountServiceQueryController {

    private final AccountServiceQueryService accountServiceQueryService;

    @Autowired
    public AccountServiceQueryController(AccountServiceQueryService accountServiceQueryService) {
        this.accountServiceQueryService = accountServiceQueryService;
    }

    @GetMapping("/customer/{customerId}/services")
    public List<AccountServiceQuery> get(@PathVariable Long customerId) {
        List<AccountService> accountServices = accountServiceQueryService.get(customerId);
        return accountServices.stream().map((s) -> new AccountServiceQuery()).collect(Collectors.toList());
    }

}
