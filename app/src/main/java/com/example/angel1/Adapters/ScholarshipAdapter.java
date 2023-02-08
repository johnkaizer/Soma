package com.example.angel1.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.angel1.ApplicationActivity;
import com.example.angel1.Model.ScholarshipsModel;
import com.example.angel1.R;

import java.util.ArrayList;

public class ScholarshipAdapter extends RecyclerView.Adapter<ScholarshipAdapter.ViewHolder> {

    public ScholarshipAdapter(Context context, ArrayList<ScholarshipsModel> list) {
        this.context = context;
        this.list = list;
    }

    Context context;
    ArrayList<ScholarshipsModel> list;
    @NonNull
    @Override
    public ScholarshipAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.scholarship_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ScholarshipAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.created.setText(list.get(position).getDateCreated());
        holder.deadline.setText(list.get(position).getDeadline());
        holder.title.setText(list.get(position).getTitle());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context, ApplicationActivity.class);
                intent.putExtra("ScholarTitle",list.get(position).getTitle());
                intent.putExtra("ScholarCreated",list.get(position).getDateCreated());
                intent.putExtra("ScholarDeadline",list.get(position).getDeadline());
                intent.putExtra("ScholarDescription",list.get(position).getDescription());
                intent.putExtra("ScholarMessage",list.get(position).getMessage());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView created, deadline,title;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            created =itemView.findViewById(R.id.date_posted);
            deadline =itemView.findViewById(R.id.deadlinetxt);
            title =itemView.findViewById(R.id.scholarship_header);
            cardView = itemView.findViewById(R.id.cardView1);

        }
    }
}
