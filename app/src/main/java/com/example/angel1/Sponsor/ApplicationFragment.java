package com.example.angel1.Sponsor;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.angel1.Adapters.AdminApplicationAdapter;
import com.example.angel1.Model.ApplicationModel;

import com.example.angel1.R;
import com.example.angel1.databinding.FragmentApplicationBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class ApplicationFragment extends Fragment {
    RecyclerView applicationRV;
    AdminApplicationAdapter applicationAdminAdapter;
    FragmentApplicationBinding  binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=  inflater.inflate(R.layout.fragment_application2, container, false);
        applicationRV= view.findViewById(R.id.applicantsRv);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        layoutManager.setReverseLayout(true);
        layoutManager.getStackFromEnd();
        applicationRV.setLayoutManager(layoutManager);

        FirebaseRecyclerOptions<ApplicationModel> context = new FirebaseRecyclerOptions.Builder<ApplicationModel>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("Applications"),ApplicationModel.class)
                .build();

        applicationAdminAdapter = new AdminApplicationAdapter(context,getContext());
        applicationRV.setAdapter(applicationAdminAdapter);


        return view;
    }
    @Override
    public void onStart() {
        super.onStart();
        applicationAdminAdapter.startListening();
    }
    @Override
    public void onStop() {
        super.onStop();
        applicationAdminAdapter.stopListening();
    }
}