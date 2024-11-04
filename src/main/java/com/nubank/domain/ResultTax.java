package com.nubank.domain;

public class ResultTax {
    private final Double tax;

    public ResultTax(Double tax) {
        this.tax = tax;
    }

    public Double getTax() {
        return tax;
    }

    @Override
    public String toString() {
        return "Result{" +
                "tax=" + tax +
                '}';
    }
}
