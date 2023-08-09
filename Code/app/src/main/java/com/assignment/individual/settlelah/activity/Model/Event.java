package com.assignment.individual.settlelah.activity.Model;

public class Event {

    private long eventId;
    private String eventName;

    public Event() {
    }

    public Event(String eventName) {
        this.eventName = eventName;
    }

    public long getId() {
        return eventId;
    }

    public void setId(long mId) {
        this.eventId = mId;
    }

    public String getName() {
        return eventName;
    }

    public void setName(String FullName) {
        this.eventName = FullName;
    }

}
