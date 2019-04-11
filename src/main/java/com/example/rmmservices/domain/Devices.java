package com.example.rmmservices.domain;

import java.util.List;

public interface Devices {

    List<Device> get(Long customerId);
}
