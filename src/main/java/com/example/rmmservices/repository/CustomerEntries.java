package com.example.rmmservices.repository;

import com.example.rmmservices.repository.AccountServiceEntries.AccountServiceEntry;
import com.example.rmmservices.repository.DeviceEntries.DeviceEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Repository
public interface CustomerEntries extends JpaRepository<CustomerEntries.CustomerEntry, Long> {

    @Entity
    @Table(name = "customers")
    class CustomerEntry {

        @Id
        @SequenceGenerator(name = "customers_id_seq", sequenceName = "customers_id_seq", allocationSize = 1)
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customers_id_seq")
        private Long id;

        private String name;

        @OneToMany(mappedBy = "owner")
        private List<AccountServiceEntry> accountServices;

        @OneToMany(mappedBy = "owner")
        private List<DeviceEntry> devices;

        public CustomerEntry() {
        }

        public CustomerEntry(final Long id) {
            this.id = id;
        }

        public Long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public List<AccountServiceEntry> getAccountServices() {
            return accountServices;
        }

        public List<DeviceEntry> getDevices() {
            return devices;
        }
    }
}
