package com.example.imlab14;

import java.util.Random;

public class Operator {
    private Customer servedCustomer = null;
    private Random random = new Random();
    private double endOfServiceTime = Double.MAX_VALUE;
    private double lambda = 2;

    public boolean isFree() {
        return servedCustomer == null;
    }

    public void acceptCustomer(Customer customer) {
        if (isFree()) {
            servedCustomer = customer;
            endOfServiceTime = Model.getTime() + simulateServiceTime();
        }
    }

    private double simulateServiceTime() {
        return -Math.log(random.nextDouble()) / lambda;
    }

    public double getEndOfServiceTime() {
        return endOfServiceTime;
    }

    public void finishServingCustomer() {
        servedCustomer = null;
        endOfServiceTime = Double.MAX_VALUE;
    }

    @Override
    public String toString() {
        return "Operator{" +
                "servedCustomer=" + servedCustomer +
                ", endOfServiceTime=" + endOfServiceTime +
                '}';
    }
}
