package com.nubank.domain;

import com.nubank.application.SimulationState;

public interface Operation {
    void run(SimulationState state, Transaction transaction);
}
