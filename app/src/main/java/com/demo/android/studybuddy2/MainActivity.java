package com.demo.android.studybuddy2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.demo.android.studybuddy2.databinding.ActivityMainBinding;
import com.demo.android.studybuddy2.strategy.CompatibilityChecker;
import com.demo.android.studybuddy2.strategy.StudyPreferenceCompatibilityStrategy;
import com.demo.android.studybuddy2.strategy.AvailabilityCompatibilityStrategy;
import com.demo.android.studybuddy2.utils.UserDataReader;

import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Button loginButton;
    private View loginBackgroundImage;
    private View homeFrag;
    private ActivityMainBinding binding;
    private TextView compatibilityResultTextView; // Add TextView for compatibility result

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_profile, R.id.navigation_messages)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        loginButton = findViewById(R.id.loginButton);
        loginBackgroundImage = findViewById(R.id.loginBackground);
        homeFrag = findViewById(R.id.navigation_home);

        // Initialize the TextView for displaying compatibility result
        compatibilityResultTextView = findViewById(R.id.compatibilityResultsTextView);

        navView.setVisibility(View.INVISIBLE);
        homeFrag.setVisibility(View.INVISIBLE);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Actual login process here?

                loginButton.setVisibility(View.INVISIBLE);
                loginBackgroundImage.setVisibility(View.INVISIBLE);
                navView.setVisibility(View.VISIBLE);
                homeFrag.setVisibility(View.VISIBLE);
            }
        });

        // -------- Compatibility Logic Starts Here --------
        // Read user data from the CSV file
        List<Map<String, String>> usersData = UserDataReader.readUserData(MainActivity.this, "users.csv");

        // Example of converting Map<String, String> to User objects (adjust according to your CSV structure)
        User user1 = new User(
                usersData.get(0).get("name"),
                usersData.get(0).get("study_preference"),
                usersData.get(0).get("availability")
        );
        User user2 = new User(
                usersData.get(1).get("name"),
                usersData.get(1).get("study_preference"),
                usersData.get(1).get("availability")
        );

        // Check study preference compatibility
        CompatibilityChecker checker = new CompatibilityChecker(new StudyPreferenceCompatibilityStrategy());
        boolean isCompatible = checker.checkCompatibility(user1, user2);

        // Switch to availability compatibility
        checker.setStrategy(new AvailabilityCompatibilityStrategy());
        boolean isAvailable = checker.checkCompatibility(user1, user2);

        // Display results in the UI
        String compatibilityMessage = "Study Preference Compatible: " + isCompatible + "\n" +
                "Availability Compatible: " + isAvailable;
        compatibilityResultTextView.setText(compatibilityMessage);
        compatibilityResultTextView.setVisibility(View.VISIBLE);
        // -------- Compatibility Logic Ends Here --------
    }
}
