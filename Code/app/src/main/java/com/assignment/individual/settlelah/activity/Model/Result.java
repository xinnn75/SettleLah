package com.assignment.individual.settlelah.activity.Model;

import androidx.recyclerview.widget.RecyclerView;

public class Result {
    private long id;
    private double totalAmount;
    private int numPeople;
    private double individualShare;

    public Result(long id, double totalAmount, int numPeople, double individualShare) {
        this.id = id;
        this.totalAmount = totalAmount;
        this.numPeople = numPeople;
        this.individualShare = individualShare;
    }

    public long getId() {
        return id;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public int getNumPeople() {
        return numPeople;
    }

    public double getIndividualShare() {
        return individualShare;
    }
}
