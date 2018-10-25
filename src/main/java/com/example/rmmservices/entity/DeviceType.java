package com.example.rmmservices.entity;

import javax.persistence.*;

@Entity
@Table(name = "device_types")
public class DeviceType {

    @Id
    @SequenceGenerator(name = "device_types_id_seq", sequenceName = "device_types_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "device_types_id_seq")
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
