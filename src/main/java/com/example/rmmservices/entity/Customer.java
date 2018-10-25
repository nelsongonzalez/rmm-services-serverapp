package com.example.rmmservices.entity;

import javax.persistence.*;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @SequenceGenerator(name = "customers_id_seq", sequenceName = "customers_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customers_id_seq")
    private Long id;

    private String name;

    public Customer() {
    }

    public Customer(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
