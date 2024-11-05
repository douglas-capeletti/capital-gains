package com.nubank.domain;

import java.util.function.Supplier;

public interface Operation {
    double TAX_RATE = 0.2;
    double NO_TAX_LIMIT_VALUE = 20_000;
    Supplier<SimulationState> run(SimulationState state, Transaction transaction);
}
