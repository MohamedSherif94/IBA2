package com.example.iba.ibasecond.courses_update;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.iba.ibasecond.HelperClass;
import com.example.iba.ibasecond.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;


public class UpdateCourseActivity extends AppCompatActivity {

    HelperClass helperClass = new HelperClass(this);

    private Toolbar mToolbar;

    private DatabaseReference mDatabase;

    private String course_type;
    private String course_category;

    private ArrayList<String> coursesList;
    private Spinner mCoursesListSpinner;

    //Progress Dialog
    private ProgressDialog mProgressDialog;

    private TextInputLayout mCourseName;
    private TextInputLayout mCoursePlace;
    private TextInputLayout mCoursePrice;
    private Button mUpdateCourseBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_course);

        mToolbar = findViewById(R.id.update_course_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("تحديث كورس");

        //Intialization
        coursesList = new ArrayList<>();
        mCoursesListSpinner = findViewById(R.id.update_course_spinner);
        mCourseName = findViewById(R.id.update_course_name_text);
        mCoursePlace = findViewById(R.id.update_course_place_text);
        mCoursePrice = findViewById(R.id.update_course_price_text);
        mUpdateCourseBtn = findViewById(R.id.update_course_btn);

        course_type = getIntent().getStringExtra("course_type");
        course_category = getIntent().getStringExtra("course_category");

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle("تحميل الكورسات");
        mProgressDialog.setMessage("من فضلك إنتظر حتى يتم تحميل الكورسات.");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();

        //Query to get available courses list in database
        Query query = FirebaseDatabase.getInstance().getReference()
                .child("Training").child(course_type).child(course_category);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot child: dataSnapshot.getChildren())
                {
                    String key = child.getKey().toString();
                   // String value = child.getValue().toString();
                    coursesList.add(key);
                }
              /*  for (String course : coursesList){
                    Log.v(UpdateCourseActivity.class.getSimpleName(), "course : " + course);

                }
               */
              addItemsOnSpinner();
              mProgressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                mProgressDialog.dismiss();
                Toast.makeText(UpdateCourseActivity.this, "Error", Toast.LENGTH_LONG).show();
            }
        });

        mUpdateCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        mUpdateCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String course_name = mCourseName.getEditText().getText().toString().trim();
                String course_place = mCoursePlace.getEditText().getText().toString().trim();
                String course_price = mCoursePrice.getEditText().getText().toString().trim();

                if( !TextUtils.isEmpty(course_name) || !TextUtils.isEmpty(course_place) || !TextUtils.isEmpty(course_price)){

                    mProgressDialog.setTitle("تحديل الكورس");
                    mProgressDialog.setMessage("من فضلك إنتظر حتى يتم تحديث الكورس.");
                    mProgressDialog.setCanceledOnTouchOutside(false);
                    mProgressDialog.show();

                    updateCourse(course_name, course_place, course_price);
                }
            }
        });


    }

    private void updateCourse(String course_name, String course_place, String course_price) {
        String course_code = String.valueOf(mCoursesListSpinner.getSelectedItem());
        mDatabase = FirebaseDatabase.getInstance().getReference()
                .child("Training").child(course_type).child(course_category).child(course_code);

        HashMap<String, String> courseMap = new HashMap<>();
        courseMap.put("name", course_name);
        courseMap.put("place", course_place);
        courseMap.put("price", course_price);

        mDatabase.setValue(courseMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()){
                    mProgressDialog.dismiss();
                    Toast.makeText(UpdateCourseActivity.this, "تم تحديث الكورس بنجاح", Toast.LENGTH_LONG).show();
                }
                else {
                    mProgressDialog.dismiss();
                    Toast.makeText(UpdateCourseActivity.this, "حدث خطأ فى تحديث الكورس", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    private void addItemsOnSpinner() {

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item, coursesList);

        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mCoursesListSpinner.setAdapter(spinnerArrayAdapter);
    }

}
