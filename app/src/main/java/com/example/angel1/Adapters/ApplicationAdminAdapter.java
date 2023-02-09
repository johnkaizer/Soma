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

import com.example.angel1.Model.ApplicationModel;
import com.example.angel1.R;
import com.example.angel1.Sponsor.ApproveScholarshipActivity;
import com.example.angel1.Student.ApplicationActivity;

import java.util.ArrayList;

public class ApplicationAdminAdapter extends RecyclerView.Adapter<ApplicationAdminAdapter.ViewHolder> {

    public ApplicationAdminAdapter(Context context, ArrayList<ApplicationModel> list) {
        this.context = context;
        this.list = list;
    }

    Context context;
    ArrayList<ApplicationModel>list;

    @NonNull
    @Override
    public ApplicationAdminAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.application_admin_item, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull ApplicationAdminAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.title.setText(list.get(position).getAppTitle());
        holder.deadline.setText(list.get(position).getAppDeadline());
        holder.name.setText(list.get(position).getStdName());
        holder.email.setText(list.get(position).getStdEmail());
        holder.school.setText(list.get(position).getStdSchool());
        holder.confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context, ApproveScholarshipActivity.class);
                intent.putExtra("ScholarTitle",list.get(position).getAppTitle());
                intent.putExtra("StudentName",list.get(position).getStdName());
                intent.putExtra("StudentSchool",list.get(position).getStdSchool());
                intent.putExtra("StudentEmail",list.get(position).getStdEmail());
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
        TextView title,deadline,name,email,school,uid;
        AppCompatButton confirm;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.textView13);
            deadline = itemView.findViewById(R.id.textView15);
            name = itemView.findViewById(R.id.textView17);
            email = itemView.findViewById(R.id.textView19);
            school = itemView.findViewById(R.id.textView21);
            school = itemView.findViewById(R.id.textView21);
            confirm = itemView.findViewById(R.id.appCompatButton3);
        }
    }
}
