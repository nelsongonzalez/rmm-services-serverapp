package com.example.rmmservices.domain.impl;

import com.example.rmmservices.domain.Bill;
import com.example.rmmservices.domain.Customer;
import com.example.rmmservices.domain.Customer.MonthlyBillData.ServiceCostData;
import com.example.rmmservices.repository.AccountServiceEntries.AccountServiceEntry;
import com.example.rmmservices.repository.CustomerEntries;
import com.example.rmmservices.repository.CustomerEntries.CustomerEntry;
import com.example.rmmservices.repository.DeviceEntries.DeviceEntry;
import com.example.rmmservices.repository.ServiceCostEntries;

import javax.money.Monetary;
import javax.money.MonetaryAmount;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public final class JpaCustomer implements Customer {

    private static final MonetaryAmount ZERO_COST = Monetary.getDefaultAmountFactory()
            .setNumber(BigDecimal.ZERO)
            .setCurrency(Monetary.getCurrency("USD"))
            .create();

    private static final MonetaryAmount DEVICE_COST = Monetary.getDefaultAmountFactory()
            .setNumber(new BigDecimal("4"))
            .setCurrency(Monetary.getCurrency("USD"))
            .create();

    private final Long id;

    private final CustomerEntries customerEntries;

    private final Bill bill;

    public JpaCustomer(final Long id,
                       final CustomerEntries customerEntries,
                       final ServiceCostEntries serviceCostEntries) {
        this.id = id;
        this.customerEntries = customerEntries;
        this.bill = new JpaMonthlyBill(serviceCostEntries);
    }

    @Override
    public MonthlyBillData billData() {
        final CustomerEntry customerEntry = customerEntries.getOne(id);
        final List<ServiceCostData> serviceCostDataList = new ArrayList<>();
        for (final AccountServiceEntry accountService : customerEntry.getAccountServices()) {
            MonetaryAmount serviceCost = Monetary.getDefaultAmountFactory()
                    .setNumber(BigDecimal.ZERO)
                    .setCurrency(Monetary.getCurrency("USD"))
                    .create();
            for (final DeviceEntry device : customerEntry.getDevices()) {
                serviceCost = serviceCost.add(
                        bill.monthlyCost(accountService.getService().getId(), device.getType().getId()));
            }
            serviceCostDataList.add(new ServiceCostData(accountService.getService().getName(), serviceCost));
        }
        serviceCostDataList.add(new ServiceCostData("Devices", DEVICE_COST.multiply(customerEntry.getDevices().size())));
        final MonetaryAmount output = serviceCostDataList.stream()
                .map(ServiceCostData::getCost)
                .reduce(MonetaryAmount::add)
                .orElse(ZERO_COST);
        return new MonthlyBillData(output, serviceCostDataList);
    }
}
