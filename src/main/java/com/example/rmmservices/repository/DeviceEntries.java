package com.example.rmmservices.repository;

import com.example.rmmservices.repository.CustomerEntries.CustomerEntry;
import com.example.rmmservices.repository.DeviceTypeEntries.DeviceTypeEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

@Repository
public interface DeviceEntries extends JpaRepository<DeviceEntries.DeviceEntry, Long> {

    @Entity
    @Table(name = "devices")
    class DeviceEntry {

        @Id
        @SequenceGenerator(name = "devices_id_seq", sequenceName = "devices_id_seq", allocationSize = 1)
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "devices_id_seq")
        private Long id;

        private String systemName;

        @ManyToOne
        @JoinColumn(name = "device_type_id")
        private DeviceTypeEntry type;

        @ManyToOne
        @JoinColumn(name = "customer_id")
        private CustomerEntry owner;

        public DeviceEntry() {
        }

        public DeviceEntry(final CustomerEntry owner) {
            this.owner = owner;
        }

        public DeviceEntry(final String systemName, final DeviceTypeEntry deviceType, final CustomerEntry owner) {
            this.systemName = systemName;
            this.type = deviceType;
            this.owner = owner;
        }

        public DeviceEntry(final Long id, final String systemName, final DeviceTypeEntry deviceType, final CustomerEntry owner) {
            this.id = id;
            this.systemName = systemName;
            this.type = deviceType;
            this.owner = owner;
        }

        public DeviceEntry(final Long id, final CustomerEntry owner) {
            this.id = id;
            this.owner = owner;
        }

        public Long getId() {
            return id;
        }

        public String getSystemName() {
            return systemName;
        }

        public DeviceTypeEntry getType() {
            return type;
        }

        public CustomerEntry getOwner() {
            return owner;
        }
    }
}
