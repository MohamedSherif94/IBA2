package com.example.iba.ibasecond;


import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private Handler mHandler = new Handler();

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
       // getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {

                // Check if user is signed in (non-null) and update UI accordingly.
                FirebaseUser currentUser = mAuth.getCurrentUser();

                if (currentUser == null){

                    Intent homeStudentIntent = new Intent(MainActivity.this, HomeStudentActivity.class);
                    startActivity(homeStudentIntent);
                    finish();
                }else {
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        }, 1000); // 1 second
    }

}
