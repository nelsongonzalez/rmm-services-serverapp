package com.example.rmmservices.entity;

import javax.persistence.*;

@Entity
@Table(name = "services")
public class Service {

    @Id
    @SequenceGenerator(name = "devices_id_seq", sequenceName = "devices_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "devices_id_seq")
    private Long id;

    private String name;

    private String description;

    public Service() {
    }

    public Service(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
