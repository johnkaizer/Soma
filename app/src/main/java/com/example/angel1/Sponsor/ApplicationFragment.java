package com.example.angel1.Sponsor;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.angel1.Adapters.ApplicationAdminAdapter;
import com.example.angel1.Model.ApplicationModel;

import com.example.angel1.R;
import com.example.angel1.databinding.FragmentApplicationBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ApplicationFragment extends Fragment {
    RecyclerView applicationRV;
    ArrayList<ApplicationModel> list;
    ApplicationAdminAdapter applicationAdminAdapter  ;
    Query databaseReference;

    FragmentApplicationBinding  binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=  inflater.inflate(R.layout.fragment_application2, container, false);
        applicationRV= view.findViewById(R.id.applicantsRv);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Applications");
        applicationRV.setHasFixedSize(true);
        applicationRV.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        list = new ArrayList<>();
        applicationAdminAdapter = new ApplicationAdminAdapter(getContext(), list);
        applicationRV.setAdapter(applicationAdminAdapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    ApplicationModel applicationModel   = dataSnapshot.getValue(ApplicationModel.class);
                    list.add(applicationModel);
                }
                applicationAdminAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;
    }
}