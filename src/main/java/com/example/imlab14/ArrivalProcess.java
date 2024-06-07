package com.example.imlab14;

import java.util.Random;

public class ArrivalProcess extends Agent {

    private Random random = new Random();
    private double nextArrivalTime = 0;
    public double lambda = 2;
    private Bank bank;

    public ArrivalProcess(Bank bank) {
        this.bank = bank;
        nextArrivalTime = simulateInterArrivalTime();
    }

    private double simulateInterArrivalTime() {
        return -Math.log(random.nextDouble()) / lambda;
    }

    @Override
    public double getNextEventTime() {
        return nextArrivalTime;
    }

    @Override
    public void processEvent() {
        Customer customer = new Customer();
        bank.customerArrived(customer);
        nextArrivalTime += simulateInterArrivalTime();

        bank.updateLabels();
    }
}
