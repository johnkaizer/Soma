package com.example.angel1.Student;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.angel1.Model.DetailsModel;
import com.example.angel1.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MoreUserDetails extends AppCompatActivity {
    EditText addressEt,homeEt, countyEt,genderEt,nameEt;
    AppCompatButton submitBtn;
    DatabaseReference dataRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_user_details);
        dataRef= FirebaseDatabase.getInstance().getReference().child("MoreUserDetails");
        addressEt = findViewById(R.id.editText3);
        homeEt = findViewById(R.id.editText4);
        countyEt = findViewById(R.id.editText5);
        genderEt = findViewById(R.id.editText6);
        nameEt = findViewById(R.id.editText7);
        submitBtn = findViewById(R.id.SubButton);
        SharedPreferences preference= getSharedPreferences("PREFERENCE",MODE_PRIVATE);
        String FirstTime= preference.getString("FirstTimeInstall","");
        if (FirstTime.equals("Yes")){
            Intent intent=new Intent(   MoreUserDetails.this, StudentDash.class);
            startActivity(intent);
            finish();
        }else {
            SharedPreferences.Editor editor= preference.edit();
            editor.putString("FirstTimeInstall","Yes");
            editor.apply();
        }
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String address = addressEt.getText().toString().trim();
                String home = homeEt.getText().toString().trim();
                String county = countyEt.getText().toString().trim();
                String gender = genderEt.getText().toString().trim();
                String name = nameEt.getText().toString().trim();
                if (address.isEmpty()) {
                    addressEt.setError("home address is required!!");
                    addressEt.requestFocus();
                    return;
                }
                if (home.isEmpty()) {
                    homeEt.setError("home town is required!!");
                    homeEt.requestFocus();
                    return;
                }
                if (county.isEmpty()) {
                    countyEt.setError(" county is required!!");
                    countyEt.requestFocus();
                    return;
                }
                if (gender.isEmpty()) {
                    genderEt.setError(" gender is required!!");
                    genderEt.requestFocus();
                    return;
                }
                if (name.isEmpty()) {
                    nameEt.setError(" name is required!!");
                    nameEt.requestFocus();
                    return;
                }
                saveData();
            }
        });
    }
    private void saveData() {
        String UserAddress =  addressEt.getText().toString();
        String UserHome =  homeEt.getText().toString();
        String UserCounty =  countyEt.getText().toString();
        String UserGender =  genderEt.getText().toString();
        String UserName =  nameEt.getText().toString();

        DetailsModel details = new DetailsModel(UserAddress,UserHome,UserCounty,UserGender,UserName);
        dataRef.push().setValue(details);
        Toast.makeText(this, "Successfully submitted", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(MoreUserDetails.this, StudentDash.class));
    }
}