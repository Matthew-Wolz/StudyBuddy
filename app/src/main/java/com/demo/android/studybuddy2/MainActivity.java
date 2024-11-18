package com.demo.android.studybuddy2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.demo.android.studybuddy2.ui.home.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.demo.android.studybuddy2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private Button loginButton;
    private View loginBackgroundImage;
    private View homeFrag;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_profile, R.id.navigation_messages)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);


        loginButton = findViewById(R.id.loginButton);
        loginBackgroundImage = findViewById(R.id.loginBackground);
        homeFrag = findViewById(R.id.navigation_home);

//        navView = findViewById(R.id.nav_host_fragment_activity_main);
        navView.setVisibility(View.INVISIBLE);
        homeFrag.setVisibility((View.INVISIBLE));

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Actual login process here?

                // Hide the button and background, show BottomNavigationView
                loginButton.setVisibility(v.INVISIBLE);
                loginBackgroundImage.setVisibility((v.INVISIBLE)); //should not be transparent....
                navView.setVisibility(View.VISIBLE);
                homeFrag.setVisibility(View.VISIBLE); //doesn't work???
            }
        });
    }

}