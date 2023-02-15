package com.example.angel1.Student;

import static com.example.angel1.Adapters.DBmain.TABLENAME;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.angel1.Adapters.DBmain;
import com.example.angel1.R;
import com.example.angel1.Sponsor.MainActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class ApplicationActivity extends AppCompatActivity {

    TextView title,posted,deadline,description, message;
    AppCompatButton submitBtn, uploadImage;
    EditText nameEt,schoolEt,ageEt,emailEt,feesEt,siblingsEt;
    ImageView imageView;
    //Request storage access
    public static final int REQUEST_CODE_IMAGE=101;
    Uri imageUri;
    boolean isImageAdded= false;
    //Firebase user reference to save user data using userUid
    private FirebaseUser user;
    DatabaseReference reference;
    DatabaseReference dataRef;
    String userID;
    //progressbar
    ProgressBar progressBar;
    StorageReference storageRef;
    private Spinner spinner,spinner1,spinner2;
    //Sqlite
    DBmain dBmain;
    int id = 0;
    SQLiteDatabase sqLiteDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application);
        dBmain = new DBmain(this);
        title = findViewById(R.id.appTitle);
        posted = findViewById(R.id.stdName);
        deadline = findViewById(R.id.stdSch);
        description = findViewById(R.id.stdEmail);
        message = findViewById(R.id.stdUid);
        //getting details from the cardView
        title.setText(getIntent().getExtras().getString("ScholarTitle"));
        posted.setText(getIntent().getExtras().getString("ScholarCreated"));
        deadline.setText(getIntent().getExtras().getString("ScholarDeadline"));
        description.setText(getIntent().getExtras().getString("ScholarDescription"));
        message.setText(getIntent().getExtras().getString("ScholarMessage"));

        //Firebase Paths
        dataRef= FirebaseDatabase.getInstance().getReference().child("Applications");
        storageRef= FirebaseStorage.getInstance().getReference().child("ApplicantsImages");
        reference = FirebaseDatabase.getInstance().getReference("Users");
        //Getting logged in user ID
        user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();
        //User details
        uploadImage = findViewById(R.id.uploadBtn);
        submitBtn =findViewById(R.id.submitBt);
        nameEt = findViewById(R.id.stud_name);
        schoolEt = findViewById(R.id.student_school);
        ageEt = findViewById(R.id.student_age);
        emailEt = findViewById(R.id.student_email);
        feesEt = findViewById(R.id.student_fees);
        siblingsEt = findViewById(R.id.student_siblings);
        imageView = findViewById(R.id.imageV);
        progressBar = findViewById(R.id.progressbar1);
        //Spinners
        spinner = findViewById(R.id.parent_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this ,R.array.pWorking, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner1 = findViewById(R.id.orphan_spinner);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this ,R.array.Orphan, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);

        spinner2 = findViewById(R.id.disability_spinner);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this ,R.array.Disabilities, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDataSQL();
                final String studentName= nameEt.getText().toString();
                final String studentAge=ageEt.getText().toString();
                final String studentEmail=emailEt.getText().toString();
                final String studentSchool= schoolEt.getText().toString();
                final String applicationTitle= title.getText().toString();
                final String studentDeadline= deadline.getText().toString();
                final String studentFee= feesEt.getText().toString();
                final String stdParentsWorking= spinner.getSelectedItem().toString();
                final String stdOrphan= spinner1.getSelectedItem().toString();
                final String stdSiblings= siblingsEt.getText().toString();
                final String stdDisabilities= spinner2.getSelectedItem().toString();
                final String user = userID;

                if (isImageAdded!=false && studentName!=null && studentAge!=null && studentEmail!=null && studentSchool!=null  && user!=null && applicationTitle!=null && studentDeadline!=null && studentFee!=null && stdParentsWorking!=null && stdOrphan!=null && stdSiblings!=null && stdDisabilities!=null){
                    saveApplication(studentName,studentAge,studentEmail,studentSchool,user,applicationTitle,studentDeadline,studentFee,stdParentsWorking,stdOrphan,stdSiblings,stdDisabilities);
                }

            }
        });

        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,REQUEST_CODE_IMAGE);

            }
        });

    }

    private void saveDataSQL() {
        ContentValues cv = new ContentValues();
        cv.put("stdName", nameEt.getText().toString());
        cv.put("appTitle", title.getText().toString());
        cv.put("stdEmail", emailEt.getText().toString());
        cv.put("stdSchool", schoolEt.getText().toString());
        cv.put("stdFee", feesEt.getText().toString());
        cv.put("stdParents", spinner.getSelectedItem().toString());
        cv.put("stdOrphan", spinner1.getSelectedItem().toString());
        cv.put("stdDisabilities", spinner2.getSelectedItem().toString());
        sqLiteDatabase = dBmain.getWritableDatabase();
        Long recinsert = sqLiteDatabase.insert(TABLENAME, null, cv);
        if (recinsert != null) {
            Toast.makeText(ApplicationActivity.this, "Details entered sql successfully", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveApplication(String studentName, String studentAge, String studentEmail, String studentSchool, String user,String applicationTitle,String studentDeadline,String studentFee,String stdParentsWorking,String stdOrphan,String stdSiblings,String stdDisabilities) {
        progressBar.setVisibility(View.VISIBLE);

        final String key = dataRef.push().getKey();
        storageRef.child(key +".jpg").putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                storageRef.child(key +".jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        HashMap hashMap=new HashMap();
                        hashMap.put("stdName",studentName);
                        hashMap.put("stdAge",studentAge);
                        hashMap.put("stdEmail",studentEmail);
                        hashMap.put("stdSchool",studentSchool);
                        hashMap.put("User", user);
                        hashMap.put("appTitle",applicationTitle);
                        hashMap.put("appDeadline",studentDeadline);
                        hashMap.put("stdFee",studentFee);
                        hashMap.put("stdParents",stdParentsWorking);
                        hashMap.put("stdOrphan",stdOrphan);
                        hashMap.put("stdSiblings",stdSiblings);
                        hashMap.put("stdDisabilities",stdDisabilities);
                        hashMap.put("ImageUrl",uri.toString());

                        dataRef.child(key).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(ApplicationActivity.this, "Data was successfully uploaded", Toast.LENGTH_SHORT).show();
                                emailEt.setText("");
                                schoolEt.setText("");
                                nameEt.setText("");
                                ageEt.setText("");
                                feesEt.setText("");
                                siblingsEt.setText("");
                                imageView.setImageResource(R.drawable.image_24);
                                progressBar.setVisibility(View.GONE);
                                startActivity(new Intent(ApplicationActivity.this, MainActivity.class));
                                finish();

                            }
                        });
                    }
                });

            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                double progress =snapshot.getBytesTransferred()*100/snapshot.getTotalByteCount();
                progressBar.setProgress((int) progress);

            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_IMAGE && data != null) {
            imageUri = data.getData();
            isImageAdded = true;
            imageView.setImageURI(imageUri);

        }
    }
}