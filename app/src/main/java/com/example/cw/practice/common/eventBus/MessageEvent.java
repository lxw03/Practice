package com.example.cw.practice.common.eventBus;

import java.util.ArrayList;

/**
 * Created by chenwei on 17/2/7.
 */

public class MessageEvent {

    private ArrayList<String> message;

    public MessageEvent(ArrayList<String> message) {
        this.message = message;
    }

    public ArrayList<String> getMessage() {
        return message;
    }

    public void setMessage(ArrayList<String> message) {
        this.message = message;
    }
}
