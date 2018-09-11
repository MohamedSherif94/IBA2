package com.example.iba.ibasecond.payment_methods;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.iba.ibasecond.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class PaymentMethodsActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    private Button mBankBtn;
    private Button mVodafoneCashBtn;
    private Button mWesternUnionBtn;
    private Button mHeadquarterBtn;

    private LinearLayout mLLBank;
    private LinearLayout mLLVodafoneCash;
    private LinearLayout mLLWesternUnion;

    private TextView mCairoBankTextview;
    private TextView mMasrBankTextview;
    private TextView mVCNo1Textview;
    private TextView mVCNo2Textview;
    private TextView mWesternUnionTextview;
    private TextView mHeadquarterTextview;

    private boolean notVisibleBank = true;
    private boolean notVisibleVodafone = true;
    private boolean notVisibleWestern = true;
    private boolean notVisibleHeadquarter = true;

    private DatabaseReference mDatabase;

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

    @Override
    protected void onStart() {
        super.onStart();

        mDatabase = FirebaseDatabase.getInstance().getReference()
                .child("طرق الدفع");

        final HashMap<String, String> vodCashMap = new HashMap<>();
        vodCashMap.clear();

        Query query = mDatabase.child("فودافون كاش");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot child: dataSnapshot.getChildren())
                {
                    String key = child.getKey().toString();
                    String value = child.getValue().toString();
                    vodCashMap.put(key, value);

                  //  Log.v(PaymentMethodsActivity.class.getSimpleName(), key +" : "+value);
                }

                mVCNo1Textview.setText(vodCashMap.get("number1"));
                mVCNo2Textview.setText(vodCashMap.get("number2"));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        setBanksNumber();
        setWesternNumber();
        setHeadquarterAddress();
    }

    private void setValuesOnTextViews(HashMap<String, String> imageMap) {
    }

    private void initializeComponents() {
        mBankBtn = findViewById(R.id.payment_bank_btn);
        mVodafoneCashBtn = findViewById(R.id.payment_vodafone_cash_btn);
        mWesternUnionBtn = findViewById(R.id.payment_western_union_btn);
        mHeadquarterBtn = findViewById(R.id.payment_headquarter_btn);

        mLLBank = findViewById(R.id.payment_bank_layout);
        mLLVodafoneCash = findViewById(R.id.payment_vodafone_cash_layout);
        mLLWesternUnion = findViewById(R.id.payment_western_union_layout);

        mCairoBankTextview = findViewById(R.id.payment_methods_cairo_bank_no);
        mMasrBankTextview = findViewById(R.id.payment_methods_masr_bank_no);

        mVCNo1Textview = findViewById(R.id.payment_methods_v_c_no1);
        mVCNo2Textview = findViewById(R.id.payment_methods_v_c_no2);

        mWesternUnionTextview = findViewById(R.id.payment_methods_western_union);

        mHeadquarterTextview = findViewById(R.id.payment_headquarter_textview);

    }

    private void setBanksNumber(){
        final HashMap<String, String> banksMap = new HashMap<>();
        banksMap.clear();

        Query query = mDatabase.child("البنك");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot child: dataSnapshot.getChildren())
                {
                    String key = child.getKey().toString();
                    String value = child.getValue().toString();
                    banksMap.put(key, value);

                    //  Log.v(PaymentMethodsActivity.class.getSimpleName(), key +" : "+value);
                }

                mCairoBankTextview.setText(banksMap.get("بنك القاهرة"));
                mMasrBankTextview.setText(banksMap.get("بنك مصر"));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void setWesternNumber(){
        final HashMap<String, String> banksMap = new HashMap<>();
        banksMap.clear();

        Query query = mDatabase.child("western union");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot child: dataSnapshot.getChildren())
                {
                    String key = child.getKey().toString();
                    String value = child.getValue().toString();
                    banksMap.put(key, value);

                    //  Log.v(PaymentMethodsActivity.class.getSimpleName(), key +" : "+value);
                }

                mWesternUnionTextview.setText(banksMap.get("text"));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void setHeadquarterAddress(){
        final HashMap<String, String> banksMap = new HashMap<>();
        banksMap.clear();

        Query query = mDatabase.child("بمقر الأكاديمية");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot child: dataSnapshot.getChildren())
                {
                    String key = child.getKey().toString();
                    String value = child.getValue().toString();
                    banksMap.put(key, value);

                    //  Log.v(PaymentMethodsActivity.class.getSimpleName(), key +" : "+value);
                }

                mHeadquarterTextview.setText(banksMap.get("text"));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
