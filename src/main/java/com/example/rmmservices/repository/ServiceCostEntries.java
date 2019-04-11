package com.example.rmmservices.repository;

import com.example.rmmservices.repository.DeviceTypeEntries.DeviceTypeEntry;
import com.example.rmmservices.repository.ServiceEntries.ServiceEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.math.BigDecimal;

@Repository
public interface ServiceCostEntries extends JpaRepository<ServiceCostEntries.ServiceCostEntry, Long> {

    @Entity
    @Table(name = "services_cost")
    class ServiceCostEntry {

        @Id
        @SequenceGenerator(name = "services_cost_id_seq", sequenceName = "services_cost_id_seq", allocationSize = 1)
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "services_cost_id_seq")
        private Long id;

        @ManyToOne
        @JoinColumn(name = "service_id")
        private ServiceEntry service;

        private BigDecimal monthlyCost;

        @ManyToOne
        @JoinColumn(name = "device_type_id")
        private DeviceTypeEntry deviceType;

        public ServiceCostEntry() {
        }

        public ServiceCostEntry(final ServiceEntry service, final DeviceTypeEntry deviceType) {
            this.service = service;
            this.deviceType = deviceType;
        }

        public Long getId() {
            return id;
        }

        public ServiceEntry getService() {
            return service;
        }

        public BigDecimal getMonthlyCost() {
            return monthlyCost;
        }

        public DeviceTypeEntry getDeviceType() {
            return deviceType;
        }
    }
}
