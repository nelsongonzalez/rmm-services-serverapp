package com.example.rmmservices.repository;

import com.example.rmmservices.repository.AccountServiceEntries.AccountServiceEntry;
import com.example.rmmservices.repository.ServiceCostEntries.ServiceCostEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Repository
public interface ServiceEntries extends JpaRepository<ServiceEntries.ServiceEntry, Long> {

    @Entity
    @Table(name = "services")
    class ServiceEntry {

        @Id
        @SequenceGenerator(name = "devices_id_seq", sequenceName = "devices_id_seq", allocationSize = 1)
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "devices_id_seq")
        private Long id;

        private String name;

        private String description;

        @OneToMany(mappedBy = "service")
        private List<AccountServiceEntry> accountServices;

        @OneToMany(mappedBy = "service")
        private List<ServiceCostEntry> serviceCosts;

        public ServiceEntry() {
        }

        public ServiceEntry(final Long id) {
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

        public List<AccountServiceEntry> getAccountServices() {
            return accountServices;
        }

        public List<ServiceCostEntry> getServiceCosts() {
            return serviceCosts;
        }
    }
}
