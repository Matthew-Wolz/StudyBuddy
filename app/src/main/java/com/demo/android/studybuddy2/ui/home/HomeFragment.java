package com.demo.android.studybuddy2.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.android.studybuddy2.CardAdapter;
import com.demo.android.studybuddy2.R;
import com.demo.android.studybuddy2.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";
    private RecyclerView recyclerView;

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "Looking at Home Tab");

        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

//        ###############################

        recyclerView = root.findViewById(R.id.recyclerView);

        // Set Layout Manager
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setLayoutManager(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        // Create Data
        List<String> data = new ArrayList<>(); //TODO: Replace this with Firebase container.

        //TODO: We wont need to enter values here, but will need to initialize the CardAdapter
        for (int i = 1; i <= 20; i++) {
            data.add("Test Subj. " + i);
        }

        // Set Adapter
        CardAdapter adapter = new CardAdapter(data); //Initializing CardAdapter
        recyclerView.setAdapter(adapter);

//        #####################################

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}