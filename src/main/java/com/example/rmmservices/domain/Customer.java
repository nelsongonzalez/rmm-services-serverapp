package com.example.rmmservices.domain;

import io.swagger.annotations.ApiModel;

import javax.money.MonetaryAmount;
import java.util.List;

public interface Customer {

    MonthlyBillData billData();

    @ApiModel(description = "Monthly bill")
    class MonthlyBillData {

        private final MonetaryAmount output;

        private final List<ServiceCostData> explanation;

        public MonthlyBillData(final MonetaryAmount output, final List<ServiceCostData> explanation) {
            this.output = output;
            this.explanation = explanation;
        }

        public MonetaryAmount getOutput() {
            return output;
        }

        public List<ServiceCostData> getExplanation() {
            return explanation;
        }

        public static class ServiceCostData {

            private final String serviceName;

            private final MonetaryAmount cost;

            public ServiceCostData(final String serviceName, final MonetaryAmount cost) {
                this.serviceName = serviceName;
                this.cost = cost;
            }

            public String getServiceName() {
                return serviceName;
            }

            public MonetaryAmount getCost() {
                return cost;
            }
        }
    }
}
