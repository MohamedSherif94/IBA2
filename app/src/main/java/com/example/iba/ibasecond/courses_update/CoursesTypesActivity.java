package com.example.iba.ibasecond.courses_update;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.iba.ibasecond.HelperClass;
import com.example.iba.ibasecond.R;

public class CoursesTypesActivity extends AppCompatActivity {

    HelperClass helperClass = new HelperClass(this);
    private Toolbar mToolbar;

    private Button mHeadOnlineBtn;


    private String[] mHeadOrOnlineCoursesList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses_types);

        mToolbar = findViewById(R.id.update_courses_types_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("تحديث الكورسات");

        mHeadOrOnlineCoursesList = getResources().getStringArray(R.array.head_or_online_courses);

        mHeadOnlineBtn = findViewById(R.id.update_courses_types_head_online_btn);


        mHeadOnlineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helperClass.openUpdateTypeActivity(mHeadOrOnlineCoursesList);
            }
        });

    }
}
