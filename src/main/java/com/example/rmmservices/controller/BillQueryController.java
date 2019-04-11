package com.example.rmmservices.controller;

import com.example.rmmservices.domain.Customer;
import com.example.rmmservices.domain.Customer.MonthlyBillData;
import com.example.rmmservices.domain.impl.JpaCustomer;
import com.example.rmmservices.repository.CustomerEntries;
import com.example.rmmservices.repository.ServiceCostEntries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillQueryController {

    private final CustomerEntries customerEntries;

    private final ServiceCostEntries serviceCostEntries;

    @Autowired
    public BillQueryController(final CustomerEntries customerEntries, final ServiceCostEntries serviceCostEntries) {
        this.customerEntries = customerEntries;
        this.serviceCostEntries = serviceCostEntries;
    }

    @GetMapping("/customer/{customerId}/bill")
    public MonthlyBillData get(@PathVariable final Long customerId) {
        final Customer customer = new JpaCustomer(customerId, customerEntries, serviceCostEntries);
        return customer.billData();
    }
}
