package com.example.iba.ibasecond.courses_update;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.iba.ibasecond.HelperClass;
import com.example.iba.ibasecond.R;

public class UpdateTypeActivity extends AppCompatActivity {

    HelperClass helperClass = new HelperClass(this);
    private Toolbar mToolbar;

    private String[] coursesList;
    private String course_type;


    private Spinner mCoursesListSpinner;

    private Button mAddnewCourseBtn;
    private Button mupdateCourseBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_type);

        mToolbar = findViewById(R.id.update_type_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("تحديث الكورسات");

        mAddnewCourseBtn = findViewById(R.id.update_type_add_course_btn);
        mupdateCourseBtn = findViewById(R.id.update_type_update_course_btn);


       coursesList = getIntent().getStringArrayExtra(helperClass.COURSES_LIST);
       course_type = getIntent().getStringExtra(helperClass.COURSE_TYPE);

        addItemsOnSpinner();


        mAddnewCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helperClass.openAddingCourseActivity(course_type, String.valueOf(mCoursesListSpinner.getSelectedItem()));
            }
        });

        mupdateCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helperClass.openUpdateCourseActivity(course_type, String.valueOf(mCoursesListSpinner.getSelectedItem()));

            }
        });
    }

    private void addItemsOnSpinner() {

        mCoursesListSpinner = findViewById(R.id.update_type_spinner);

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item, coursesList);

        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mCoursesListSpinner.setAdapter(spinnerArrayAdapter);
    }
}
