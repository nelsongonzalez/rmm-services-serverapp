package com.example.rmmservices.domain.impl;

import com.example.rmmservices.domain.AccountService;
import com.example.rmmservices.repository.AccountServiceEntries;
import com.example.rmmservices.repository.AccountServiceEntries.AccountServiceEntry;
import com.example.rmmservices.repository.CustomerEntries;
import com.example.rmmservices.repository.CustomerEntries.CustomerEntry;
import com.example.rmmservices.repository.ServiceCostEntries;
import com.example.rmmservices.repository.ServiceEntries;
import com.example.rmmservices.repository.ServiceEntries.ServiceEntry;
import org.springframework.data.domain.Example;

import javax.persistence.EntityNotFoundException;
import java.util.Objects;
import java.util.Optional;

public final class JpaAccountService implements AccountService {

    private final Long id;

    private final Long serviceId;

    private final Long customerId;

    private final ServiceEntries serviceEntries;

    private final AccountServiceEntries accountServiceEntries;

    private final CustomerEntries customerEntries;

    private final ServiceCostEntries serviceCostEntries;

    public JpaAccountService(final Long id,
                             final Long serviceId,
                             final Long customerId,
                             final ServiceEntries serviceEntries,
                             final AccountServiceEntries accountServiceEntries,
                             final CustomerEntries customerEntries,
                             final ServiceCostEntries serviceCostEntries) {
        this.id = id;
        this.serviceId = serviceId;
        this.customerId = customerId;
        this.serviceEntries = serviceEntries;
        this.accountServiceEntries = accountServiceEntries;
        this.customerEntries = customerEntries;
        this.serviceCostEntries = serviceCostEntries;
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        final JpaAccountService that = (JpaAccountService) other;
        return id.equals(that.id) &&
                serviceId.equals(that.serviceId) &&
                customerId.equals(that.customerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, serviceId, customerId);
    }

    @Override
    public AccountService register() {
        try {
            final Example<AccountServiceEntry> accountServiceEntryExample = serviceAddedByExample(serviceId, customerId);
            final Optional<AccountServiceEntry> optionalAccountServiceEntry = accountServiceEntries.findOne(accountServiceEntryExample);
            if (optionalAccountServiceEntry.isPresent()) {
                throw new IllegalArgumentException("Can't select the domain.");
            }
            final ServiceEntry serviceEntry = serviceEntries.getOne(serviceId);
            final CustomerEntry customerEntry = customerEntries.getOne(customerId);
            final AccountServiceEntry accountServiceEntry = accountServiceEntries.save(new AccountServiceEntry(serviceEntry, customerEntry));
            return new JpaAccountService(accountServiceEntry.getId(),
                    accountServiceEntry.getService().getId(),
                    accountServiceEntry.getOwner().getId(),
                    serviceEntries,
                    accountServiceEntries,
                    customerEntries,
                    serviceCostEntries);
        } catch (final EntityNotFoundException e) {
            throw new IllegalArgumentException("Can't select the device.", e);
        }
    }

    @Override
    public void unregister() {
        final Example<AccountServiceEntry> accountServiceEntryExample = serviceAddedByExample(serviceId, customerId);
        final Optional<AccountServiceEntry> optionalAccountServiceEntry = accountServiceEntries.findOne(accountServiceEntryExample);
        if (optionalAccountServiceEntry.isPresent()) {
            accountServiceEntries.delete(optionalAccountServiceEntry.get());
        } else {
            throw new IllegalArgumentException("Can't delete the domain.");
        }
    }

    private Example<AccountServiceEntry> serviceAddedByExample(final Long serviceId, final Long customerId) {
        final ServiceEntry serviceEntry = new ServiceEntry(serviceId);
        final CustomerEntry customerEntry = new CustomerEntry(customerId);
        final AccountServiceEntry accountServiceEntry = new AccountServiceEntry(serviceEntry, customerEntry);
        return Example.of(accountServiceEntry);
    }

    @Override
    public Data data() {
        return new Data(id, serviceId, customerId);
    }
}
