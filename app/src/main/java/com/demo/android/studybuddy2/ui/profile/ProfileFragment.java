package com.demo.android.studybuddy2.ui.profile;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.demo.android.studybuddy2.R;
import com.demo.android.studybuddy2.User;
import com.demo.android.studybuddy2.databinding.FragmentProfileBinding;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import org.w3c.dom.Document;

import java.util.HashMap;
import java.util.Map;

public class ProfileFragment extends Fragment {

    private static final String TAG = "ProfileFragment";
    private FirebaseAuth mauth;

    private FragmentProfileBinding binding;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

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
        FirebaseUser account = mauth.getCurrentUser();

        Button logoutButton = root.findViewById(R.id.button_logout);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logOut();
            }
        });

        nameField = root.findViewById(R.id.inputName);
        availabilityField = root.findViewById(R.id.inputAvailability);
        preferencesField = root.findViewById(R.id.inputPreferences);

        DocumentReference docRef = db.collection("users").document(account.getEmail());
        Log.d("Database", docRef.toString());

        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Map<String, Object> userData = document.getData();

                        String fullName = (String) userData.get("firstAndLast");
//                        String email = (String) userData.get("Email Address");
//                        String grade = (String) userData.get("Grade");
                        String availability = (String) userData.get("availability");
//                        String major = (String) userData.get("Major(s)");
//                        String strugglingWith = (String) userData.get("Struggling With");
                        String confidentWith = (String) userData.get("preferences");

                        nameField.setText(fullName);
                        availabilityField.setText(availability);
                        preferencesField.setText(confidentWith);

                    }else {
                        Log.d("Firestore", "No such document");
                    }
                }else {
                    Log.d("Firestore", "get failed with ", task.getException());
                }
            }
        });

        nameField = binding.inputName;
        availabilityField = binding.inputAvailability;
        preferencesField = binding.inputPreferences;
        updateProfileButton = binding.inputSubmitButton;
        updateProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                FirebaseUser account = mauth.getCurrentUser();

                Map<String, Object> user = new HashMap<>();
//                user.put("firstAndLast", nameField.getText());
                user.put("availability", availabilityField.getText().toString());
                user.put("preferences", preferencesField.getText().toString());

                db.collection("users")
                        .document(account.getEmail())
                        .set(user, SetOptions.merge())
                        .addOnSuccessListener(new OnSuccessListener<Void>() {

                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d(TAG, "Changes Applied ");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error adding document", e);
                            }
                        });
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