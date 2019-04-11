package com.example.rmmservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

@Repository
public interface DeviceTypeEntries extends JpaRepository<DeviceTypeEntries.DeviceTypeEntry, Long> {

    @Entity
    @Table(name = "device_types")
    class DeviceTypeEntry {

        @Id
        @SequenceGenerator(name = "device_types_id_seq", sequenceName = "device_types_id_seq", allocationSize = 1)
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "device_types_id_seq")
        private Long id;

        private String name;

        public DeviceTypeEntry() {
        }

        public DeviceTypeEntry(final Long id) {
            this.id = id;
        }

        public Long getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }
}
