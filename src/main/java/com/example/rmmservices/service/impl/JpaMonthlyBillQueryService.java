package com.example.rmmservices.service.impl;

import com.example.rmmservices.entity.*;
import com.example.rmmservices.repository.ServiceCostRepository;
import com.example.rmmservices.service.AccountServiceQueryService;
import com.example.rmmservices.service.BillQueryService;
import com.example.rmmservices.service.DeviceQueryService;
import com.example.rmmservices.service.bean.MonthlyBillQuery;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
public class JpaMonthlyBillQueryService implements BillQueryService {

    private static final BigDecimal DEVICE_COST = new BigDecimal("4");

    private final AccountServiceQueryService accountServiceQueryService;

    private final DeviceQueryService deviceQueryService;

    private final ServiceCostRepository serviceCostRepository;

    public JpaMonthlyBillQueryService(AccountServiceQueryService accountServiceQueryService,
                                      DeviceQueryService deviceQueryService,
                                      ServiceCostRepository serviceCostRepository) {
        this.accountServiceQueryService = accountServiceQueryService;
        this.deviceQueryService = deviceQueryService;
        this.serviceCostRepository = serviceCostRepository;
    }

    @Override
    public MonthlyBillQuery calculate(Long customerId) {
        List<Device> devices = deviceQueryService.get(customerId);
        List<AccountService> accountServices = accountServiceQueryService.get(customerId);
        List<ServiceCost> serviceCosts = serviceCostRepository.findAll();

        List<MonthlyBillQuery.CostPerService> explanation = new ArrayList<>();
        for (AccountService accountService : accountServices) {
            Service service = accountService.getService();
            BigDecimal serviceCost = BigDecimal.ZERO;
            for (Device device : devices) {
                DeviceType deviceType = device.getType();
                serviceCost = serviceCost.add(serviceCosts.stream()
                        .filter((c) -> selectedService(service, deviceType, c))
                        .findFirst()
                        .map(ServiceCost::getMonthlyCost)
                        .orElse(BigDecimal.ZERO));
            }
            explanation.add(new MonthlyBillQuery.CostPerService(service.getName(), serviceCost));
        }
        explanation.add(new MonthlyBillQuery.CostPerService("Devices", DEVICE_COST.multiply(new BigDecimal(devices.size()))));
        BigDecimal output = explanation.stream()
                .map(MonthlyBillQuery.CostPerService::getCost)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
        return new MonthlyBillQuery(output, explanation);
    }

    private boolean selectedService(Service service, DeviceType deviceType, ServiceCost c) {
        return service.getId().equals(c.getService().getId()) && deviceType.getId().equals(c.getDeviceType().getId());
    }
}
