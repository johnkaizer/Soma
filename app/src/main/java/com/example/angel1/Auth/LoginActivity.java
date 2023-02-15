package com.example.angel1.Auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.angel1.Student.MoreUserDetails;
import com.example.angel1.R;
import com.example.angel1.Sponsor.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    ProgressBar progressBar;
    EditText EditTextEmail,EditTextPassword;
    boolean passwordVisible;
    TextView signUp;
    CheckBox admin;
    AppCompatButton SignBtn;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        EditTextEmail = findViewById(R.id.editText2);
        EditTextPassword = findViewById(R.id.editText3);
        progressBar = findViewById(R.id.progressBar);
        admin =findViewById(R.id.checkBox);
        SignBtn = findViewById(R.id.appCompatButton);
        EditTextPassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int Right=2;
                if (event.getAction()==MotionEvent.ACTION_UP){
                    if (event.getRawX()>=EditTextPassword.getRight()-EditTextPassword.getCompoundDrawables()[Right].getBounds().width()){
                        int selection=EditTextPassword.getSelectionEnd();
                        if (passwordVisible){
                            //show password
                            EditTextPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.visibility_off_24,0);
                            //hide password
                            EditTextPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisible=false;

                        }else {
                            //show password
                            EditTextPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.visibility_24,0);
                            //show password
                            EditTextPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisible=true;

                        }
                        EditTextPassword.setSelection(selection);
                        return  true;

                    }
                }
                return false;
            }
        });
        SignBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                finish();
            }
        });

        EditTextEmail.addTextChangedListener(textWatcher);
        EditTextPassword.addTextChangedListener(textWatcher);
    }
    public void login(View view) {
        if (admin.isChecked()){
            String email1 =EditTextEmail.getText().toString().trim();
            String password1 =EditTextPassword.getText().toString().trim();
            if (email1.isEmpty()){
                EditTextEmail.setError(" email is required!!");
                EditTextEmail.requestFocus();
                return;
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email1).matches()){
                EditTextEmail.setError("Please provide a valid email address!");
                EditTextEmail.requestFocus();
                return;
            }
            if (password1.isEmpty()){
                EditTextPassword.setError(" password is required!!");
                EditTextPassword.requestFocus();
                return;
            }
            progressBar.setVisibility(View.VISIBLE);
            if ("admin@gmail.com".equals(email1) && "A1234".equals(password1)){
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                Toast.makeText(LoginActivity.this,"Admin Logged in successfully ",Toast.LENGTH_SHORT).show();

            }else{
                Toast.makeText(LoginActivity.this,"Not admin!! ",Toast.LENGTH_SHORT).show();
            }
            progressBar.setVisibility(View.GONE);
        }else
            Login2();
    }
    private void Login2() {
        String email = EditTextEmail.getText().toString().trim();
        String password = EditTextPassword.getText().toString().trim();

        if (email.isEmpty()) {
            EditTextEmail.setError(" email is required!!");
            EditTextEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            EditTextEmail.setError("Please provide a valid email address!");
            EditTextEmail.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            EditTextPassword.setError(" password is required!!");
            EditTextPassword.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (mAuth.getCurrentUser().isEmailVerified()){
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        Toast.makeText(LoginActivity.this,"Logged in successfully",Toast.LENGTH_SHORT).show();
                        finish();
                    }else {
                        Toast.makeText(LoginActivity.this,"Please verify your email",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(LoginActivity.this,"Failed to log in check your credentials and Internet connection",Toast.LENGTH_SHORT).show();
                }
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    public void password(View view) {
        AlertDialog.Builder builder= new AlertDialog.Builder(LoginActivity.this);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_forgot,null);
        EditText emailBox = dialogView.findViewById(R.id.emailBox);


        builder.setView(dialogView);
        AlertDialog dialog = builder.create();

        dialogView.findViewById(R.id.btnReset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmail = emailBox.getText().toString();
                if (TextUtils.isEmpty(userEmail) && !Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()){
                    Toast.makeText(LoginActivity.this,"Enter your registered email address",Toast.LENGTH_SHORT).show();
                    return;
                }
                mAuth.sendPasswordResetEmail(userEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(LoginActivity.this,"Check your email",Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }else {
                            Toast.makeText(LoginActivity.this,"Unable to send, Failed",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        dialogView.findViewById(R.id.cancelButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        if (dialog.getWindow() != null){
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        dialog.show();
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
           if (s.toString().trim().length() >0){
               SignBtn.setVisibility(View.GONE);

            }else{
               SignBtn.setVisibility(View.VISIBLE);
            }

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}