package com.demo.android.studybuddy2;

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

        //TODO autofill other values once name has been retrieved

        //apply value to individual UI elements
        holder.matchName.setText(user.getName());
        holder.matchStrengths.setText(user.getStrengths());
        holder.matchAvailability.setText(user.getAvailability());
        holder.matchWeaknesses.setText(user.getWeaknesses());
        holder.matchGrade.setText(user.getGrade());
        holder.compatibilityScore.setText(user.getScore());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView matchName, matchStrengths, matchAvailability, matchWeaknesses, matchGrade, compatibilityScore;
        ImageView profileImage; //will need to implement image access through Firebase

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            //initialize UI elements
            matchName = itemView.findViewById(R.id.MatchName);
            matchStrengths = itemView.findViewById(R.id.Strengths);
            matchAvailability = itemView.findViewById(R.id.Availability);
            matchWeaknesses = itemView.findViewById(R.id.Weakness);
            matchGrade = itemView.findViewById(R.id.Grade);

            compatibilityScore = itemView.findViewById(R.id.CompatibilityScore);
        }
    }
}
