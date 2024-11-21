package com.demo.android.studybuddy2.strategy;
import com.demo.android.studybuddy2.User;

public class StudyPreferenceCompatibilityStrategy implements CompatibilityStrategy {
    @Override
    public boolean checkCompatibility(User user1, User user2) {
        // Check compatibility based on study preferences
        return user1.getStudyPreference().equals(user2.getStudyPreference());
    }
}
