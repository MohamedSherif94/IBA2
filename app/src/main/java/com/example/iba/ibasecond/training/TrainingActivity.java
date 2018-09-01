package com.example.iba.ibasecond.training;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.iba.ibasecond.HelperClass;
import com.example.iba.ibasecond.R;

public class TrainingActivity extends AppCompatActivity {

    HelperClass helperClass = new HelperClass(this);
    private Toolbar mToolbar;

    private Button mHeadOnlineBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        mToolbar = findViewById(R.id.training_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Training");

        mHeadOnlineBtn = findViewById(R.id.training_head_online_btn);
        mHeadOnlineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helperClass.openHeadOnlineTrainingActivity();
            }
        });

    }
}
