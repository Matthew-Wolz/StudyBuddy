package com.demo.android.studybuddy2.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.android.studybuddy2.CardAdapter;
import com.demo.android.studybuddy2.R;
import com.demo.android.studybuddy2.User;
import com.demo.android.studybuddy2.databinding.FragmentHomeBinding;
import com.demo.android.studybuddy2.strategy.CompatibilityChecker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";
    private RecyclerView recyclerView;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    private FirebaseAuth mauth;

    private FragmentHomeBinding binding;
    User currentUser = new User();



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "Looking at Home Tab");

        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = root.findViewById(R.id.recyclerView);

        // Set Layout Manager
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        addFirebaseEventListener(myRef);
        readUserData();

        return root;
    }

    public void readUserData(){

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        mauth = FirebaseAuth.getInstance();
        FirebaseUser account = mauth.getCurrentUser();
        if(account == null) { return; }
        DocumentReference docRef = db.collection("users").document(account.getEmail());

        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Map<String, Object> userData = document.getData();
                        currentUser.name = (String) userData.get("firstAndLast");
//                        String email = (String) userData.get("Email Address");
                        currentUser.grade = (String) userData.get("grade");
                        currentUser.availability = (String) userData.get("availability");
                        currentUser.major = (String) userData.get("major");
                        currentUser.weaknesses = (String) userData.get("weaknesses");
                        currentUser.strengths = (String) userData.get("preferences");

//                        Log.d("Compat", currentUser.toString());
                        readDataOnStart(myRef);
                    }else {
                        Log.d("Firestore", "No such document");
                    }
                }else {
                    Log.d("Firestore", "get failed with ", task.getException());
                }
            }
        });
    }

    public void readDataOnStart(DatabaseReference myRef){

        myRef.child("userData").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {

                    List<User> data = new ArrayList<>();

                    for(DataSnapshot userSnapshot: task.getResult().getChildren()){
                        String timestamp = userSnapshot.child("Timestamp").getValue(String.class);
                        String emailAddress = userSnapshot.child("Email Address").getValue(String.class);
                        String fullName = userSnapshot.child("First & Last Name").getValue(String.class);
                        String grade = userSnapshot.child("Grade").getValue(String.class);
                        String availability = userSnapshot.child("Availability").getValue(String.class);
                        String majors = userSnapshot.child("Major(s)").getValue(String.class);
                        String strugglingWith = userSnapshot.child("Struggling With").getValue(String.class);
                        String confidentWith = userSnapshot.child("Confident With").getValue(String.class);
                        // Use the retrieved data as needed

                        User user = new User(fullName, confidentWith, availability, strugglingWith, grade, majors);

                        // -------- Compatibility Logic Starts Here --------

                        CompatibilityChecker checker = new CompatibilityChecker();
                        double compatibility = checker.calculateCompatibility(user, currentUser);
                        Log.d("Compat", currentUser.toString());
                        user.compatibility = Double.toString(compatibility);
                        data.add(user);

//                       // -------- Compatibility Logic Ends Here ---------

                    }


                    Log.d("Database", data.toString());
                    // Set Adapter
                    CardAdapter adapter = new CardAdapter(data); //Initializing CardAdapter
                    recyclerView.setAdapter(adapter);
                }
            }
        });
    }

    public void addFirebaseEventListener(DatabaseReference myRef){
        myRef.child("userData").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot userSnapshot: snapshot.getChildren()){
                    String timestamp = userSnapshot.child("Timestamp").getValue(String.class);
                    String emailAddress = userSnapshot.child("Email Address").getValue(String.class);
                    String fullName = userSnapshot.child("First & Last Name").getValue(String.class);
                    String grade = userSnapshot.child("Grade").getValue(String.class);
                    String availability = userSnapshot.child("Availability").getValue(String.class);
                    String majors = userSnapshot.child("Major(s)").getValue(String.class);
                    String strugglingWith = userSnapshot.child("Struggling With").getValue(String.class);
                    String confidentWith = userSnapshot.child("Confident With").getValue(String.class);
                    // Use the retrieved data as needed
                    System.out.println("User: " + fullName + ", Email: " + emailAddress);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("Database", error.toString());
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
