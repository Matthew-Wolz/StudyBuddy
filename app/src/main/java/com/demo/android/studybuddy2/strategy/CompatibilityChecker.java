package com.demo.android.studybuddy2.strategy;
import com.demo.android.studybuddy2.User;


public class CompatibilityChecker {
    private CompatibilityStrategy strategy;

    public CompatibilityChecker(CompatibilityStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(CompatibilityStrategy strategy) {
        this.strategy = strategy;
    }

    public boolean checkCompatibility(User user1, User user2) {
        return strategy.checkCompatibility(user1, user2);
    }
}
