package com.demo.android.studybuddy2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.util.Log;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.demo.android.studybuddy2.databinding.ActivityMainBinding;
//import com.demo.android.studybuddy2.strategy.StudyPreferenceCompatibilityStrategy;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

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
