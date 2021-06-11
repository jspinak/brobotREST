package com.brobot.brobotREST.manageStates;

import java.util.function.BooleanSupplier;

public class StateTransition {

    private int probability = 100;
    private BooleanSupplier booleanSupplier;

    public StateTransition(BooleanSupplier booleanSupplier) {
        this.booleanSupplier = booleanSupplier;
    }

    public StateTransition(BooleanSupplier booleanSupplier, int probability) {
        this.booleanSupplier = booleanSupplier;
        this.probability = probability;
    }
}
