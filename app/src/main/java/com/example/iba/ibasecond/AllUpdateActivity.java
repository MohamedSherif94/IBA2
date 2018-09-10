package com.example.iba.ibasecond;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class AllUpdateActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    private Button mLastNewsUpdateBtn;
    private Button mLibraryUpdateBtn;
    private Button mCoursesUpdateBtn;
    private Button mWorksDevelopmentUpdateBtn;


    private HelperClass helperClass = new HelperClass(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_update);

        mToolbar = findViewById(R.id.all_update_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("التحديثات");

        initializeComponents();

        mLastNewsUpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helperClass.openNewsUpdateActivity();
            }
        });

        mLibraryUpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helperClass.openLibraryContentActivity();
            }
        });

        mCoursesUpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helperClass.openCoursesTypeActivity();
            }
        });

        mWorksDevelopmentUpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });





    }

    private void initializeComponents() {
        mLastNewsUpdateBtn = findViewById(R.id.all_update_last_news_btn);
        mLibraryUpdateBtn = findViewById(R.id.all_update_library_btn);
        mCoursesUpdateBtn = findViewById(R.id.all_update_courses_btn);
        mWorksDevelopmentUpdateBtn = findViewById(R.id.all_update_works_development_btn);
    }
}
