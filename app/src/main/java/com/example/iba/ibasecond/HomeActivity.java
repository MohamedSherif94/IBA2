package com.example.iba.ibasecond;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {


    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private Toolbar mToolbar;

    //Social Media
    private ImageView mImVFacebook;
    private ImageView mImVTwitter;
    private ImageView mImVLinkedin;

    private RelativeLayout mRLServices;

    private Button mChatBtn;


    HelperClass helperClass = new HelperClass(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.setTitleTextColor(Color.WHITE);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        mNavigationView = findViewById(R.id.nav_view);


        mImVFacebook = findViewById(R.id.home_ic_facebook);
        mImVTwitter = findViewById(R.id.home_ic_twitter);
        mImVLinkedin = findViewById(R.id.home_ic_linkedin);

        mRLServices = findViewById(R.id.home_rl_works_development);

        mChatBtn = findViewById(R.id.home_chat_btn);
        mChatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helperClass.openChatHomeActivity();
            }
        });


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

        mRLServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helperClass.openWorksDevelopmentActivity();
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.END);
                return true;

            case R.id.home_chat_logout_btn:
                FirebaseAuth.getInstance().signOut();
                Intent homeStudentIntent = new Intent(HomeActivity.this, HomeStudentActivity.class);
                homeStudentIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(homeStudentIntent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.home_chat_menu, menu);
        return true;
    }







    /*
    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    */
}
