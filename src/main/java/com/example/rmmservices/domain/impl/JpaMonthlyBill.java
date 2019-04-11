package com.example.rmmservices.domain.impl;

import com.example.rmmservices.domain.Bill;
import com.example.rmmservices.repository.ServiceCostEntries;
import com.example.rmmservices.repository.ServiceCostEntries.ServiceCostEntry;

import javax.money.Monetary;
import javax.money.MonetaryAmount;
import java.math.BigDecimal;
import java.util.List;

public final class JpaMonthlyBill implements Bill {

    private static final MonetaryAmount ZERO_COST = Monetary.getDefaultAmountFactory()
            .setNumber(BigDecimal.ZERO)
            .setCurrency(Monetary.getCurrency("USD"))
            .create();

    private final ServiceCostEntries serviceCostEntries;

    JpaMonthlyBill(final ServiceCostEntries serviceCostEntries) {
        this.serviceCostEntries = serviceCostEntries;
    }

    @Override
    public MonetaryAmount monthlyCost(final Long serviceId, final Long deviceTypeId) {
        final List<ServiceCostEntry> serviceCostEntryList = serviceCostEntries.findAll();
        for (final ServiceCostEntry serviceCostEntry : serviceCostEntryList) {
            if (serviceCostEntry.getService().getId().equals(serviceId)
                    && serviceCostEntry.getDeviceType().getId().equals(deviceTypeId)) {
                return Monetary.getDefaultAmountFactory()
                        .setNumber(serviceCostEntry.getMonthlyCost())
                        .setCurrency(Monetary.getCurrency("USD"))
                        .create();
            }
        }
        return ZERO_COST;
    }
}
