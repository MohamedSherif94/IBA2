package com.example.iba.ibasecond.training.head_online_courses;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.iba.ibasecond.HelperClass;
import com.example.iba.ibasecond.R;

public class HeadOrOnlineCoursesActivity extends AppCompatActivity {

    HelperClass helperClass = new HelperClass(this);
    private Toolbar mToolbar;

    private boolean notVisible = true;

    private Button mEconomyBtn;
    private Button mStockMarketBtn;
    private Button mFinancialAnalysisBtn;
    private Button mTechnicalAnalysisBtn;
    private Button mBanksBtn;
    private Button mManagementBtn;
    private Button mInterCertificateBtn;
    private Button mMarketingBtn;
    private Button mComputersBtn;
    private Button mLanguagesBtn;

    private LinearLayout mLLayoutStockMarket;

    private static String HeadOrOnline = "بالمقر أو أونلاين";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_head_or_online_courses);

        mToolbar = findViewById(R.id.head_online_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("تدريب فى الأكاديمية أو أونلاين");

        initializeComponents();

        mStockMarketBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (notVisible){
                    notVisible = false;
                    mLLayoutStockMarket.setVisibility(View.VISIBLE);
                }
                else{
                    notVisible = true;
                    mLLayoutStockMarket.setVisibility(View.GONE);
                }

            }
        });

        mEconomyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helperClass.openCoursesActivity(HeadOrOnline, "الإقتصاد");
            }
        });

        mFinancialAnalysisBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helperClass.openCoursesActivity(HeadOrOnline, "التحليل المالي");
            }
        });

        mTechnicalAnalysisBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helperClass.openCoursesActivity(HeadOrOnline, "التحليل الفني");
            }
        });

        mBanksBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helperClass.openCoursesActivity(HeadOrOnline, "البنوك");
            }
        });

        mManagementBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helperClass.openCoursesActivity(HeadOrOnline, "الإدارة");
            }
        });

        mInterCertificateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helperClass.openCoursesActivity(HeadOrOnline, "الشهادة الدولية");
            }
        });

        mMarketingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helperClass.openCoursesActivity(HeadOrOnline, "التسويق");
            }
        });

        mComputersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helperClass.openCoursesActivity(HeadOrOnline, "كورسات الكمبيوتر");
            }
        });

        mLanguagesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helperClass.openCoursesActivity(HeadOrOnline, "اللغات");
            }
        });

    }

    private void initializeComponents(){
        mEconomyBtn = findViewById(R.id.head_online_econonmy_btn);

        mStockMarketBtn = findViewById(R.id.head_online_stock_market_btn);
        mLLayoutStockMarket = findViewById(R.id.head_online_stock_market_layout);
        mFinancialAnalysisBtn = findViewById(R.id.head_online_financial_analysis_btn);
        mTechnicalAnalysisBtn = findViewById(R.id.head_online_technical_analysis_btn);

        mBanksBtn = findViewById(R.id.head_online_banks_btn);
        mManagementBtn = findViewById(R.id.head_online_management_btn);
        mInterCertificateBtn = findViewById(R.id.head_online_international_certifiacte_btn);
        mMarketingBtn = findViewById(R.id.head_online_marketing_btn);
        mComputersBtn = findViewById(R.id.head_online_computer_courses_btn);
        mLanguagesBtn = findViewById(R.id.head_online_languages_btn);

    }

}
