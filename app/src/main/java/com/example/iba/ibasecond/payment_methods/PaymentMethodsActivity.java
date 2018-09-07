package com.example.iba.ibasecond.payment_methods;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.iba.ibasecond.R;

public class PaymentMethodsActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    private Button mBankBtn;
    private Button mVodafoneCashBtn;
    private Button mWesternUnionBtn;
    private Button mHeadquarterBtn;

    private LinearLayout mLLBank;
    private LinearLayout mLLVodafoneCash;
    private LinearLayout mLLWesternUnion;

    private TextView mHeadquarterTextview;

    private boolean notVisibleBank = true;
    private boolean notVisibleVodafone = true;
    private boolean notVisibleWestern = true;
    private boolean notVisibleHeadquarter = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_methods);

        mToolbar = findViewById(R.id.payment_methods_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("طرق الدفع");

        initializeComponents();

        mBankBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (notVisibleBank){

                    notVisibleBank = false;
                    mLLBank.setVisibility(View.VISIBLE);
                }
                else{
                    notVisibleBank = true;
                    mLLBank.setVisibility(View.GONE);
                }

            }
        });

        mVodafoneCashBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (notVisibleVodafone){

                    notVisibleVodafone = false;
                    mLLVodafoneCash.setVisibility(View.VISIBLE);
                }
                else{
                    notVisibleVodafone = true;
                    mLLVodafoneCash.setVisibility(View.GONE);
                }

            }
        });

        mWesternUnionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (notVisibleWestern){

                    notVisibleWestern = false;
                    mLLWesternUnion.setVisibility(View.VISIBLE);
                }
                else{
                    notVisibleWestern = true;
                    mLLWesternUnion.setVisibility(View.GONE);
                }

            }
        });

        mHeadquarterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (notVisibleHeadquarter){

                    notVisibleHeadquarter = false;
                    mHeadquarterTextview.setVisibility(View.VISIBLE);
                }
                else{
                    notVisibleHeadquarter = true;
                    mHeadquarterTextview.setVisibility(View.GONE);
                }

            }
        });

    }

    private void initializeComponents() {
        mBankBtn = findViewById(R.id.payment_bank_btn);
        mVodafoneCashBtn = findViewById(R.id.payment_vodafone_cash_btn);
        mWesternUnionBtn = findViewById(R.id.payment_western_union_btn);
        mHeadquarterBtn = findViewById(R.id.payment_headquarter_btn);

        mLLBank = findViewById(R.id.payment_bank_layout);
        mLLVodafoneCash = findViewById(R.id.payment_vodafone_cash_layout);
        mLLWesternUnion = findViewById(R.id.payment_western_union_layout);

        mHeadquarterTextview = findViewById(R.id.payment_headquarter_textview);


    }
}
