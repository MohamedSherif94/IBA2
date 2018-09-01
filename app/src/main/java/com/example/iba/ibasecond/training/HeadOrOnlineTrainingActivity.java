package com.example.iba.ibasecond.training;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.iba.ibasecond.HelperClass;
import com.example.iba.ibasecond.R;

public class HeadOrOnlineTrainingActivity extends AppCompatActivity {

    HelperClass helperClass = new HelperClass(this);
    private Toolbar mToolbar;

    private boolean notVisible = true;

    private Button mEconomyBtn;
    private Button mStockMarketBtn;

    private LinearLayout mLLayoutStockMarket;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_head_or_online_training);

        mToolbar = findViewById(R.id.head_online_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("التدريب");

        mEconomyBtn = findViewById(R.id.head_online_econonmy_btn);
        mStockMarketBtn = findViewById(R.id.head_online_stock_market_btn);

        mLLayoutStockMarket = findViewById(R.id.head_online_stock_market_layout);

        mEconomyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helperClass.openEconomyCoursesActivity();
            }
        });

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

    }

}
