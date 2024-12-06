package com.demo.android.studybuddy2.strategy;
import com.demo.android.studybuddy2.User;

public interface CompatibilityStrategy {
    double calculateCompatibility(User user1, User user2);
    boolean checkCompatibility(User user1, User user2);
}
