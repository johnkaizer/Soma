package com.example.angel1.ui.Approved;

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

import com.example.angel1.Adapters.ApplicationAdapter;
import com.example.angel1.Adapters.ApprovedScholarshipAdapter;
import com.example.angel1.Model.ApplicationModel;
import com.example.angel1.Model.ApprovedModel;
import com.example.angel1.R;
import com.example.angel1.databinding.FragmentApplicationBinding;
import com.example.angel1.databinding.FragmentApprovedBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Approved extends Fragment {
    ApprovedScholarshipAdapter approvedScholarshipAdapter;
    ArrayList<ApprovedModel> list;
    Query databaseReference;
    RecyclerView approved;
    private FirebaseUser user;
    DatabaseReference reference;
    String userIdentity;

    FragmentApprovedBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentApprovedBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        //Getting logged in user ID
        user = FirebaseAuth.getInstance().getCurrentUser();
        userIdentity = user.getEmail();
        approved=root.findViewById(R.id.approvedRec);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Approved").orderByChild("UserId").startAt(userIdentity).endAt(userIdentity);
        approved.setHasFixedSize(true);
        approved.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        list = new ArrayList<>();
        approvedScholarshipAdapter = new ApprovedScholarshipAdapter(getContext(),list);
        approved.setAdapter(approvedScholarshipAdapter);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    ApprovedModel approvedModel   = dataSnapshot.getValue(ApprovedModel.class);
                    list.add(approvedModel);
                }
                approvedScholarshipAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(),"Sorry could not fetch anything",Toast.LENGTH_SHORT).show();
            }
        });



        return root;

    }
}