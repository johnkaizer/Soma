package com.example.angel1.Sponsor;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.angel1.Adapters.AdminScholarshipAdapter;
import com.example.angel1.Adapters.ScholarshipAdapter;
import com.example.angel1.Auth.LoginActivity;
import com.example.angel1.Auth.RegisterActivity;
import com.example.angel1.Model.ScholarshipsModel;
import com.example.angel1.R;
import com.example.angel1.databinding.FragmentApplicationBinding;
import com.example.angel1.databinding.FragmentSponsorHomeBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SponsorHomeFragment extends Fragment {

    private FragmentSponsorHomeBinding binding;
    RecyclerView scholarRv;
    ArrayList<ScholarshipsModel> list;
    AdminScholarshipAdapter adminScholarshipAdapter ;
    Query databaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSponsorHomeBinding.inflate(inflater, container,  false);
        View root = binding.getRoot();
        scholarRv= root.findViewById(R.id.scholarshipRv);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Scholarships");
        scholarRv.setHasFixedSize(true);
        scholarRv.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        list = new ArrayList<>();
        adminScholarshipAdapter = new AdminScholarshipAdapter(getContext(), list);
        scholarRv.setAdapter(adminScholarshipAdapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    ScholarshipsModel scholarshipsModel  = dataSnapshot.getValue(ScholarshipsModel.class);
                    list.add(scholarshipsModel);
                }
                adminScholarshipAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        binding.addScholarship.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CreateScholarshipActivity.class));
            }
        });
        return root;
    }
}