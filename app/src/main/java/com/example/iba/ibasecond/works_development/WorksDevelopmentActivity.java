package com.example.iba.ibasecond.works_development;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.iba.ibasecond.R;

public class WorksDevelopmentActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_works_development);

        mToolbar = findViewById(R.id.works_development_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Works Development");


    }
}
