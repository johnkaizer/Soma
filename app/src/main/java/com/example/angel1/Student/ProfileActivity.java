package com.example.angel1.Student;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.angel1.Adapters.ApprovedScholarshipAdapter;
import com.example.angel1.Adapters.MoreDetailsAdapter;
import com.example.angel1.Model.ConfirmedModel;
import com.example.angel1.Model.DetailsModel;
import com.example.angel1.Model.User;
import com.example.angel1.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID, userIdentity;
    ProgressBar progressBar;

    AppCompatButton editBtn;
    MoreDetailsAdapter moreDetailsAdapter;
    ArrayList<DetailsModel> list;
    Query databaseReference;
    RecyclerView moreRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        progressBar =findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        editBtn = findViewById(R.id.edit);
        moreRV=findViewById(R.id.moreRv);
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();
        userIdentity = user.getEmail();
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TextView fullNameTxt = findViewById(R.id.cust_name);
                final TextView emailTxt = findViewById(R.id.cust_email);
                final TextView phoneTxt =findViewById(R.id.cust_phone);
                final TextView passwordTxt =findViewById(R.id.password);

                String name = fullNameTxt.getText().toString();
                String email = emailTxt.getText().toString();
                String phone = phoneTxt.getText().toString();
                String pass = passwordTxt.getText().toString();

                Intent intent = new Intent(ProfileActivity.this, EditProfile.class);
                intent.putExtra("uname", name);
                intent.putExtra("uemail", email);
                intent.putExtra("uphone", phone);
                intent.putExtra("upass", pass);
                startActivity(intent);

            }
        });

        final TextView fullNameTxt = findViewById(R.id.cust_name);
        final TextView emailTxt = findViewById(R.id.cust_email);
        final TextView phoneTxt =findViewById(R.id.cust_phone);
        final TextView passwordTxt =findViewById(R.id.password);
        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);
                if (userProfile !=null){
                    String fullName = userProfile.fullName;
                    String email = userProfile.email;
                    String phone = userProfile.phone;
                    String password = userProfile.password;
                    fullNameTxt.setText(fullName);
                    emailTxt.setText(email);
                    phoneTxt.setText(phone);
                    passwordTxt.setText(password);

                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        databaseReference = FirebaseDatabase.getInstance().getReference().child("MoreUserDetails").orderByChild("uEmail").startAt(userIdentity).endAt(userIdentity);
        moreRV.setHasFixedSize(true);
        moreRV.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        list = new ArrayList<>();
        moreDetailsAdapter = new MoreDetailsAdapter(this,list);
        moreRV.setAdapter(moreDetailsAdapter);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    DetailsModel detailsModel   = dataSnapshot.getValue(DetailsModel.class);
                    list.add(detailsModel);
                }
                moreDetailsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ProfileActivity.this,"Sorry could not fetch anything",Toast.LENGTH_SHORT).show();
            }
        });
    }
}