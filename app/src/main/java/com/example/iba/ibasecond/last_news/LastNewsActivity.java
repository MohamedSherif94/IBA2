package com.example.iba.ibasecond.last_news;

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
import com.example.iba.ibasecond.training.Course;
import com.example.iba.ibasecond.training.CourseViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class LastNewsActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private RecyclerView mNewsList;

    //Progress Dialog
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_news);

        mToolbar = findViewById(R.id.last_news_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("أخر الأخبار");

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle("تحميل أخر الأخبار");
        mProgressDialog.setMessage("من فضلك إنتظر حتى يتم تحميل الأخبار.");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();

        mNewsList = findViewById(R.id.last_news_list);
        mNewsList.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onStart() {
        super.onStart();
        startListening();

    }

    public void startListening(){
        Query query = FirebaseDatabase.getInstance().getReference().child(HelperClass.LAST_NEWS);

        FirebaseRecyclerOptions<News> options =
                new FirebaseRecyclerOptions.Builder<News>()
                        .setQuery(query, News.class)
                        .build();
        FirebaseRecyclerAdapter adapter = new FirebaseRecyclerAdapter<News, NewsViewHolder>(options) {
            @Override
            public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                // Create a new instance of the ViewHolder, in this case we are using a custom
                // layout called R.layout.message for each item
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.news_single_layout, parent, false);

                return new NewsViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(NewsViewHolder newsViewHolder, int position, News news) {
                // Bind the Chat object to the ChatHolder
                // Log.v(CoursesActivity.class.getSimpleName(), "Course Image : "+course.getImage());

                newsViewHolder.setNewsImage(news.getImage());

                newsViewHolder.setNewsText(news.getText());

                // ...
            }

            @Override
            public void onDataChanged() {
                super.onDataChanged();
                mProgressDialog.dismiss();
            }
        };
        mNewsList.setAdapter(adapter);
        adapter.startListening();

    }
}
