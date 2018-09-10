package com.example.iba.ibasecond.library_update;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.iba.ibasecond.HelperClass;
import com.example.iba.ibasecond.R;

public class LibraryContentActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    private Button mFreeBooksBtn;
    private Button mPayedBooksBtn;
    private Button mImagesBtn;
    private Button mVideosBtn;

    private HelperClass helperClass = new HelperClass(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library_content);

        mToolbar = findViewById(R.id.library_content_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("تحديث المكتبة");

        initializeComponents();

        mFreeBooksBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helperClass.openLibraryUpdateTypeActivity(HelperClass.FREE_BOOKS, "book");
            }
        });

        mPayedBooksBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helperClass.openLibraryUpdateTypeActivity(HelperClass.PAYED_BOOKS, "book");
            }
        });


        mImagesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helperClass.openLibraryUpdateTypeActivity(HelperClass.IMAGES, "image");
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

        mFreeBooksBtn = findViewById(R.id.library_content_free_books_btn);
        mPayedBooksBtn = findViewById(R.id.library_content_payed_books_btn);
        mImagesBtn = findViewById(R.id.library_content_images_btn);
        mVideosBtn = findViewById(R.id.library_content_video_btn);
    }
}
