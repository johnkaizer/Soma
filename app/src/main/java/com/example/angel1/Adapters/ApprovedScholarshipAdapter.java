package com.example.angel1.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.angel1.Model.ApprovedModel;
import com.example.angel1.Model.ConfirmedModel;
import com.example.angel1.R;

import java.util.ArrayList;

public class ApprovedScholarshipAdapter extends RecyclerView.Adapter<ApprovedScholarshipAdapter.ViewHolder> {

    public ApprovedScholarshipAdapter(Context context, ArrayList<ConfirmedModel> list) {
        this.context = context;
        this.list = list;
    }

    Context context;
    ArrayList<ConfirmedModel> list;
    @NonNull
    @Override
    public ApprovedScholarshipAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.approved_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ApprovedScholarshipAdapter.ViewHolder holder, int position) {
        holder.title.setText(list.get(position).getAppTitle());
        holder.school.setText(list.get(position).getStdSchool());
        holder.name.setText(list.get(position).getStdName());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title,school,name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title =itemView.findViewById(R.id.textView13);
            school =itemView.findViewById(R.id.textView15);
            name =itemView.findViewById(R.id.textView17);
        }
    }
}
