package com.example.angel1.Student;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.angel1.Model.User;
import com.example.angel1.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;

public class ApplicationActivity extends AppCompatActivity {

    TextView title,posted,deadline,description, message;
    AppCompatButton submitBtn, uploadImage;
    EditText nameEt,schoolEt,ageEt,emailEt;
    ImageView imageView;
    //Request storage access
    public static final int REQUEST_CODE_IMAGE=101;
    Uri imageUri;
    boolean isImageAdded= false;
    //Firebase user reference to save user data using userUid
    DatabaseReference reference;
    DatabaseReference dataRef;
    //progressbar
    ProgressBar progressBar;
    StorageReference storageRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application);
        //Firebase Paths
        dataRef= FirebaseDatabase.getInstance().getReference().child("Applications");
        storageRef= FirebaseStorage.getInstance().getReference().child("ApplicantsImages");

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
        //User details
        uploadImage = findViewById(R.id.uploadBtn);
        submitBtn =findViewById(R.id.submitBt);
        nameEt = findViewById(R.id.stud_name);
        schoolEt = findViewById(R.id.student_school);
        ageEt = findViewById(R.id.student_age);
        emailEt = findViewById(R.id.student_email);
        imageView = findViewById(R.id.imageV);
        progressBar = findViewById(R.id.progressbar1);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String studentName= nameEt.getText().toString();
                final String studentAge=ageEt.getText().toString();
                final String studentEmail=emailEt.getText().toString();
                final String studentSchool= schoolEt.getText().toString();
                final String applicationTitle= title.getText().toString();
                final String studentDeadline= deadline.getText().toString();

                if (isImageAdded!=false && studentName!=null && studentAge!=null && studentEmail!=null && studentSchool!=null && applicationTitle!=null && studentDeadline!=null){
                    saveApplication(studentName,studentAge,studentEmail,studentSchool,applicationTitle,studentDeadline);
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

    private void saveApplication(String studentName, String studentAge, String studentEmail, String studentSchool,String applicationTitle,String studentDeadline) {
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
                        hashMap.put("appTitle",applicationTitle);
                        hashMap.put("appDeadline",studentDeadline);
                        hashMap.put("ImageUrl",uri.toString());

                        dataRef.child(key).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(ApplicationActivity.this, "Data was successfully uploaded", Toast.LENGTH_SHORT).show();
                                emailEt.setText("");
                                schoolEt.setText("");
                                nameEt.setText("");
                                ageEt.setText("");
                                imageView.setImageResource(R.drawable.image_24);
                                progressBar.setVisibility(View.GONE);

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

    private boolean checkStoragePermission(){
        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)==(PackageManager.PERMISSION_GRANTED);
        return result;

    }
    private byte[] ImageViewToByte(ImageView imageV) {
        Bitmap bitmap = ((BitmapDrawable)imageV.getDrawable()).getBitmap();
        ByteArrayOutputStream stream= new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,80,stream);
        byte[]bytes= stream.toByteArray();
        return bytes;
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