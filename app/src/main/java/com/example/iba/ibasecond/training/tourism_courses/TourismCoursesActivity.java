package com.example.iba.ibasecond.training.tourism_courses;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.iba.ibasecond.HelperClass;
import com.example.iba.ibasecond.R;

public class TourismCoursesActivity extends AppCompatActivity {

    HelperClass helperClass = new HelperClass(this);
    private Toolbar mToolbar;

    private Button mCairoBtn;

    private static String TOURISM = "سياحة";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tourism_courses);

        mToolbar = findViewById(R.id.tourism_courses_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("سياحة");

        mCairoBtn = findViewById(R.id.tourism_courses_cairo_btn);

        mCairoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helperClass.openCoursesActivity(TOURISM, "القاهرة");
            }
        });
    }
}
