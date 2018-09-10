package com.example.iba.ibasecond.library;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.iba.ibasecond.HelperClass;
import com.example.iba.ibasecond.R;

public class LibraryActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    private Button mFreeBooksBtn;
    private Button mPayedBooksBtn;
    private Button mImagesBtn;
    private Button mVideosBtn;

    private HelperClass helperClass = new HelperClass(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        mToolbar = findViewById(R.id.library_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("المكتبة");

        initializeComponents();

        mFreeBooksBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helperClass.openBooksActivity(HelperClass.FREE_BOOKS);
            }
        });

        mPayedBooksBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helperClass.openBooksActivity(HelperClass.PAYED_BOOKS);
            }
        });


        mImagesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helperClass.openBooksActivity(HelperClass.IMAGES);
            }
        });

        /*
        mVideosBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helperClass.openBooksActivity(HelperClass.VIDEOS);
            }
        });

       */
    }

    private void initializeComponents() {

        mFreeBooksBtn = findViewById(R.id.library_free_books_btn);
        mPayedBooksBtn = findViewById(R.id.library_payed_books_btn);
        mImagesBtn = findViewById(R.id.library_images_btn);
        mVideosBtn = findViewById(R.id.library_video_btn);
    }
}
