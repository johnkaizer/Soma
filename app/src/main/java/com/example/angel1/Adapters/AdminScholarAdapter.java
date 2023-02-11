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

import com.example.angel1.Model.ScholarshipsModel;
import com.example.angel1.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

public class AdminScholarAdapter extends FirebaseRecyclerAdapter<ScholarshipsModel,AdminScholarAdapter.ViewHolder> {
    Context context;
    public AdminScholarAdapter(@NonNull FirebaseRecyclerOptions<ScholarshipsModel> options,Context context) {
        super(options);
        this.context= context;
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int i, @NonNull ScholarshipsModel model) {
        getRef(i).getKey();

        holder.created.setText(model.getDateCreated());
        holder.deadline.setText(model.getDeadline());
        holder.title.setText(model.getTitle());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference().child("Scholarships")
                        .child(getRef(i).getKey())
                        .removeValue()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(context,"Deleted Successfully..",Toast.LENGTH_SHORT).show();

                            }
                        });

            }
        });

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.scholarship_item_admin, parent, false));
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
