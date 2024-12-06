package com.demo.android.studybuddy2;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class LocationSystem {
    private List<Location> locations;

    public LocationSystem() {
        locations = new ArrayList<>();
    }

    public void addLocation(String name, double latitude, double longitude) {
        Location location = new Location(name, latitude, longitude);
        locations.add(location);
        System.out.println("New Location Added: " + name + " (Lat: " + latitude + ", Long: " + longitude + ")");
    }

    public List<Location> getLocations() {
        return locations;
    }

    class Location {
        private String name;
        private double latitude;
        private double longitude;

        public Location(String name, double latitude, double longitude) {
            this.name = name;
            this.latitude = latitude;
            this.longitude = longitude;
        }

        @NonNull
        @Override
        public String toString() {
            return "Location{" +
                    "name='" + name + '\'' +
                    ", latitude=" + latitude +
                    ", longitude=" + longitude +
                    '}';
        }
    }
}
