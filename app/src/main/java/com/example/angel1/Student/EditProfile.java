package com.example.angel1.Student;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.angel1.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditProfile extends AppCompatActivity {
    EditText fEmail, fPhone, fPass;
    AppCompatButton editBtn;
    String userName, userEmail1, userPassword, userPhone, fullName;
    DatabaseReference reference;
    private FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        reference = FirebaseDatabase.getInstance().getReference("Users");
        fEmail = findViewById(R.id.editText3);
        fPhone = findViewById(R.id.editText4);
        fPass = findViewById(R.id.editText5);

        showData();
        editBtn = findViewById(R.id.editButton);
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( isEmailChanged() || isPhoneChanged() || isPassChanged()) {
                    Toast.makeText(EditProfile.this, "Changes Saved", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(EditProfile.this, "No changes found", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public boolean isEmailChanged() {
        userEmail1 = fEmail.getText().toString();
        if (!userEmail1.equals(fEmail.getText().toString())) {
            reference.child(fullName).child("email").setValue(fEmail.getText().toString());
            userName = fEmail.getText().toString();
            return true;
        } else {
            return false;
        }
    }
    public boolean isPhoneChanged() {
        userPhone = fPhone.getText().toString();
        if (!userPhone.equals(fPhone.getText().toString())) {
            reference.child(fullName).child("phone").setValue(fPhone.getText().toString());
            userName = fPhone.getText().toString();
            return true;
        } else {
            return false;
        }
    }
    public boolean isPassChanged() {
        userPassword = fPass.getText().toString();
        if (!userPassword.equals(fPass.getText().toString())) {
            reference.child(fullName).child("password").setValue(fPass.getText().toString());
            userName = fPhone.getText().toString();
            return true;
        } else {
            return false;
        }
    }
    public void  showData(){
        Intent intent= getIntent();

        userEmail1 = intent.getStringExtra("uemail");
        userPhone = intent.getStringExtra("uphone");
        userPassword = intent.getStringExtra("upass");

        fEmail.setText(userEmail1);
        fPhone.setText(userPhone);
        fPass.setText(userPassword);

    }

}

