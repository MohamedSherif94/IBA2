package com.example.iba.ibasecond;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    private FirebaseAuth mAuth;

    private TextInputLayout mEmail;
    private TextInputLayout mPassword;
    private Button mLoginBtn;

    //Progress Dialog
    private ProgressDialog mLogProgressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mToolbar = findViewById(R.id.login_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Login");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAuth = FirebaseAuth.getInstance();

        mEmail = findViewById(R.id.login_email);
        mPassword = findViewById(R.id.login_password);
        mLoginBtn = findViewById(R.id.login_btn);

        mLogProgressDialog = new ProgressDialog(this);

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = mEmail.getEditText().getText().toString().trim();
                String password = mPassword.getEditText().getText().toString().trim();

                if( !TextUtils.isEmpty(email) || !TextUtils.isEmpty(password)){

                    mLogProgressDialog.setTitle("Logging in");
                    mLogProgressDialog.setMessage("Please wait while we check your credentials");
                    mLogProgressDialog.setCanceledOnTouchOutside(false);
                    mLogProgressDialog.show();

                    login_user(email, password);
                }
            }
        });




    }

    private void login_user(String email, String password) {

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){

                    mLogProgressDialog.dismiss();

                    Intent homeChatIntent = new Intent(LoginActivity.this, HomeChatActivity.class);
                    //homeChatIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(homeChatIntent);
                    finish();
                }else {

                    mLogProgressDialog.hide();

                    //FirebaseAuthException e = (FirebaseAuthException )task.getException();
                    Log.v(String.valueOf(LoginActivity.this), "Failed Registration: "+task.getException());
                    Toast.makeText(LoginActivity.this, "Invalid Email or Password. Please try again.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
