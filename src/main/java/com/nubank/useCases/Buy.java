package com.nubank.useCases;

import com.nubank.application.SimulationState;
import com.nubank.domain.Operation;
import com.nubank.domain.ResultTax;
import com.nubank.domain.Transaction;

public class Buy implements Operation {
    @Override
    public void run(SimulationState state, Transaction transaction) {
        // nova-média-ponderada = (
        // (quantidade-de-ações-atual * média-ponderada-atual) +
        // (quantidade-de-ações-compradas * valor-de-compra)) /
        // (quantidade-de-ações-atual + quantidade-de-ações-compradas).

        var currentPrice = state.getAveragePrice() * state.getStockQuantity();
        var newPrice = transaction.getUnitCost() * transaction.getQuantity();
        var newStockQuantity = state.getStockQuantity() + transaction.getQuantity();
        var newAveragePrice = currentPrice + newPrice;

        if (newStockQuantity > 0) {
            newAveragePrice = newAveragePrice / newStockQuantity;
        }

        state.setAveragePrice(newAveragePrice);
        state.setStockQuantity(newStockQuantity);
        state.getResultTaxes().add(new ResultTax(0.00));
    }
}

