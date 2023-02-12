package com.example.angel1.Adapters;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.angel1.Model.ApprovedModel;
import com.example.angel1.R;

import java.util.ArrayList;

public class ApprovedAdapter extends RecyclerView.Adapter<ApprovedAdapter.ViewHolder> {

    public ApprovedAdapter(Context context, int application_admin_item, ArrayList<ApprovedModel> list, SQLiteDatabase sqLiteDatabase) {
        this.context = context;
        this.list = list;
    }
    Context context;
    ArrayList<ApprovedModel>list;
    @NonNull
    @Override
    public ApprovedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_approved_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ApprovedAdapter.ViewHolder holder, int position) {
        holder.title.setText(list.get(position).getAppTitle());
        holder.deadline.setText(list.get(position).getStdName());
        holder.name.setText(list.get(position).getStdFee());
        holder.email.setText(list.get(position).getStdEmail());
        holder.school.setText(list.get(position).getStdSchool());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, deadline, name, email, school, uid;
        AppCompatButton confirm, delete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textView13);
            deadline = itemView.findViewById(R.id.textView15);
            name = itemView.findViewById(R.id.textView17);
            email = itemView.findViewById(R.id.textView19);
            school = itemView.findViewById(R.id.textView21);
            school = itemView.findViewById(R.id.textView21);
            confirm = itemView.findViewById(R.id.appCompatButton3);
            delete = itemView.findViewById(R.id.appCompatButton);
        }
    }
}
