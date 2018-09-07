package com.example.iba.ibasecond.works_development;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.iba.ibasecond.R;

public class WorksDevelopmentActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    private Button mCompaniesEstablishmentBtn;
    private Button mMarketingBtn;
    private Button mSocialMediaBtn;

    private LinearLayout mLLMarketing;
    private LinearLayout mLLSocialMedia;

    private Boolean notVisibleMarketing = true;
    private Boolean notVisibleSocialMedia = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_works_development);

        mToolbar = findViewById(R.id.works_development_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("تطوير الأعمال");

        initializeComponents();

        mCompaniesEstablishmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        mMarketingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (notVisibleMarketing){

                    notVisibleMarketing = false;
                    mLLMarketing.setVisibility(View.VISIBLE);
                }
                else{
                    notVisibleMarketing = true;
                    mLLMarketing.setVisibility(View.GONE);
                }

            }
        });

        mSocialMediaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (notVisibleSocialMedia){

                    notVisibleSocialMedia = false;
                    mLLSocialMedia.setVisibility(View.VISIBLE);
                }
                else{
                    notVisibleSocialMedia = true;
                    mLLSocialMedia.setVisibility(View.GONE);
                }

            }
        });



    }

    private void initializeComponents() {
        mCompaniesEstablishmentBtn = findViewById(R.id.works_development_comp_establish_btn);
        mMarketingBtn = findViewById(R.id.works_development_marketing_btn);
        mSocialMediaBtn = findViewById(R.id.works_development_social_media_btn);

        mLLMarketing = findViewById(R.id.works_development_marketing_layout);
        mLLSocialMedia = findViewById(R.id.works_development_social_media_layout);
    }
}
