package com.assignment.individual.settlelah.activity.Model;


public class Member {

    private long mId;
    private String mName;
    private double totalAmount;
    private long eventId;
    private double individualShare;

    public Member() {
    }

    public Member(String FullName, int eventId) {
        this.mName = FullName;
        this.individualShare = individualShare;
        this.eventId = eventId;
        this.totalAmount = -1;
    }

    public long getId() {
        return mId;
    }

    public void setId(long mId) {
        this.mId = mId;
    }

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String FullName) {
        this.mName = FullName;
    }

    public double getAmount() {
        return totalAmount;
    }

    public void setAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getIndividualShare() {
        return individualShare;
    }

    public void setIndividualShare(double individualShare) {
        this.individualShare = individualShare;
    }
}
