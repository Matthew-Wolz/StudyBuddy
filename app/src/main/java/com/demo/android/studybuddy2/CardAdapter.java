package com.demo.android.studybuddy2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.MyViewHolder> {
    private List<User> dataList;

    public CardAdapter(List<User> dataList) {
        this.dataList = dataList;
    }
    //TODO: Change the above lines to utilize Firebase

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        User user = dataList.get(position);

        // Applying values to UI elements
        holder.matchName.setText(user.getName());
        holder.matchStrengths.setText(user.getStrengths());
        holder.matchAvailability.setText(user.getAvailability());
        holder.matchWeaknesses.setText(user.getWeaknesses());
        holder.matchGrade.setText(user.getGrade());

        //TODO: Apply compatibility value to UI
    }

         @Override
    public int getItemCount() {
        return dataList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView matchName, matchStrengths, matchAvailability, matchWeaknesses, matchGrade, compatibilityScore;
        ImageView profileImage; //need to access image uploaded to Firebase?

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            // Initializing UI elements
            matchName = itemView.findViewById(R.id.MatchName);
            matchStrengths = itemView.findViewById(R.id.Strengths);
            matchAvailability = itemView.findViewById(R.id.Availability);
            matchWeaknesses = itemView.findViewById(R.id.Weakness);
            matchGrade = itemView.findViewById(R.id.Grade);

            compatibilityScore = itemView.findViewById(R.id.CompatibilityScore);
        }
    }
}
