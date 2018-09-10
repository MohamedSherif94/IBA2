package com.example.iba.ibasecond.library_update;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.iba.ibasecond.HelperClass;
import com.example.iba.ibasecond.R;

public class LibraryUpdateTypeActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    private Button mAddnewBookBtn;
    private Button mupdateBookBtn;

    private String library_category;
    private String book_or_image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library_update_type);

        mToolbar = findViewById(R.id.library_update_type_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("تحديث المكتبة");

        library_category = getIntent().getStringExtra(HelperClass.LIBRARY_CATEGORY);
        book_or_image = getIntent().getStringExtra(HelperClass.BOOK_OR_IMAGE);

        mAddnewBookBtn = findViewById(R.id.library_update_type_adding_btn);
        mupdateBookBtn = findViewById(R.id.library_update_type_update_btn);

        if (book_or_image.equals("book")){
            mAddnewBookBtn.setText(R.string.adding_new_book);
            mupdateBookBtn.setText(R.string.update_book);
        }else {
            mAddnewBookBtn.setText(R.string.adding_new_image);
            mupdateBookBtn.setText(R.string.update_image);
        }

        mAddnewBookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (book_or_image.equals("book"))
                    openAddingBookActivity();
                else
                    openAddingImageActivity();
            }
        });
        mupdateBookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (book_or_image.equals("book"))
                     openUpdateBookActivity();
                else
                     openUpdateImageActivity();
            }
        });

    }

    private void openAddingBookActivity() {
        Intent intent = new Intent(LibraryUpdateTypeActivity.this, AddingBookActivity.class);
        intent.putExtra(HelperClass.LIBRARY_CATEGORY, library_category);
        startActivity(intent);
    }

    private void openUpdateBookActivity() {
        Intent intent = new Intent(LibraryUpdateTypeActivity.this, UpdateBookActivity.class);
        intent.putExtra(HelperClass.LIBRARY_CATEGORY, library_category);
        startActivity(intent);
    }

    private void openAddingImageActivity() {
        Intent intent = new Intent(LibraryUpdateTypeActivity.this, AddingImageActivity.class);
        intent.putExtra(HelperClass.LIBRARY_CATEGORY, library_category);
        startActivity(intent);
    }

    private void openUpdateImageActivity() {
        Intent intent = new Intent(LibraryUpdateTypeActivity.this, UpdateImageActivity.class);
        intent.putExtra(HelperClass.LIBRARY_CATEGORY, library_category);
        startActivity(intent);
    }
}
