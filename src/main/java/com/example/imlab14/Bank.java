package com.example.imlab14;

import javafx.scene.control.Label;

public class Bank extends Agent {

    private Service service;

    private BankQueue bankQueue = new BankQueue();

    private Label queue;

    private Label operators;

    public Bank(Service service, Label queue, Label operators) {
        this.service = service;
        this.queue = queue;
        this.operators = operators;
    }

    @Override
    public double getNextEventTime() {
        return service.getNextEventTime();
    }

    @Override
    public void processEvent() {
        service.processEvent();
        updateLabels();
    }

    public void updateLabels() {
        queue.setText("\uD83D\uDE42".repeat(bankQueue.queue.size()));

        int countOfFreeOperators = service.getFreeOperatorsSize();
        operators.setText("\uD83D\uDE10".repeat(countOfFreeOperators) +
                "\uD83D\uDE42".repeat(service.getOperators().size()
                        - countOfFreeOperators
                ));
    }

    public void customerArrived(Customer customer) {
        if (service.hasFreeOperator()) {
            Customer firstQueueCustomer = bankQueue.pollCustomer();
            if (firstQueueCustomer != null) {
                service.acceptCustomer(firstQueueCustomer);
            } else {
                service.acceptCustomer(customer);
            }
        } else {
            bankQueue.acceptCustomer(customer);
        }
        updateLabels();
    }
}
