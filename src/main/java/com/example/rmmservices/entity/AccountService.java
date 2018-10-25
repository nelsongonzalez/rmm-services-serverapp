package com.example.rmmservices.entity;

import javax.persistence.*;

@Entity
@Table(name = "account_services")
public class AccountService {

    @Id
    @SequenceGenerator(name = "account_services_id_seq", sequenceName = "account_services_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_services_id_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private Service service;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer owner;

    public AccountService() {
    }

    public AccountService(Customer owner) {
        this.owner = owner;
    }

    public AccountService(Service service, Customer owner) {
        this.service = service;
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
