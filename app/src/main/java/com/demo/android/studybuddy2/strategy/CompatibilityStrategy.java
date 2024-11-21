package com.demo.android.studybuddy2.strategy;
import com.demo.android.studybuddy2.User;

public interface CompatibilityStrategy {
    boolean checkCompatibility(User user1, User user2);
}
