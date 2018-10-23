package com.example.rmmservices.repository;

import com.example.rmmservices.domain.CustomerService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerServiceRepository extends JpaRepository<CustomerService, Long> {

}
