package com.example.rmmservices.domain;

public interface AccountService {

    AccountService register();

    void unregister();

    Data data();

    class Data {

        private final Long id;

        private final Long serviceId;

        private final Long customerId;

        public Data(final Long id, final Long serviceId, final Long customerId) {
            this.id = id;
            this.serviceId = serviceId;
            this.customerId = customerId;
        }

        public Long getId() {
            return id;
        }

        public Long getServiceId() {
            return serviceId;
        }

        public Long getCustomerId() {
            return customerId;
        }
    }
}
