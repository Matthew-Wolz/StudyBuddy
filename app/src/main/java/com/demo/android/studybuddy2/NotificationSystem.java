package com.demo.android.studybuddy2;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class NotificationSystem {
    public void pushNotification(String title, String message) {
    }
}

class UserNotification {
    private String name;

    public UserNotification(String name) {
        this.name = name;
    }

    public void update(String title, String message) {
//        System.out.println(name + " received notification: " + title + " - " + message);
    }
}
