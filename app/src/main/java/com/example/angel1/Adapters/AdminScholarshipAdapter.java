package com.example.angel1.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.angel1.Model.ScholarshipsModel;
import com.example.angel1.R;
import com.example.angel1.Student.ApplicationActivity;

import java.util.ArrayList;

public class AdminScholarshipAdapter extends RecyclerView.Adapter<AdminScholarshipAdapter.ViewHolder> {

    public AdminScholarshipAdapter(Context context, ArrayList<ScholarshipsModel> list) {
        this.context = context;
        this.list = list;
    }
    Context context;
    ArrayList<ScholarshipsModel> list;
    @NonNull
    @Override
    public AdminScholarshipAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.scholarship_item_admin, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AdminScholarshipAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.created.setText(list.get(position).getDateCreated());
        holder.deadline.setText(list.get(position).getDeadline());
        holder.title.setText(list.get(position).getTitle());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView created, deadline,title;
        AppCompatButton cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            created =itemView.findViewById(R.id.date_posted);
            deadline =itemView.findViewById(R.id.deadlinetxt);
            title =itemView.findViewById(R.id.scholarship_header);
            cardView = itemView.findViewById(R.id.deleteBtn);

        }
    }
}
