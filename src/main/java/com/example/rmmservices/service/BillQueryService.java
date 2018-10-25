package com.example.rmmservices.service;

import com.example.rmmservices.service.bean.MonthlyBillQuery;

public interface BillQueryService {

    MonthlyBillQuery calculate(Long customerId);
}
