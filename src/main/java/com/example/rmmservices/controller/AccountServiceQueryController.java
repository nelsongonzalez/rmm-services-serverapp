package com.example.rmmservices.controller;

import com.example.rmmservices.domain.AccountService;
import com.example.rmmservices.domain.AccountService.Data;
import com.example.rmmservices.domain.AccountServices;
import com.example.rmmservices.domain.impl.JpaAccountServices;
import com.example.rmmservices.repository.AccountServiceEntries;
import com.example.rmmservices.repository.CustomerEntries;
import com.example.rmmservices.repository.ServiceCostEntries;
import com.example.rmmservices.repository.ServiceEntries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AccountServiceQueryController {

    private final ServiceEntries serviceEntries;

    private final AccountServiceEntries accountServiceEntries;

    private final CustomerEntries customerEntries;

    private final ServiceCostEntries serviceCostEntries;

    @Autowired
    public AccountServiceQueryController(final ServiceEntries serviceEntries,
                                         final AccountServiceEntries accountServiceEntries,
                                         final CustomerEntries customerEntries,
                                         final ServiceCostEntries serviceCostEntries) {
        this.serviceEntries = serviceEntries;
        this.accountServiceEntries = accountServiceEntries;
        this.customerEntries = customerEntries;
        this.serviceCostEntries = serviceCostEntries;
    }

    @GetMapping("/customer/{customerId}/services")
    public List<Data> get(@PathVariable final Long customerId) {
        final AccountServices accountServices = new JpaAccountServices(serviceEntries,
                accountServiceEntries,
                customerEntries,
                serviceCostEntries);
        final List<AccountService> accountServicesList = accountServices.get(customerId);
        return accountServicesList.stream()
                .map(AccountService::data)
                .collect(Collectors.toList());
    }

}
