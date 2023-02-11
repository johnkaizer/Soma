package com.example.angel1.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.angel1.Model.ApplicationModel;
import com.example.angel1.Model.ApprovedModel;
import com.example.angel1.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminApplicationAdapter extends FirebaseRecyclerAdapter<ApplicationModel, AdminApplicationAdapter.ApplicationViewHolder> {
    Context context;

    public AdminApplicationAdapter(@NonNull FirebaseRecyclerOptions<ApplicationModel> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull ApplicationViewHolder holder, @SuppressLint("RecyclerView") int i, @NonNull ApplicationModel model) {
        getRef(i).getKey();

        holder.title.setText(model.getAppTitle());
        holder.deadline.setText(model.getAppDeadline());
        holder.name.setText(model.getStdName());
        holder.email.setText(model.getStdEmail());
        holder.school.setText(model.getStdSchool());
        holder.confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Approved");
                String AppTitle = holder.title.getText().toString();
                String stdName = holder.name.getText().toString();
                String stdEmail = holder.email.getText().toString();
                String stdSchool = holder.school.getText().toString();

                ApprovedModel approvedModel = new ApprovedModel(AppTitle, stdName, stdEmail, stdSchool);
                databaseReference.push().setValue(approvedModel);
                Toast.makeText(context, "Confirmed successfully", Toast.LENGTH_SHORT).show();
                FirebaseDatabase.getInstance().getReference().child("Applications")
                        .child(getRef(i).getKey())
                        .removeValue()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                            }
                        });

            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference().child("Applications")
                        .child(getRef(i).getKey())
                        .removeValue()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(context, "Declined Successfully..", Toast.LENGTH_SHORT).show();

                            }
                        });
            }
        });
    }

    @NonNull
    @Override
    public ApplicationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ApplicationViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.application_admin_item, parent, false));
    }

    public class ApplicationViewHolder extends RecyclerView.ViewHolder {
        TextView title, deadline, name, email, school, uid;
        AppCompatButton confirm, delete;

        public ApplicationViewHolder(@NonNull View itemView) {
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
