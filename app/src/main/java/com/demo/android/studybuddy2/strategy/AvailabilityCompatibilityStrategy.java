package com.demo.android.studybuddy2.strategy;
import com.demo.android.studybuddy2.User;

public class AvailabilityCompatibilityStrategy implements CompatibilityStrategy {
    @Override
    public boolean checkCompatibility(User user1, User user2) {
        // Check compatibility based on availability
        return user1.getAvailability().equals(user2.getAvailability());
    }
}
