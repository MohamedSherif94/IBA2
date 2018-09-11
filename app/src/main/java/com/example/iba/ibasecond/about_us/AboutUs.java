package com.example.iba.ibasecond.about_us;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.iba.ibasecond.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class AboutUs extends AppCompatActivity {

    private Toolbar mToolbar;

    private ImageView mAboutUsImageView;

    private Button mAboutAcademyBtn;
    private Button mVisionMessageBtn;
    private Button mPresidentWordBtn;
    private Button mCooperationContactsBtn;

    private TextView mAboutAcademyTextView;
    private TextView mVisionMessageTextView;
    private TextView mPresidentWordTextView;

    private ImageView mCooperationContactsImageView;

    private boolean notVisibleAboutAcademy = true;
    private boolean notVisibleVisionMessage = true;
    private boolean notVisiblePresidentWord = true;
    private boolean notVisibleCooperationContacts = true;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        mToolbar = findViewById(R.id.about_us_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("معلومات عن الأكاديمية");

        initializeComponents();

        mDatabase = FirebaseDatabase.getInstance().getReference()
                .child("معلومات عن الأكاديمية");

        setData();

        mAboutAcademyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (notVisibleAboutAcademy){

                    notVisibleAboutAcademy = false;
                    mAboutAcademyTextView.setVisibility(View.VISIBLE);
                }
                else{
                    notVisibleAboutAcademy = true;
                    mAboutAcademyTextView.setVisibility(View.GONE);
                }

            }
        });

        mVisionMessageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (notVisibleVisionMessage){

                    notVisibleVisionMessage = false;
                    mVisionMessageTextView.setVisibility(View.VISIBLE);
                }
                else{
                    notVisibleVisionMessage = true;
                    mVisionMessageTextView.setVisibility(View.GONE);
                }

            }
        });

        mPresidentWordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (notVisiblePresidentWord){

                    notVisiblePresidentWord = false;
                    mPresidentWordTextView.setVisibility(View.VISIBLE);
                }
                else{
                    notVisiblePresidentWord = true;
                    mPresidentWordTextView.setVisibility(View.GONE);
                }

            }
        });

        mCooperationContactsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (notVisibleCooperationContacts){

                    notVisibleCooperationContacts = false;
                    mCooperationContactsImageView.setVisibility(View.VISIBLE);
                }
                else{
                    notVisibleCooperationContacts = true;
                    mCooperationContactsImageView.setVisibility(View.GONE);
                }

            }
        });

    }

    private void initializeComponents(){

        mAboutUsImageView = findViewById(R.id.about_us_image_view);

        mAboutAcademyBtn = findViewById(R.id.about_us_about_academy_btn);
        mVisionMessageBtn = findViewById(R.id.about_us_vision_message_btn);
        mPresidentWordBtn = findViewById(R.id.about_us_president_word_btn);
        mCooperationContactsBtn = findViewById(R.id.about_us_cooperation_contacts_btn);

        mAboutAcademyTextView = findViewById(R.id.about_us_about_academy_text);
        mVisionMessageTextView = findViewById(R.id.about_us_vision_message_text);
        mPresidentWordTextView = findViewById(R.id.about_us_president_word_text);

        mCooperationContactsImageView = findViewById(R.id.about_us_cooperation_contacts_image);
    }

    private void setData(){

        final HashMap<String, String> aboutUsMap = new HashMap<>();

        Query query = mDatabase;
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot child: dataSnapshot.getChildren())
                {
                    String key = child.getKey().toString();
                    String value = child.getValue().toString();
                    aboutUsMap.put(key, value);

                    //  Log.v(PaymentMethodsActivity.class.getSimpleName(), key +" : "+value);
                }

                String imageUrl = aboutUsMap.get("header_image");
                String aboutAcademtText = aboutUsMap.get("عن الأكاديمية");
                String visionMessageText = aboutUsMap.get("الرؤية و الرسالة");
                String presidentWordText = aboutUsMap.get("كلمة رئيس مجلس الإدارة");
                String cooperationImageUrl = aboutUsMap.get("صورة جهات التعاون");


                Picasso.get().load(imageUrl).placeholder(R.drawable.adc_nav_logo).into(mAboutUsImageView);
                mAboutAcademyTextView.setText(aboutAcademtText);
                mVisionMessageTextView.setText(visionMessageText);
                mPresidentWordTextView.setText(presidentWordText);
                Picasso.get().load(cooperationImageUrl).placeholder(R.drawable.adc_nav_logo).into(mCooperationContactsImageView);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
