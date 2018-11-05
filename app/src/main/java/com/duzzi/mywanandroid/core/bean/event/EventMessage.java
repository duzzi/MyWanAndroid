package com.duzzi.mywanandroid.core.bean.event;

public class EventMessage {

    public static final int EVENT_REFRESH_USER_INFO = 0x01;
    public static final int EVENT_LOGOUT = 0x02;

    private int eventType;

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public EventMessage(int eventType) {
        this.eventType = eventType;
    }
}