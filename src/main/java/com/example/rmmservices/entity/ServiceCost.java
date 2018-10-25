package com.example.rmmservices.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "services_cost")
public class ServiceCost {

    @Id
    @SequenceGenerator(name = "services_cost_id_seq", sequenceName = "services_cost_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "services_cost_id_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private Service service;

    private BigDecimal monthlyCost;

    @ManyToOne
    @JoinColumn(name = "device_type_id")
    private DeviceType deviceType;

    public ServiceCost() {
    }

    public ServiceCost(Service service, DeviceType deviceType) {
        this.service = service;
        this.deviceType = deviceType;
    }

    public Long getId() {
        return id;
    }

    public Service getService() {
        return service;
    }

    public BigDecimal getMonthlyCost() {
        return monthlyCost;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }
}
