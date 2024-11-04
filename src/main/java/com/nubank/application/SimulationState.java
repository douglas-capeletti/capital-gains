package com.nubank.application;

import com.nubank.domain.ResultTax;

import java.util.ArrayList;
import java.util.List;

public class SimulationState {
    // TODO: move to a config.properties file
    public final double TAX_RATE = 0.2;
    public final double NO_TAX_LIMIT_VALUE = 20_000;

    private Double averagePrice;
    private Long stockQuantity;
    private Double balance;
    private final List<ResultTax> resultTaxes;

    public SimulationState() {
        this.averagePrice = 0D;
        this.stockQuantity = 0L;
        this.balance = 0D;
        this.resultTaxes = new ArrayList<>();
    }

    public Double getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(Double averagePrice) {
        this.averagePrice = averagePrice;
    }

    public Long getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Long stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public List<ResultTax> getResultTaxes() {
        return resultTaxes;
    }
}
