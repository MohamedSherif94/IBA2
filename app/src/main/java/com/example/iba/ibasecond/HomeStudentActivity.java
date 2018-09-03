package com.example.iba.ibasecond;

import android.graphics.Color;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class HomeStudentActivity extends AppCompatActivity {


    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private Toolbar mToolbar;

    private ImageView mImVLogin;

    //Social Media
    private ImageView mImVFacebook;
    private ImageView mImVTwitter;
    private ImageView mImVLinkedin;

    private RelativeLayout mRLTraining;
    private RelativeLayout mRLWorksDevlopment;
    private RelativeLayout mRLLibrary;

    HelperClass helperClass = new HelperClass(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_student);

        mToolbar = findViewById(R.id.home_student_toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.setTitleTextColor(Color.WHITE);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);

        mDrawerLayout = findViewById(R.id.home_student_drawer_layout);
        mNavigationView = findViewById(R.id.home_student_nav_view);

        mImVLogin = findViewById(R.id.home_student_login_btn);

        mImVFacebook = findViewById(R.id.home_ic_facebook);
        mImVTwitter = findViewById(R.id.home_ic_twitter);
        mImVLinkedin = findViewById(R.id.home_ic_linkedin);

        mRLWorksDevlopment = findViewById(R.id.home_student_rl_works_development);
        mRLTraining = findViewById(R.id.home_student_rl_training);
        mRLLibrary = findViewById(R.id.home_student_rl_library);

        mNavigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        return true;
                    }
                });


        mImVLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                helperClass.openLoginActivity();
            }
        });

        mImVFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helperClass.openFacebookPage();
            }
        });

        mImVTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helperClass.openTwitterPage();
            }
        });

        mImVLinkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helperClass.openLinkedInPage();
            }
        });

        mRLWorksDevlopment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helperClass.openWorksDevelopmentActivity();
            }
        });

        mRLTraining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helperClass.openTrainingActivity();
            }
        });

        mRLLibrary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helperClass.openLibraryActivity();
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.END);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
