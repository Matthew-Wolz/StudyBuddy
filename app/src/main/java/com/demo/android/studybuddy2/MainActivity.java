package com.demo.android.studybuddy2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.util.Log;

import com.demo.android.studybuddy2.ui.home.HomeFragment;
import com.demo.android.studybuddy2.ui.messages.MessagesFragment;
import com.demo.android.studybuddy2.ui.profile.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.demo.android.studybuddy2.databinding.ActivityMainBinding;
import com.demo.android.studybuddy2.strategy.CompatibilityChecker;
import com.demo.android.studybuddy2.strategy.StudyPreferenceCompatibilityStrategy;
import com.demo.android.studybuddy2.strategy.AvailabilityCompatibilityStrategy;
import com.demo.android.studybuddy2.utils.UserDataReader;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Button loginButton;
    private View loginBackgroundImage;
    private View homeFrag;
    private Button compatibilityResultsButton;
    private ActivityMainBinding binding;
    private TextView compatibilityResultTextView;
    private NotificationSystem notificationSystem;
    private LocationSystem locationSystem;

    // [START declare_auth]
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
//        SharedPreferences sharedPref = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
//        boolean isLoggedIn = sharedPref.getBoolean("isLoggedIn", false);
//        if (!isLoggedIn)
//        {
//            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//            startActivity(intent);
//            finish(); // Close MainActivity
//        }

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        setContentView(R.layout.activity_main);

//        setupFragments();

        //TODO: Remove stuff below this point?? (Excluding commented out code)

        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_profile, R.id.navigation_messages)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        homeFrag = findViewById(R.id.nav_host_fragment_activity_main);
        compatibilityResultsButton = findViewById(R.id.compatibilityResultsButton);
        compatibilityResultTextView = findViewById(R.id.compatibilityResultsTextView);

        if (homeFrag == null || compatibilityResultTextView == null || navView == null) {
            Log.e("MainActivity", "Some UI elements are not properly initialized.");
            return;
        }


//        navView.setVisibility(View.INVISIBLE);
//        homeFrag.setVisibility(View.INVISIBLE);


//        // -------- Compatibility Logic Starts Here --------
//        List<Map<String, String>> usersData = UserDataReader.readUserData(MainActivity.this, "users.csv");
//
//        if (usersData != null && usersData.size() >= 2) {
//            User user1 = new User(
//                    usersData.get(0).get("name"),
//                    usersData.get(0).get("study_preference"),
//                    usersData.get(0).get("availability")
//            );
//            User user2 = new User(
//                    usersData.get(1).get("name"),
//                    usersData.get(1).get("study_preference"),
//                    usersData.get(1).get("availability")
//            );
//
//            CompatibilityChecker checker = new CompatibilityChecker(new StudyPreferenceCompatibilityStrategy());
//            boolean isCompatible = checker.checkCompatibility(user1, user2);
//
//            checker.setStrategy(new AvailabilityCompatibilityStrategy());
//            boolean isAvailable = checker.checkCompatibility(user1, user2);
//
//            String compatibilityMessage = "Study Preference Compatible: " + isCompatible + "\n" +
//                    "Availability Compatible: " + isAvailable;
//            compatibilityResultTextView.setText(compatibilityMessage);
//            compatibilityResultTextView.setVisibility(View.VISIBLE);
//        } else {
//            Log.e("MainActivity", "Insufficient user data");
//        }
//        // -------- Compatibility Logic Ends Here ---------

        // -------- Notification and Location System Initialization ---------
        locationSystem = new LocationSystem();
        notificationSystem = new NotificationSystem();

        locationSystem.addLocation("Central Park", 40.785091, -73.968285);
        notificationSystem.pushNotification("Welcome", "Welcome to StudyBuddy!");

        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser == null){
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }else {
            Log.d("GoogleActivity", currentUser.getDisplayName());
        }

    }

//    Chat Code: (Doesn't work, but may be a better approach to switching fragments)

//    private void setupFragments() {
//        FragmentManager fragmentManager = getSupportFragmentManager();
//
//        if (fragmentManager.findFragmentById(R.id.fragmentContainer) == null) {
//            fragmentManager.beginTransaction()
//                    .replace(R.id.fragmentContainer, new HomeFragment())
//                    .commit();
//        }
//
//        setupBottomNavigation();
//    }
//
//    private void setupBottomNavigation() {
//        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view);
//
//        bottomNavigationView.setOnItemSelectedListener(item -> {
//            Fragment selectedFragment;
//
//            switch (item.getItemId()) {
//                case R.id.navigation_home:
//                    selectedFragment = new HomeFragment();
//                    break;
//                case R.id.navigation_profile:
//                    selectedFragment = new ProfileFragment();
//                    break;
//                case R.id.navigation_messages:
//                default:
//                    selectedFragment = new MessagesFragment();
//                    break;
//            }
//
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.fragmentContainer, selectedFragment)
//                    .commit();
//
//            return true;
//        });
//
//        bottomNavigationView.setSelectedItemId(R.id.nav_home);
//    }
}
