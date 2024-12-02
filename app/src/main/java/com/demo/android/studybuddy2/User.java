package com.demo.android.studybuddy2;

import java.util.Objects;

public class User {
    public String name;
    public String strengths;
    public String availability;
    public String weaknesses;
    public String grade;

    // needed for database
    public User(){}

    // Constructor
    public User(String name, String strengths, String availability, String weaknesses, String grade) {
        this.name = name;
        this.strengths = strengths;
        this.availability = availability;
        this.weaknesses = weaknesses;
        this.grade = grade;
    }

    // Getter methods
    public String getName() {
        return name;
    }

    public String getStrengths() {
        return strengths;
    }

    public String getAvailability() {
        return availability;
    }

    public String getWeaknesses()
    {
        return weaknesses;
    }

    public String getGrade()
    {
        return grade;
    }


    // Optional setter methods for flexibility
    public void setName(String name) {
        this.name = name;
    }

    public void setstrengths(String strengths) {
        this.strengths = strengths;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    // Optional toString() method for debugging/display
    @Override
    public String toString() {
        return "Name: " + name + ", Study Preference: " + strengths + ", Availability: " + availability;
    }

    // Optional equals() and hashCode() methods for comparing Users
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User user = (User) obj;
        return name.equals(user.name) &&
                strengths.equals(user.strengths) &&
                availability.equals(user.availability);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, strengths, availability);
    }
}
