package com.demo.android.studybuddy2.ui.profile;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.demo.android.studybuddy2.R;
import com.demo.android.studybuddy2.databinding.FragmentProfileBinding;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileFragment extends Fragment {

    private static final String TAG = "ProfileFragment";
    private FirebaseAuth mauth;

    private FragmentProfileBinding binding;

    private EditText nameField, availabilityField, preferencesField;
    private Button updateProfileButton;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        Log.d(TAG, "Looking at Profile Tab");

        ProfileViewModel profileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        mauth = FirebaseAuth.getInstance();

        Button logoutButton = root.findViewById(R.id.button_logout);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logOut();
            }
        });

//        final TextView textView = binding.textProfile;
//        profileViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        nameField = binding.inputName;
        availabilityField = binding.inputAvailability;
        preferencesField = binding.inputPreferences;

        updateProfileButton = binding.inputSubmitButton;
        updateProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: update Firebase values based on EditText fields

                // This really should pop up unless we get confirmation from Firebase...
                Toast.makeText(getContext(), "Profile Updated!", Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }

    public void logOut(){
        mauth.signOut();
        Toast.makeText(getActivity(), "Logged out successfully!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}