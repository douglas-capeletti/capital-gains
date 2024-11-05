package com.nubank.application;

import com.nubank.domain.ResultTax;
import com.nubank.domain.Simulation;

import java.util.List;

public interface UserInterface<X, Y> {
    List<Simulation> handleInput(X stdin);
    Y handleOutput(List<ResultTax> resultTaxes);
}
