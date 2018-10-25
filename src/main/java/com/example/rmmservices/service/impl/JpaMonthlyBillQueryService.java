package com.example.rmmservices.service.impl;

import com.example.rmmservices.service.BillQueryService;
import com.example.rmmservices.service.bean.MonthlyBillQuery;
import org.springframework.stereotype.Service;

@Service
public class JpaMonthlyBillQueryService implements BillQueryService {

    @Override
    public MonthlyBillQuery calculate(Long customerId) {
        return null;
    }
}
