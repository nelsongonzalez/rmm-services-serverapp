package com.example.rmmservices.domain;

import javax.persistence.*;

@Entity
@Table(name = "customer_devices")
public class CustomerDevice {

    @Id
    private Long id;

    private String systemName;

    @ManyToOne
    @JoinColumn(name = "device_type_id")
    private DeviceType type;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Customer owner;

    public CustomerDevice() {
    }

    public CustomerDevice(Customer owner) {
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public String getSystemName() {
        return systemName;
    }

    public DeviceType getType() {
        return type;
    }

    public Customer getOwner() {
        return owner;
    }
}
