package com.example.angel1.Sponsor;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.ContentUris;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.angel1.Model.ScholarshipsModel;
import com.example.angel1.R;
import com.example.angel1.databinding.ActivityCreateScholarshipBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;

public class CreateScholarshipActivity extends AppCompatActivity {

    ActivityCreateScholarshipBinding binding;
    DatabaseReference dataRef;
    EditText title, description,message,deadline;
    TextView date;
    private DatePickerDialog picker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateScholarshipBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        dataRef= FirebaseDatabase.getInstance().getReference().child("Scholarships");

        title= findViewById(R.id.title);
        description= findViewById(R.id.desc);
        message= findViewById(R.id.message);
        deadline= findViewById(R.id.deadline);
        date = findViewById(R.id.textView5);
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());
        date.setText(currentDate);

        deadline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar calendar = Calendar.getInstance();
                int day= calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                picker = new DatePickerDialog(CreateScholarshipActivity.this,  new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        deadline.setText(dayOfMonth + "/" + (month + 1) + "/" + year);

                    }
                },year, month,day);
                picker.show();
            }
        });
        
        binding.submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveScholarship();
            }
        });
    }

    private void SaveScholarship() {
        String Title =title.getText().toString();
        String Description =description.getText().toString();
        String Message =message.getText().toString();
        String Deadline =deadline.getText().toString();
        String Posted =date.getText().toString();

        ScholarshipsModel scholarships = new ScholarshipsModel(Title,Posted,Description,Deadline,Message);
        dataRef.push().setValue(scholarships);
        Toast.makeText(CreateScholarshipActivity.this,"Successfully created appointment",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(CreateScholarshipActivity.this, MainActivity.class);
        startActivity(intent);
        finish();

    }
}