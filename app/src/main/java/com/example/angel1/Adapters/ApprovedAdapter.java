package com.example.angel1.Adapters;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.angel1.Model.ApprovedModel;
import com.example.angel1.Model.ConfirmedModel;
import com.example.angel1.Model.DetailsModel;
import com.example.angel1.R;
import com.example.angel1.Student.MoreUserDetails;
import com.example.angel1.Student.StudentDash;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
        holder.confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference dataRef= FirebaseDatabase.getInstance().getReference().child("Confirmed");
                String appTitle = holder.title.getText().toString();
                String stdDeadline = holder.deadline.getText().toString();
                String stdName = holder.name.getText().toString();
                String stdEmail = holder.email.getText().toString();
                String stdSchool = holder.school.getText().toString();

                ConfirmedModel confirmed = new ConfirmedModel(appTitle,stdDeadline,stdName,stdEmail,stdSchool);
                dataRef.push().setValue(confirmed);
                Toast.makeText(context, "Successfully Sent", Toast.LENGTH_SHORT).show();



            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, deadline, name, email, school;
        AppCompatButton confirm;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textView13);
            deadline = itemView.findViewById(R.id.textView15);
            name = itemView.findViewById(R.id.textView17);
            email = itemView.findViewById(R.id.textView19);
            school = itemView.findViewById(R.id.textView21);
            confirm = itemView.findViewById(R.id.appCompatButton3);
        }
    }
}
