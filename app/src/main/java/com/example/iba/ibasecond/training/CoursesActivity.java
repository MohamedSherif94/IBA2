package com.example.iba.ibasecond.training;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.iba.ibasecond.HelperClass;
import com.example.iba.ibasecond.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class CoursesActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private RecyclerView mCoursesList;

    //Progress Dialog
    private ProgressDialog mProgressDialog;

    private String course_type;
    private String course_category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        mToolbar = findViewById(R.id.courses_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("الكورسات المتاحة");

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle("تحميل الكورسات");
        mProgressDialog.setMessage("من فضلك إنتظر حتى يتم تحميل الكورسات.");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();

        course_type = getIntent().getStringExtra(HelperClass.COURSE_TYPE);
        course_category = getIntent().getStringExtra(HelperClass.COURSE_CATEGORY);

        mCoursesList = findViewById(R.id.courses_list);
        mCoursesList.setLayoutManager(new LinearLayoutManager(this));

    }


    @Override
    protected void onStart() {
        super.onStart();
        startListening();

    }

    public void startListening(){
        Query query = FirebaseDatabase.getInstance().getReference()
                .child("Training").child(course_type).child(course_category);

        FirebaseRecyclerOptions<Course> options =
                new FirebaseRecyclerOptions.Builder<Course>()
                        .setQuery(query, Course.class)
                        .build();
        FirebaseRecyclerAdapter adapter = new FirebaseRecyclerAdapter<Course, CourseViewHolder>(options) {
            @Override
            public CourseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                // Create a new instance of the ViewHolder, in this case we are using a custom
                // layout called R.layout.message for each item
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.course_single_layout, parent, false);

                return new CourseViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(CourseViewHolder courseViewHolder, int position, Course course) {
                // Bind the Chat object to the ChatHolder
               // Log.v(CoursesActivity.class.getSimpleName(), "Course Image : "+course.getImage());

                courseViewHolder.setCourseImage(course.getImage());

                courseViewHolder.setCourseName(course.getName());

                courseViewHolder.setCoursePlace(course.getPlace());

                courseViewHolder.setCoursePrice(course.getPrice());
                // ...
            }

            @Override
            public void onDataChanged() {
                super.onDataChanged();
                mProgressDialog.dismiss();
            }
        };
        mCoursesList.setAdapter(adapter);
        adapter.startListening();

    }

}
