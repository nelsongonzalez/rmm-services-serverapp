package com.example.rmmservices.service.bean;

import io.swagger.annotations.ApiModel;

import java.math.BigDecimal;
import java.util.List;

@ApiModel(description = "Monthly bill")
public class MonthlyBillQuery {

    private final BigDecimal output;

    private final List<CostPerService> explanation;

    public MonthlyBillQuery(BigDecimal output, List<CostPerService> explanation) {
        this.output = output;
        this.explanation = explanation;
    }

    public BigDecimal getOutput() {
        return output;
    }

    public List<CostPerService> getExplanation() {
        return explanation;
    }

    public static class CostPerService {

        private final String serviceName;

        private final BigDecimal cost;

        public CostPerService(String serviceName, BigDecimal cost) {
            this.serviceName = serviceName;
            this.cost = cost;
        }

        public String getServiceName() {
            return serviceName;
        }

        public BigDecimal getCost() {
            return cost;
        }
    }
}
