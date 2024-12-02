package com.demo.android.studybuddy2.strategy;
import com.demo.android.studybuddy2.User;

public class StrengthsCompatibilityStrategy implements CompatibilityStrategy {
    @Override
    public boolean checkCompatibility(User user1, User user2) {
        // Check compatibility based on study preferences
        return user1.getStrengths().equals(user2.getStrengths());
    }
}
