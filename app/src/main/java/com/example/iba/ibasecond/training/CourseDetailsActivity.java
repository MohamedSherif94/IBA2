package com.example.iba.ibasecond.training;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iba.ibasecond.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class CourseDetailsActivity extends AppCompatActivity {

    private String mCourseImageUrl;
    private String mCourseName;
    private String mCourseDescription;

    private String mRegisterationUrl;

    private Toolbar mToolbar;

    private ImageView mCourseImageView;
    private TextView mCourseNameTextView;
    private TextView mCourseDescriptionTextView;
    private Button mRegisterBtn;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);

        mToolbar = findViewById(R.id.course_details_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("تفاصيل الكورس");

        initializeComponents();

        mDatabase = FirebaseDatabase.getInstance().getReference()
                .child("لينك التسجيل");

        mCourseImageUrl = getIntent().getStringExtra("course_image");
        mCourseName = getIntent().getStringExtra("course_name");
        mCourseDescription = getIntent().getStringExtra("course_description");

        Picasso.get().load(mCourseImageUrl).placeholder(R.drawable.account_im).into(mCourseImageView);
        mCourseNameTextView.setText(mCourseName);
        mCourseDescriptionTextView.setText(mCourseDescription);

        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openRegisterationPage(mRegisterationUrl);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        getRegisterationUrl();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void initializeComponents() {
        mCourseImageView = findViewById(R.id.course_details_image_view);
        mCourseNameTextView = findViewById(R.id.course_details_name_text);
        mCourseDescriptionTextView = findViewById(R.id.course_details_description_text);
        mRegisterBtn = findViewById(R.id.course_details_register_btn);
    }

    public void openRegisterationPage(String url) {

        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(CourseDetailsActivity.this, "عفوا. هذا الكورس ليس متاح الان.", Toast.LENGTH_SHORT).show();
        }
    }

    private void getRegisterationUrl() {

        Query query = mDatabase;
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot child: dataSnapshot.getChildren())
                {
                     mRegisterationUrl = child.getValue().toString();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
