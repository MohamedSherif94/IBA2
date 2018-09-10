package com.example.iba.ibasecond;

import android.content.Intent;
import android.graphics.Color;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.daasuu.bl.BubbleLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class HomeActivity extends AppCompatActivity {


    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;

    private Toolbar mToolbar;

    private ImageView mLogoImageView;
    //Social Media
    private ImageView mImVFacebook;
    private ImageView mImVTwitter;
    private ImageView mImVLinkedin;

    private RelativeLayout mRLLastNews;
    private RelativeLayout mRLPaymentMethods;
    private RelativeLayout mRLTraining;
    private RelativeLayout mRLWorksDevlopment;
    private RelativeLayout mRLLibrary;

    private RelativeLayout mRLUpdates;

    private ImageView mIcLastNews;
    private ImageView mIcAboutUs;
    private ImageView mIcLibrary;
    private ImageView mIcTraining;
    private ImageView mIcWorksDevelopment;
    private ImageView mIcPaymentMethods;
    private ImageView mIcEmployees;
    private ImageView mIcUpdate;


    private BubbleLayout mHelpBubbleLayout;


    HelperClass helperClass = new HelperClass(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.setTitleTextColor(Color.WHITE);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle("أكاديمية إستثمار و بنوك");
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);

        initializeComponents();

        /*
        mHelpBubbleLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helperClass.openChatHomeActivity();
            }
        });
        */


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
                        int id = menuItem.getItemId();
                        switch (id){
                            case R.id.nav_home_last_news_ic:
                                helperClass.openLastNewsActivity();
                                break;

                            case R.id.nav_home_about_us_ic:
                                break;

                            case R.id.nav_home_library_ic:
                                helperClass.openLibraryActivity();


                            case R.id.nav_home_training_ic:
                                helperClass.openTrainingActivity();
                                break;

                            case R.id.nav_home_works_development_ic:
                                helperClass.openWorksDevelopmentActivity();


                            case R.id.nav_home_payment_methods_ic:
                                helperClass.openPaymentMethodsActivity();
                                break;

                            case R.id.nav_home_employees_ic:
                                break;

                            case R.id.nav_home_update_ic:
                                helperClass.openAllUpdateActivity();
                                break;
                        }

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

        mRLLastNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helperClass.openLastNewsActivity();
            }
        });

        mRLPaymentMethods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helperClass.openPaymentMethodsActivity();
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

        mRLUpdates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helperClass.openAllUpdateActivity();
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
                mDrawerLayout.openDrawer(GravityCompat.START);
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

    private void initializeComponents(){
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mNavigationView = findViewById(R.id.nav_view);

        mLogoImageView = findViewById(R.id.home_logo_image_view);

        mImVFacebook = findViewById(R.id.home_ic_facebook);
        mImVTwitter = findViewById(R.id.home_ic_twitter);
        mImVLinkedin = findViewById(R.id.home_ic_linkedin);

        mRLLastNews = findViewById(R.id.home_rl_last_news);
        mRLPaymentMethods = findViewById(R.id.home_rl_payment);
        mRLWorksDevlopment = findViewById(R.id.home_rl_works_development);
        mRLTraining = findViewById(R.id.home_rl_training);
        mRLUpdates = findViewById(R.id.home_rl_updates);
        mRLLibrary = findViewById(R.id.home_rl_library);

        mIcLastNews = findViewById(R.id.home_ic_last_news);
        mIcAboutUs = findViewById(R.id.home_ic_about_us);
        mIcLibrary = findViewById(R.id.home_ic_library);
        mIcTraining = findViewById(R.id.home_ic_training);
        mIcWorksDevelopment = findViewById(R.id.home_ic_works_dev);
        mIcPaymentMethods = findViewById(R.id.home_ic_payment);
        mIcEmployees = findViewById(R.id.home_ic_staff);
        mIcUpdate = findViewById(R.id.home_ic_updates);

        mHelpBubbleLayout = findViewById(R.id.home_help_bubble_layout);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Picasso.get().load(HelperClass.acd_logo).into(mLogoImageView);
        Picasso.get().load(HelperClass.ic_last_news).into(mIcLastNews);
        Picasso.get().load(HelperClass.ic_about_us).into(mIcAboutUs);
        Picasso.get().load(HelperClass.ic_library).into(mIcLibrary);
        Picasso.get().load(HelperClass.ic_training).into(mIcTraining);
        Picasso.get().load(HelperClass.ic_works_development).into(mIcWorksDevelopment);
        Picasso.get().load(HelperClass.ic_payment_method).into(mIcPaymentMethods);
        Picasso.get().load(HelperClass.ic_employees).into(mIcEmployees);
        Picasso.get().load(HelperClass.ic_update).into(mIcUpdate);
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
