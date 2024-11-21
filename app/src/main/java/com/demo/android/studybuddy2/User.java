package com.demo.android.studybuddy2;

import java.util.Objects;

public class User {
    private String name;
    private String studyPreference;
    private String availability;

    // Constructor
    public User(String name, String studyPreference, String availability) {
        this.name = name;
        this.studyPreference = studyPreference;
        this.availability = availability;
    }

    // Getter methods
    public String getName() {
        return name;
    }

    public String getStudyPreference() {
        return studyPreference;
    }

    public String getAvailability() {
        return availability;
    }

    // Optional setter methods for flexibility
    public void setName(String name) {
        this.name = name;
    }

    public void setStudyPreference(String studyPreference) {
        this.studyPreference = studyPreference;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    // Optional toString() method for debugging/display
    @Override
    public String toString() {
        return "Name: " + name + ", Study Preference: " + studyPreference + ", Availability: " + availability;
    }

    // Optional equals() and hashCode() methods for comparing Users
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User user = (User) obj;
        return name.equals(user.name) &&
                studyPreference.equals(user.studyPreference) &&
                availability.equals(user.availability);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, studyPreference, availability);
    }
}
