package com.example.angel1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditProfile extends AppCompatActivity {
    EditText fName, fEmail, fPhone, fPass;
    AppCompatButton editBtn;
    String userName, userEmail1, userPassword, userPhone, userID;
    DatabaseReference reference;
    private FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        reference = FirebaseDatabase.getInstance().getReference("Users");
        //Getting logged in user ID
        user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();
        fName = findViewById(R.id.editText2);
        fEmail = findViewById(R.id.editText3);
        fPhone = findViewById(R.id.editText4);
        fPass = findViewById(R.id.editText5);
        fName.setText(getIntent().getExtras().getString("uname"));
        fEmail.setText(getIntent().getExtras().getString("uemail"));
        fPhone.setText(getIntent().getExtras().getString("uphone"));
        fPass.setText(getIntent().getExtras().getString("upass"));
        editBtn = findViewById(R.id.editButton);
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNameChanged() || isEmailChanged() || isPhoneChanged() || isPassChanged()) {
                    Toast.makeText(EditProfile.this, "Changes Saved", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(EditProfile.this, "No changes found", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    public boolean isNameChanged() {
        if (!userName.equals(fName.getText().toString())) {
            reference.child(userID).child("fullName").setValue(fName.getText().toString());
            userName = fName.getText().toString();
            return true;
        } else {
            return false;
        }

    }

    public boolean isEmailChanged() {
        if (!userEmail1.equals(fEmail.getText().toString())) {
            reference.child(userID).child("email").setValue(fEmail.getText().toString());
            userEmail1 = fEmail.getText().toString();
            return true;
        } else {
            return false;
        }

    }

    public boolean isPhoneChanged() {
        if (!userPhone.equals(fPhone.getText().toString())) {
            reference.child(userID).child("phone").setValue(fPhone.getText().toString());
            userPhone = fPhone.getText().toString();
            return true;
        } else {
            return false;
        }

    }

    public boolean isPassChanged() {
        if (!userPassword.equals(fPass.getText().toString())) {
            reference.child(userID).child("password").setValue(fPass.getText().toString());
            userPassword = fPass.getText().toString();
            return true;
        } else {
            return false;
        }

    }
}

