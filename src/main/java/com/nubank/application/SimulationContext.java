package com.nubank.application;

import com.nubank.domain.SimulationState;

public class SimulationContext {
    // TODO: env variables and config properties can go here later
    private SimulationState state;

    public SimulationContext() {
        this.state = new SimulationState();
    }

    public SimulationState getState() {
        return state;
    }

    public void setState(SimulationState state) {
        this.state = state;
    }
}