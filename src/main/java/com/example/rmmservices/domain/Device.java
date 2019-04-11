package com.example.rmmservices.domain;

public interface Device {

    Device register();

    void update();

    void unregister();

    Data data();

    class Data {
        private final Long id;

        private final String systemName;

        private final Long deviceTypeId;

        private final Long customerId;

        public Data(final Long id, final String systemName, final Long deviceTypeId, final Long customerId) {
            this.id = id;
            this.systemName = systemName;
            this.deviceTypeId = deviceTypeId;
            this.customerId = customerId;
        }

        public Long getId() {
            return id;
        }

        public String getSystemName() {
            return systemName;
        }

        public Long getDeviceTypeId() {
            return deviceTypeId;
        }

        public Long getCustomerId() {
            return customerId;
        }
    }
}
