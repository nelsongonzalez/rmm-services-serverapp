package com.example.rmmservices.domain.impl;

import com.example.rmmservices.domain.AccountService;
import com.example.rmmservices.domain.AccountServices;
import com.example.rmmservices.repository.AccountServiceEntries;
import com.example.rmmservices.repository.AccountServiceEntries.AccountServiceEntry;
import com.example.rmmservices.repository.CustomerEntries;
import com.example.rmmservices.repository.CustomerEntries.CustomerEntry;
import com.example.rmmservices.repository.ServiceCostEntries;
import com.example.rmmservices.repository.ServiceEntries;
import org.springframework.data.domain.Example;

import java.util.List;
import java.util.stream.Collectors;

public final class JpaAccountServices implements AccountServices {

    private final ServiceEntries serviceEntries;

    private final AccountServiceEntries accountServiceEntries;

    private final CustomerEntries customerEntries;

    private final ServiceCostEntries serviceCostEntries;

    public JpaAccountServices(final ServiceEntries serviceEntries,
                              final AccountServiceEntries accountServiceEntries,
                              final CustomerEntries customerEntries,
                              final ServiceCostEntries serviceCostEntries) {
        this.serviceEntries = serviceEntries;
        this.accountServiceEntries = accountServiceEntries;
        this.customerEntries = customerEntries;
        this.serviceCostEntries = serviceCostEntries;
    }

    @Override
    public List<AccountService> get(final Long customerId) {
        final CustomerEntry customerEntry = new CustomerEntry(customerId);
        final AccountServiceEntry accountServiceEntry = new AccountServiceEntry(customerEntry);
        final Example<AccountServiceEntry> accountServiceEntryExample = Example.of(accountServiceEntry);
        return accountServiceEntries.findAll(accountServiceEntryExample).stream()
                .map(entry -> new JpaAccountService(entry.getId(),
                        entry.getService().getId(),
                        entry.getOwner().getId(),
                        serviceEntries,
                        accountServiceEntries,
                        customerEntries,
                        serviceCostEntries))
                .collect(Collectors.toList());
    }
}
