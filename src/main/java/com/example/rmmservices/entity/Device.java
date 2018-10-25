package com.example.rmmservices.entity;

import javax.persistence.*;

@Entity
@Table(name = "devices")
public class Device {

    @Id
    @SequenceGenerator(name = "devices_id_seq", sequenceName = "devices_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "devices_id_seq")
    private Long id;

    private String systemName;

    @ManyToOne
    @JoinColumn(name = "device_type_id")
    private DeviceType type;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer owner;

    public Device() {
    }

    public Device(Customer owner) {
        this.owner = owner;
    }

    public Device(String systemName, DeviceType deviceType, Customer owner) {
        this.systemName = systemName;
        this.type = deviceType;
        this.owner = owner;
    }

    public Device(Long id, Customer owner) {
        this.id = id;
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public void setType(DeviceType type) {
        this.type = type;
    }

    public DeviceType getType() {
        return type;
    }

    public Customer getOwner() {
        return owner;
    }
}
