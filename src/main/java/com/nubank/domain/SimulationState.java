package com.nubank.domain;

import java.util.ArrayList;
import java.util.List;

public class SimulationState {

    private final Double averagePrice;
    private final Long stockQuantity;
    private final Double balance;
    private final List<ResultTax> resultTaxes;

    public SimulationState() {
        this.averagePrice = 0D;
        this.stockQuantity = 0L;
        this.balance = 0D;
        this.resultTaxes = new ArrayList<>();
    }

    public SimulationState(List<ResultTax> resultTaxes, Double balance, Long stockQuantity, Double averagePrice) {
        this.resultTaxes = resultTaxes;
        this.balance = balance;
        this.stockQuantity = stockQuantity;
        this.averagePrice = averagePrice;
    }

    public Double getAveragePrice() {
        return averagePrice;
    }

    public Long getStockQuantity() {
        return stockQuantity;
    }

    public Double getBalance() {
        return balance;
    }

    public List<ResultTax> getResultTaxes() {
        return resultTaxes;
    }

    @Override
    public String toString() {
        return "SimulationState{" +
                "resultTaxes=" + resultTaxes +
                ", balance=" + balance +
                ", stockQuantity=" + stockQuantity +
                ", averagePrice=" + averagePrice +
                '}';
    }
}
