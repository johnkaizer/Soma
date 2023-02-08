package com.example.angel1.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.angel1.Adapters.ScholarshipAdapter;
import com.example.angel1.Model.ScholarshipsModel;
import com.example.angel1.R;
import com.example.angel1.databinding.FragmentHomeBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    RecyclerView scholarshipRv;
    ArrayList<ScholarshipsModel> list;
    ScholarshipAdapter scholarshipAdapter;
    Query databaseReference;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        //Image slider
        ImageSlider imageSlider = root.findViewById(R.id.imageView2);
        ArrayList<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.scholar0,ScaleTypes.CENTER_INSIDE));
        slideModels.add(new SlideModel(R.drawable.kenya1, ScaleTypes.CENTER_INSIDE));
        slideModels.add(new SlideModel(R.drawable.scholar6, ScaleTypes.CENTER_INSIDE));
        slideModels.add(new SlideModel(R.drawable.scholar4, ScaleTypes.CENTER_INSIDE));
        imageSlider.setImageList(slideModels,ScaleTypes.CENTER_CROP);
        scholarshipRv=root.findViewById(R.id.scholarRec);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Scholarships");
        scholarshipRv.setHasFixedSize(true);
        scholarshipRv.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL, false));
        list = new ArrayList<>();
        scholarshipAdapter = new ScholarshipAdapter(getContext(), list);
        scholarshipRv.setAdapter(scholarshipAdapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    ScholarshipsModel scholarshipsModel  = dataSnapshot.getValue(ScholarshipsModel.class);
                    list.add(scholarshipsModel);
                }
                scholarshipAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}