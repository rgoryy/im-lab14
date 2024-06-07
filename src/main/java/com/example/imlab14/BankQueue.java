package com.example.imlab14;

import javafx.scene.control.Label;

import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;

public class BankQueue {
    Queue<Customer> queue = new LinkedBlockingQueue<>();

    public void acceptCustomer(Customer customer) {
        queue.add(customer);
    }

    public Customer pollCustomer() {
        return queue.poll();
    }

}
