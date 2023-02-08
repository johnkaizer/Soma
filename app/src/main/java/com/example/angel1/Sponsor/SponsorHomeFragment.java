package com.example.angel1.Sponsor;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.angel1.Auth.LoginActivity;
import com.example.angel1.Auth.RegisterActivity;
import com.example.angel1.R;
import com.example.angel1.databinding.FragmentApplicationBinding;
import com.example.angel1.databinding.FragmentSponsorHomeBinding;

public class SponsorHomeFragment extends Fragment {

    private FragmentSponsorHomeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSponsorHomeBinding.inflate(inflater, container,  false);
        View root = binding.getRoot();

        binding.addScholarship.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CreateScholarshipActivity.class));
            }
        });
        return root;
    }
}