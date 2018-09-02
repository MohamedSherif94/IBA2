package com.example.iba.ibasecond.courses_update;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.iba.ibasecond.R;
import com.example.iba.ibasecond.training.Course;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UpdateCourseActivity extends AppCompatActivity {

    private DatabaseReference mEconomyCoursesDatabase;


    //Progress Dialog
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_course);

        mEconomyCoursesDatabase = FirebaseDatabase.getInstance().getReference()
                .child("Training").child("HeadOrOnline");


        Query query = FirebaseDatabase.getInstance().getReference()
                .child("Training").child("HeadOrOnline").child("Economy");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Log.v(UpdateCourseActivity.class.getSimpleName(), dataSnapshot.getValue().toString());
                Log.v(UpdateCourseActivity.class.getSimpleName(),  dataSnapshot.getKey());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }
}
