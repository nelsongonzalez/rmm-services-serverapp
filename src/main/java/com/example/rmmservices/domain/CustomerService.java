package com.example.rmmservices.domain;

import javax.persistence.*;

@Entity
@Table(name = "customer_services")
public class CustomerService {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private Service service;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Customer owner;

    public CustomerService() {
    }

    public CustomerService(Customer owner) {
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public Service getService() {
        return service;
    }

    public Customer getOwner() {
        return owner;
    }
}
