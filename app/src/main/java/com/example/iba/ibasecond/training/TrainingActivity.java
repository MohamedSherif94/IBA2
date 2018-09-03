package com.example.iba.ibasecond.training;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.iba.ibasecond.HelperClass;
import com.example.iba.ibasecond.R;

public class TrainingActivity extends AppCompatActivity {

    HelperClass helperClass = new HelperClass(this);
    private Toolbar mToolbar;

    private Button mHeadOnlineBtn;
    private Button mTourismBtn;
    private Button mCompaniesBtn;
    private Button mUniversitiesBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        mToolbar = findViewById(R.id.training_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Training");

        mHeadOnlineBtn = findViewById(R.id.training_head_online_btn);
        mTourismBtn = findViewById(R.id.training_tourism_btn);
        mCompaniesBtn = findViewById(R.id.training_companies_btn);
        mUniversitiesBtn = findViewById(R.id.training_universities_btn);

        mHeadOnlineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helperClass.openHeadOnlineCoursesActivity();
            }
        });

        mTourismBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helperClass.openTourismCoursesActivity();
            }
        });

    }
}
