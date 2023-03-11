package com.example.angel1.Sponsor;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.angel1.Adapters.ApprovedScholarshipAdapter;
import com.example.angel1.Adapters.ApprovedScholarshipAdapter2;
import com.example.angel1.Model.ConfirmedModel;
import com.example.angel1.R;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class AdminProfileFragment extends Fragment {
    ApprovedScholarshipAdapter2 approvedScholarshipAdapter2;
    ArrayList<ConfirmedModel> list;
    Query databaseReference;
    RecyclerView approved1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=  inflater.inflate(R.layout.fragment_admin_profile, container, false);
        approved1=view.findViewById(R.id.scholarhipp);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Confirmed");
        approved1.setHasFixedSize(true);
        approved1.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        list = new ArrayList<>();
        approvedScholarshipAdapter2 = new ApprovedScholarshipAdapter2(getContext(),list);
        approved1.setAdapter(approvedScholarshipAdapter2);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    ConfirmedModel confirmedModel   = dataSnapshot.getValue(ConfirmedModel.class);
                    list.add(confirmedModel);
                }
                approvedScholarshipAdapter2.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(),"Sorry could not fetch anything",Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }
}