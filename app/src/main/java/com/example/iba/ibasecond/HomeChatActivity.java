package com.example.iba.ibasecond;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeChatActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Toolbar mToolbar;

    private ViewPager mViewPager;

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_chat);

        mAuth = FirebaseAuth.getInstance();
        mToolbar = findViewById(R.id.home_chat_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Chat");

        //tabs
        mViewPager = findViewById(R.id.home_chat_tab_pager);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager.setAdapter(mSectionsPagerAdapter);

        mTabLayout = findViewById(R.id.home_chat_tabs);
        mTabLayout.setupWithViewPager(mViewPager);

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser == null){

            sendToStartActivity();
        }
    }

    private void sendToStartActivity() {
        Intent startIntent = new Intent(HomeChatActivity.this, StartActivity.class);
        startActivity(startIntent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.home_chat_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        if (item.getItemId() == R.id.home_chat_logout_btn){
            FirebaseAuth.getInstance().signOut();
            sendToStartActivity();
        }

        /*
        if (item.getItemId() == R.id.home_chat_account_settings_btn){
            Intent settingsIntent = new Intent(HomeChatActivity.this, SettingsActivity.class);
            startActivity(settingsIntent);
        }

        if (item.getItemId() == R.id.home_chat_all_users){
            Intent usersIntent = new Intent(HomeChatActivity.this, UsersActivity.class);
            startActivity(usersIntent);
        }
        */
        return true;
    }
}
