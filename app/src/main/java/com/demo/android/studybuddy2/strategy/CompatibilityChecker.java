package com.demo.android.studybuddy2.strategy;

import com.demo.android.studybuddy2.User;

public class CompatibilityChecker implements CompatibilityStrategy {

    @Override
    public double calculateCompatibility(User user1, User user2) {

        //begin the user with a score of 100 and slowly deduct
        int score = 100;

        //compare the "struggling with" field
        String strugglingWith1 = user1.weaknesses;
        String strugglingWith2 = user2.weaknesses;

        if(!strugglingWith1.equalsIgnoreCase(strugglingWith2)) {
            //remove 10 points for mismatching struggling with fields
            score -= 10;
        }

        //compare the "availability" field
        String availability1 = user1.availability;
        String availability2 = user2.availability;

        if(hasOverlap(availability1, availability2)) {
            //remove 10 points for mismatching availability fields
            score -= 15;
        }

        //compare the "majors" field
        String major1 = user1.major;
        String major2 = user2.major;

        if(hasOverlap(major1, major2)) {
            //remove 5 points for mismatching major fields
            score -= 5;
        }

        //compare the "grade" field
        String grade1 = user1.grade;
        String grade2 = user2.grade;

        if(!grade1.equalsIgnoreCase(grade2)) {
            //remove 10 points for mismatching grade fields
            score -= 5;
        }

        //compare the "confident with" field
        String confidentWith1 = user1.strengths;
        String confidentWith2 = user2.strengths;

        if(hasOverlap(confidentWith1, confidentWith2)) {
            //remove 10 points for mismatching grade fields
            score -= 10;
        }

        return score;
    }

    //helper method to check if there is overlap in availability slots or other comma-separated fields
    private boolean hasOverlap(String field1, String field2) {
        for (String slot : field1.split(", ")) {
            if (field2.contains(slot)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean checkCompatibility(User user1, User user2) {
        double compatibilityScore = calculateCompatibility(user1, user2);
        //return true if a user is above a compatibility score of 75
        return compatibilityScore >= 75;
    }
}
