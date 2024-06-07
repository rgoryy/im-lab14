package com.example.imlab14;

import java.util.List;

public class Service extends Agent {
    private List<Operator> operators;
    private double nextEndOfServingCustomerTime = 0;

    public Service(List<Operator> operators) {
        this.operators = operators;
    }

    public void acceptCustomer(Customer customer) {
        Operator freeOperator = findFreeOperator();
        if (freeOperator != null) {
            freeOperator.acceptCustomer(customer);
        }
    }

    public Operator findFreeOperator() {
        for (Operator operator : operators) {
            if (operator.isFree()) {
                return operator;
            }
        }
        return null;
    }

    public boolean hasFreeOperator() {
        Operator freeOperator = findFreeOperator();
        if (freeOperator != null) {
            return findFreeOperator().isFree();
        }
        return false;
    }

    @Override
    public double getNextEventTime() {
        double tMin = Double.MAX_VALUE;
        for (Operator operator : operators) {
            if (operator != null) {
                tMin = Math.min(operator.getEndOfServiceTime(), tMin);
            }
        }
        return tMin;
    }

    @Override
    public void processEvent() {
        if (operators.isEmpty()) {
            return;
        }

        Operator op = null;
        double tMin = Double.MAX_VALUE;
        for (Operator operator : operators) {
            if (operator != null && operator.getEndOfServiceTime() < tMin) {
                tMin = operator.getEndOfServiceTime();
                op = operator;
            }
        }

        if (op != null) {
            op.finishServingCustomer();
        }
    }

    public List<Operator> getOperators() {
        return operators;
    }

    @Override
    public String toString() {
        return "Service{" +
                "operators=" + operators.toString() + '}';
    }


    public int getFreeOperatorsSize() {
        int i = 0;
        for (Operator o: operators) {
            if (o.isFree()) {
                i++;
            }
        }
        return i;
    }

}
