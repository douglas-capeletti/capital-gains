package com.nubank.domain;

import java.util.List;

public class Simulation {
    private final List<Transaction> transactions;

    public Simulation(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<Transaction> getOperations() {
        return transactions;
    }

    @Override
    public String toString() {
        return "Simulation{operations=" + transactions + '}';
    }
}
