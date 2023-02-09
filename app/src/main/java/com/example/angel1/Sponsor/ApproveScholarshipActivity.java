package com.example.angel1.Sponsor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.angel1.Model.ApprovedModel;
import com.example.angel1.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ApproveScholarshipActivity extends AppCompatActivity {
    TextView title,name,school,email;
    Button ApproveBtn;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approve_scholarship);
        title = findViewById(R.id.appTitle);
        name = findViewById(R.id.stdName);
        school = findViewById(R.id.stdSch);
        email = findViewById(R.id.stdEmail);
        ApproveBtn = findViewById(R.id.button);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Approved");

        //getting details from the cardView and setting to textviews
        title.setText(getIntent().getExtras().getString("ScholarTitle"));
        name.setText(getIntent().getExtras().getString("StudentName"));
        school.setText(getIntent().getExtras().getString("StudentSchool"));
        email.setText(getIntent().getExtras().getString("StudentEmail"));

        ApproveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Confirm();
            }
        });
    }
    private void Confirm() {
        String AppTitle =  title.getText().toString();
        String stdName =  name.getText().toString();
        String stdEmail =  email.getText().toString();
        String stdSchool =  school.getText().toString();

        ApprovedModel approvedModel  = new ApprovedModel(AppTitle,stdName,stdEmail,stdSchool);
        databaseReference.push().setValue(approvedModel);
        Toast.makeText(ApproveScholarshipActivity.this,"Confirmed successfully",Toast.LENGTH_SHORT).show();
        finish();
    }
}