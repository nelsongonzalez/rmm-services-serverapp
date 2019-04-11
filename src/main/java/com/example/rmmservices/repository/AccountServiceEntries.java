package com.example.rmmservices.repository;

import com.example.rmmservices.repository.CustomerEntries.CustomerEntry;
import com.example.rmmservices.repository.ServiceEntries.ServiceEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

@Repository
public interface AccountServiceEntries extends JpaRepository<AccountServiceEntries.AccountServiceEntry, Long> {

    @Entity
    @Table(name = "account_services")
    class AccountServiceEntry {

        @Id
        @SequenceGenerator(name = "account_services_id_seq", sequenceName = "account_services_id_seq", allocationSize = 1)
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_services_id_seq")
        private Long id;

        @ManyToOne
        @JoinColumn(name = "service_id")
        private ServiceEntry service;

        @ManyToOne
        @JoinColumn(name = "customer_id")
        private CustomerEntry owner;

        public AccountServiceEntry() {
        }

        public AccountServiceEntry(final CustomerEntry owner) {
            this.owner = owner;
        }

        public AccountServiceEntry(final ServiceEntry service, final CustomerEntry owner) {
            this.service = service;
            this.owner = owner;
        }

        public Long getId() {
            return id;
        }

        public ServiceEntry getService() {
            return service;
        }

        public CustomerEntry getOwner() {
            return owner;
        }
    }
}
