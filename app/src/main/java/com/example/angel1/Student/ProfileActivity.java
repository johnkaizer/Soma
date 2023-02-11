package com.example.angel1.Student;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.angel1.Model.User;
import com.example.angel1.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.GridHolder;
import com.orhanobut.dialogplus.ViewHolder;

public class ProfileActivity extends AppCompatActivity {
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;
    ProgressBar progressBar;

    AppCompatButton editBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        progressBar =findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        editBtn = findViewById(R.id.edit);
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogPlus dialogPlus = DialogPlus.newDialog(ProfileActivity.this)
                        .setGravity(Gravity.CENTER)
                        .setMargin(50,0,50,0)
                        .setContentHolder(new ViewHolder(R.layout.edit))
                        .setExpanded(false)
                        .create();

                View holderView  = (LinearLayout) dialogPlus.getHolderView();
                EditText fName= holderView.findViewById(R.id.editText2);
                EditText fEmail= holderView.findViewById(R.id.editText3);
                EditText fPhone= holderView.findViewById(R.id.editText4);
                EditText fPass= holderView.findViewById(R.id.editText5);



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
    }
}