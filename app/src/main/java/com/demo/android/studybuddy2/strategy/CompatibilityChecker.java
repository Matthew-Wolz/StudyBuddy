package com.demo.android.studybuddy2.strategy;

import com.demo.android.studybuddy2.User;

public class CompatibilityChecker implements CompatibilityStrategy {

    @Override
    public double calculateCompatibility(User user1, User user2) {
        int score = 100; // Start with a score of 100

        // Compare "Struggling With" field
        String strugglingWith1 = user1.weaknesses;
        String strugglingWith2 = user2.weaknesses;

        if (strugglingWith1 == null || strugglingWith2 == null) {
            score -= 10; // Deduct points if any "Struggling With" field is null
        } else if (!strugglingWith1.equalsIgnoreCase(strugglingWith2)) {
            score -= 10; // Deduct points for non-matching struggles
        }

        // Compare "Availability"
        String availability1 = user1.availability;
        String availability2 = user2.availability;

        if (availability1 == null || availability2 == null) {
            score -= 15; // Deduct points if any "Availability" field is null
        } else if (!hasOverlap(availability1, availability2)) {
            score -= 15; // Deduct points for non-matching availability
        }

        // Compare "Major(s)"
        String major1 = user1.major;
        String major2 = user2.major;

        if (major1 == null || major2 == null) {
            score -= 5; // Deduct points if any "Major(s)" field is null
        } else if (!hasOverlap(major1, major2)) {
            score -= 5; // Deduct points for non-matching majors
        }

        // Compare "Grade"
        String grade1 = user1.grade;
        String grade2 = user2.grade;

        if (grade1 == null || grade2 == null) {
            score -= 5; // Deduct points if any "Grade" field is null
        } else if (!grade1.equalsIgnoreCase(grade2)) {
            score -= 5; // Deduct points for non-matching grades
        }

        // Compare "Confident With"
        String confidentWith1 = user1.strengths;
        String confidentWith2 = user2.strengths;

        if (confidentWith1 == null || confidentWith2 == null) {
            score -= 10; // Deduct points if any "Confident With" field is null
        } else if (!hasOverlap(confidentWith1, confidentWith2)) {
            score -= 10; // Deduct points for non-matching confidence
        }

        return Math.max(score, 0); // Ensure the score does not go below 0
    }

    // Helper method to check if there is overlap in availability slots or other comma-separated fields
    private boolean hasOverlap(String field1, String field2) {
        for (String slot : field1.split(", ")) {
            if (field2.contains(slot)) {
                return true; // Overlap found
            }
        }
        return false;
    }

    // Helper method to check mutual interest in subjects
    private boolean hasMutualInterest(String subjects1, String subjects2) {
        if (subjects1 == null || subjects2 == null) return false; // Ensure fields are not null
        for (String subject : subjects1.split(", ")) {
            if (subjects2.contains(subject)) {
                return true; // Mutual interest found
            }
        }
        return false;
    }

    @Override
    public boolean checkCompatibility(User user1, User user2) {
        double compatibilityScore = calculateCompatibility(user1, user2);
        return compatibilityScore >= 75; // Return true if compatibility score is above or equal to 75
    }
}
