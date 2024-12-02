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
        holder.matchName.setText(user.getName());
        holder.matchAvailability.setText(user.getAvailability());
        holder.matchPreferences.setText(user.getStudyPreference());

        // this later
//        holder.compatibilityScore.setText(user.getStrugglingWith());
//        // Example, use appropriate logic
//        holder.contactInfo.setText(user.getEmailAddress());
    }

         @Override
    public int getItemCount() {
        return dataList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView matchName, matchAvailability, matchPreferences, compatibilityScore, contactInfo;
        ImageView profileImage; //need to access image uploaded to Firebase?

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
    //            textViewItem = itemView.findViewById(R.id.possibleMatchName);
            matchName = itemView.findViewById(R.id.possibleMatchName);
            matchAvailability = itemView.findViewById(R.id.possibleMatchAvailability);
            matchPreferences = itemView.findViewById(R.id.possibleMatchPreferences);
            compatibilityScore = itemView.findViewById(R.id.possibleMatchCompatibilityScore);
            contactInfo = itemView.findViewById(R.id.possibleMatchContactInfo);
        }
    }
}
