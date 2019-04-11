package com.example.rmmservices.controller;

import com.example.rmmservices.controller.bean.AccountServiceSaveCommand;
import com.example.rmmservices.domain.AccountService;
import com.example.rmmservices.domain.impl.JpaAccountService;
import com.example.rmmservices.repository.AccountServiceEntries;
import com.example.rmmservices.repository.CustomerEntries;
import com.example.rmmservices.repository.ServiceCostEntries;
import com.example.rmmservices.repository.ServiceEntries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;

@RestController
public class AccountServiceCommandController {

    private final ServiceEntries serviceEntries;

    private final AccountServiceEntries accountServiceEntries;

    private final CustomerEntries customerEntries;

    private final ServiceCostEntries serviceCostEntries;

    @Autowired
    public AccountServiceCommandController(final ServiceEntries serviceEntries,
                                           final AccountServiceEntries accountServiceEntries,
                                           final CustomerEntries customerEntries,
                                           final ServiceCostEntries serviceCostEntries) {
        this.serviceEntries = serviceEntries;
        this.accountServiceEntries = accountServiceEntries;
        this.customerEntries = customerEntries;
        this.serviceCostEntries = serviceCostEntries;
    }

    @PostMapping("/customer/{customerId}/services/new")
    public Response create(@PathVariable final Long customerId,
                           @RequestBody final AccountServiceSaveCommand newService,
                           final HttpServletResponse response) {
        try {
            final AccountService accountService = new JpaAccountService(0L,
                    newService.getServiceId(),
                    customerId,
                    serviceEntries,
                    accountServiceEntries,
                    customerEntries,
                    serviceCostEntries)
                    .register();
            return Response.ok(accountService.data()).build();
        } catch (final IllegalArgumentException e) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return Response.noContent().build();
        }
    }

    @PostMapping("/customer/{customerId}/services/{serviceId}/delete")
    public Response delete(@PathVariable final Long customerId, @PathVariable final Long serviceId) {
        final AccountService accountService = new JpaAccountService(0L,
                serviceId,
                customerId,
                serviceEntries,
                accountServiceEntries,
                customerEntries,
                serviceCostEntries);
        accountService.unregister();
        return Response.ok().build();
    }
}
