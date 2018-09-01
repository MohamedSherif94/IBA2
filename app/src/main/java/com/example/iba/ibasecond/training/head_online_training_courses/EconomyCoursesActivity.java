package com.example.iba.ibasecond.training.head_online_training_courses;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.iba.ibasecond.R;
import com.example.iba.ibasecond.training.Course;
import com.example.iba.ibasecond.training.CourseViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class EconomyCoursesActivity extends AppCompatActivity {

    private RecyclerView mEconomyCoursesList;

    private DatabaseReference mEconomyCoursesDatabase;

    //Progress Dialog
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_economy_courses);

        mEconomyCoursesDatabase = FirebaseDatabase.getInstance().getReference()
                .child("Training").child("HeadOrOnline").child("Economy");

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle("Getting Courses");
        mProgressDialog.setMessage("Please wait while we load all Courses");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();


        mEconomyCoursesList = findViewById(R.id.economy_courses_list);
        mEconomyCoursesList.setLayoutManager(new LinearLayoutManager(this));

    }


    @Override
    protected void onStart() {
        super.onStart();
        startListening();

    }

    public void startListening(){
        Query query = FirebaseDatabase.getInstance().getReference()
                .child("Training").child("HeadOrOnline").child("Economy");

        Log.v(EconomyCoursesActivity.class.getSimpleName(), "Query : "+ query.toString());


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
               // Log.v(EconomyCoursesActivity.class.getSimpleName(), "Course Image : "+course.getImage());

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
        mEconomyCoursesList.setAdapter(adapter);
        adapter.startListening();

    }

}
