package com.demo.android.studybuddy2.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.android.studybuddy2.CardAdapter;
import com.demo.android.studybuddy2.R;
import com.demo.android.studybuddy2.User;
import com.demo.android.studybuddy2.databinding.FragmentHomeBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Comment;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";
    private RecyclerView recyclerView;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "Looking at Home Tab");

        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        final TextView textView = binding.textHome;
//        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

//        ###############################

        recyclerView = root.findViewById(R.id.recyclerView);

        // Set Layout Manager
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setLayoutManager(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        addFirebaseEventListener(myRef);
        readDataOnStart(myRef);

//        #####################################
        return root;
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

                        User user = new User(fullName, confidentWith, availability);

                        data.add(user);
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
