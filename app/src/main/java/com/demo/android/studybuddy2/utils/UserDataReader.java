package com.demo.android.studybuddy2.utils;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class UserDataReader {

    // Method to read CSV file from assets
    public static List<Map<String, String>> readUserData(Context context, String filePath) {
        // Capture users into a list of maps
        List<Map<String, String>> users = new ArrayList<>();

        // Use try-catch to ensure the file is properly closed
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(context.getAssets().open(filePath)))) {

            // Read the header line
            String headerLine = reader.readLine();
            if (headerLine == null) {
                Log.e("UserDataReader", "CSV file is empty.");
                return users; // Return an empty list
            }

            // Split the header line into keys for the map
            String[] keys = parseCsvLine(headerLine);

            // Process each subsequent line in the CSV file
            String line;
            while ((line = reader.readLine()) != null) {

                // Split the line into values
                String[] values = parseCsvLine(line);

                // Check for mismatch between header keys and values
                if (values.length != keys.length) {
                    Log.e("UserDataReader", "Mismatch between header and data.");
                    continue; // Skip invalid lines
                }

                // Create a map for the current user's data
                Map<String, String> user = new HashMap<>();
                for (int i = 0; i < keys.length; i++) {
                    user.put(keys[i].trim(), values[i].trim());
                }

                // Add user's data to the list
                users.add(user);
            }

        }
        // Handle exceptions during file reading
        catch (IOException e) {
            Log.e("UserDataReader", "Error reading CSV file: " + e.getMessage());
        }

        // Return the list of users
        return users;
    }

    // Parse a line of CSV data
    private static String[] parseCsvLine(String line) {
        // Store values from the line
        List<String> values = new ArrayList<>();
        boolean inQuotes = false; // Track whether the current position is inside quotes
        StringBuilder currentValue = new StringBuilder(); // Collect characters for the current value

        // Iterate over each character in the line
        for (int i = 0; i < line.length(); i++) {
            char currentChar = line.charAt(i);

            if (currentChar == '"') {
                // Handle quotes: toggle the inQuotes flag
                if (inQuotes && i + 1 < line.length() && line.charAt(i + 1) == '"') {
                    // Handle escaped quotes ("" within quoted values)
                    currentValue.append('"');
                    i++; // Skip the next quote
                } else {
                    inQuotes = !inQuotes; // Toggle inQuotes status
                }
            } else if (currentChar == ',' && !inQuotes) {
                // If it's a comma outside of quotes, finalize the current value
                values.add(currentValue.toString().trim());
                currentValue.setLength(0); // Reset currentValue for the next value
            } else {
                // Add the current character to the value
                currentValue.append(currentChar);
            }
        }

        // Add the last value after the final comma (if any)
        values.add(currentValue.toString().trim());

        // Return the values as an array
        return values.toArray(new String[0]);
    }
}
