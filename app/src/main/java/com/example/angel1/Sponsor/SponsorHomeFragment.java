package com.example.angel1.Sponsor;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.angel1.Adapters.AdminScholarAdapter;
import com.example.angel1.Model.ScholarshipsModel;
import com.example.angel1.R;
import com.example.angel1.databinding.FragmentSponsorHomeBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class SponsorHomeFragment extends Fragment {

    private FragmentSponsorHomeBinding binding;
    RecyclerView scholarRv;
    AdminScholarAdapter adminScholarAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSponsorHomeBinding.inflate(inflater, container,  false);
        View root = binding.getRoot();
        scholarRv= root.findViewById(R.id.scholarshipRv);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        layoutManager.setReverseLayout(true);
        scholarRv.setLayoutManager(layoutManager);
        FirebaseRecyclerOptions<ScholarshipsModel> context = new FirebaseRecyclerOptions.Builder<ScholarshipsModel>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("Scholarships"),ScholarshipsModel.class)
                .build();

        adminScholarAdapter = new AdminScholarAdapter(context,getContext());
        scholarRv.setAdapter(adminScholarAdapter);

        binding.addScholarship.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CreateScholarshipActivity.class));
            }
        });
        return root;
    }
    @Override
    public void onStart() {
        super.onStart();
        adminScholarAdapter.startListening();
    }
    @Override
    public void onStop() {
        super.onStop();
        adminScholarAdapter.stopListening();
    }
}