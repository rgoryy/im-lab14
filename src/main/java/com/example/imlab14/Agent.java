package com.example.imlab14;

public abstract class Agent {

    public double getNextEventTime() {
        return Double.MAX_VALUE;
    }

    public void processEvent() {
        throw new RuntimeException("Agent has no event exception");
    }

}
