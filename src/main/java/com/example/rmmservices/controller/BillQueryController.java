package com.example.rmmservices.controller;

import com.example.rmmservices.service.BillQueryService;
import com.example.rmmservices.service.bean.MonthlyBillQuery;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillQueryController {

    private final BillQueryService billQueryService;

    public BillQueryController(BillQueryService billQueryService) {
        this.billQueryService = billQueryService;
    }

    @GetMapping("/customer/{customerId}/bill")
    public MonthlyBillQuery get(@PathVariable Long customerId) {
        return billQueryService.calculate(customerId);
    }
}
