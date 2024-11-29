package com.demo.android.studybuddy2;

import java.util.ArrayList;
import java.util.List;

public class NotificationSystem {
    public void pushNotification(String title, String message) {
        System.out.println("New notification: " + title + " - " + message);
    }
}

class UserNotification {
    private String name;

    public UserNotification(String name) {
        this.name = name;
    }

    public void update(String title, String message) {
        System.out.println(name + " received notification: " + title + " - " + message);
    }
}
