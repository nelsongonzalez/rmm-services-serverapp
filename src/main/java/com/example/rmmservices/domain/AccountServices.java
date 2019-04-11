package com.example.rmmservices.domain;

import java.util.List;

public interface AccountServices {

    List<AccountService> get(Long customerId);
}
