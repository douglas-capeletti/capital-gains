package com.nubank.domain.operations;

import com.nubank.domain.SimulationState;
import com.nubank.domain.Operation;
import com.nubank.domain.ResultTax;
import com.nubank.domain.Transaction;

import java.util.function.Supplier;

public class Buy implements Operation {
    @Override
    public Supplier<SimulationState> run(SimulationState state, Transaction transaction) {
        // nova-média-ponderada = (
        // (quantidade-de-ações-atual * média-ponderada-atual) +
        // (quantidade-de-ações-compradas * valor-de-compra)) /
        // (quantidade-de-ações-atual + quantidade-de-ações-compradas).

        var currentPrice = state.getAveragePrice() * state.getStockQuantity();
        var newPrice = transaction.getUnitCost() * transaction.getQuantity();
        var newStockQuantity = state.getStockQuantity() + transaction.getQuantity();
        var averagePrice = currentPrice + newPrice;

        if (newStockQuantity > 0) {
            averagePrice = averagePrice / newStockQuantity;
        }

        var newAveragePrice = averagePrice;
        state.getResultTaxes().add(new ResultTax(0.00));
        return () -> new SimulationState(
                state.getResultTaxes(),
                state.getBalance(),
                newStockQuantity,
                newAveragePrice
        );
    }
}

