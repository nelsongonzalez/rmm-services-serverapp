package com.example.rmmservices.domain;

import javax.money.MonetaryAmount;

public interface Bill {

    MonetaryAmount monthlyCost(Long serviceId, Long deviceTypeId);
}
