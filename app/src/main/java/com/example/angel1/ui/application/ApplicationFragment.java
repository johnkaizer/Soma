package com.example.angel1.ui.application;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.angel1.Adapters.ApplicationAdapter;
import com.example.angel1.Adapters.ScholarshipAdapter;
import com.example.angel1.Model.ApplicationModel;
import com.example.angel1.Model.ScholarshipsModel;
import com.example.angel1.R;
import com.example.angel1.databinding.FragmentApplicationBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ApplicationFragment extends Fragment {

    ApplicationAdapter applicationAdapter;
    ArrayList<ApplicationModel> list;
    Query databaseReference;
    RecyclerView appRec;
    private FirebaseUser user;
    DatabaseReference reference;
    String userIdentity;
    FragmentApplicationBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentApplicationBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        //Getting logged in user ID
        user = FirebaseAuth.getInstance().getCurrentUser();
        userIdentity = user.getUid();
        appRec=root.findViewById(R.id.myAppRec);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Applications").orderByChild("User").startAt(userIdentity).endAt(userIdentity);
        appRec.setHasFixedSize(true);
        appRec.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        list = new ArrayList<>();
        applicationAdapter = new ApplicationAdapter(getContext(),list);
        appRec.setAdapter(applicationAdapter);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    ApplicationModel applicationModel  = dataSnapshot.getValue(ApplicationModel.class);
                    list.add(applicationModel);
                }
                applicationAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(),"Sorry could not fetch anything",Toast.LENGTH_SHORT).show();
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