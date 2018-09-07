package com.example.iba.ibasecond.courses_update;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.iba.ibasecond.HelperClass;
import com.example.iba.ibasecond.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


public class UpdateCourseActivity extends AppCompatActivity {

    HelperClass helperClass = new HelperClass(this);

    private Toolbar mToolbar;

    private DatabaseReference mDatabase;

    //firebase storage
    private StorageReference mImageStorage;

    private String course_type;
    private String course_category;

    private ArrayList<String> coursesList;
    private Spinner mCoursesListSpinner;

    //Progress Dialog
    private ProgressDialog mProgressDialog;

    private Button mUpdateImageBtn;
    private TextInputLayout mCourseName;
    private TextInputLayout mCoursePlace;
    private TextInputLayout mCoursePrice;
    private Button mUpdateCourseBtn;

    private String courseImageUrl = null;

    private static final int GALLERY_PICK = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_course);

        mToolbar = findViewById(R.id.update_course_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("تحديث كورس");

        mImageStorage = FirebaseStorage.getInstance().getReference();

        //Intialization
        coursesList = new ArrayList<>();
        mUpdateImageBtn = findViewById(R.id.update_course_image_btn);
        mCoursesListSpinner = findViewById(R.id.update_course_spinner);
        mCourseName = findViewById(R.id.update_course_name_text);
        mCoursePlace = findViewById(R.id.update_course_place_text);
        mCoursePrice = findViewById(R.id.update_course_price_text);
        mUpdateCourseBtn = findViewById(R.id.update_course_btn);

        course_type = getIntent().getStringExtra("course_type");
        course_category = getIntent().getStringExtra("course_category");

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle("تحميل الكورسات");
        mProgressDialog.setMessage("من فضلك إنتظر حتى يتم تحميل الكورسات.");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();

        //Query to get available courses list in database
        Query query = FirebaseDatabase.getInstance().getReference()
                .child("Training").child(course_type).child(course_category);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot child: dataSnapshot.getChildren())
                {
                    String key = child.getKey().toString();
                   // String value = child.getValue().toString();
                    coursesList.add(key);
                }
              /*  for (String course : coursesList){
                    Log.v(UpdateCourseActivity.class.getSimpleName(), "course : " + course);

                }
               */
              addItemsOnSpinner();
              mProgressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                mProgressDialog.dismiss();
                Toast.makeText(UpdateCourseActivity.this, "Error", Toast.LENGTH_LONG).show();
            }
        });

        mUpdateImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectCourseImage();
            }
        });

        mUpdateCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String course_name = mCourseName.getEditText().getText().toString().trim();
                String course_place = mCoursePlace.getEditText().getText().toString().trim();
                String course_price = mCoursePrice.getEditText().getText().toString().trim();

                if( !TextUtils.isEmpty(course_name) || !TextUtils.isEmpty(course_place) || !TextUtils.isEmpty(course_price)){

                    mProgressDialog.setTitle("تحديل الكورس");
                    mProgressDialog.setMessage("من فضلك إنتظر حتى يتم تحديث الكورس.");
                    mProgressDialog.setCanceledOnTouchOutside(false);
                    mProgressDialog.show();

                    updateCourse(course_name, course_place, course_price);
                }
            }
        });


    }

    private void selectCourseImage() {

        Intent galleryIntent = new Intent();
        galleryIntent.setType("image/*");
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(Intent.createChooser(galleryIntent, "SELECT IMAGE"), GALLERY_PICK);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_PICK && resultCode == RESULT_OK) {
            Uri imageUri = data.getData();
            // Toast.makeText(SettingsActivity.this, imageUri, Toast.LENGTH_LONG).show();
            // start cropping activity for pre-acquired image saved on the device
            CropImage.activity(imageUri)
                    .setAspectRatio(1, 1)
                    .start(this);

        }

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {

            CropImage.ActivityResult result = CropImage.getActivityResult(data);

            if (resultCode == RESULT_OK) {

                Uri resultUri = result.getUri();

                StorageReference filePath = mImageStorage.child("courses_images").child( helperClass.random()+ ".jpg");

                filePath.putFile(resultUri)
                        .addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                                if (task.isSuccessful()) {
                                    //Toast.makeText(SettingsActivity.this, "Working", Toast.LENGTH_LONG).show();
                                    task.getResult().getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Uri> task) {

                                           // String download_url = task.getResult().toString();
                                            courseImageUrl = task.getResult().toString();
                                            //  Log.v(SettingsActivity.class.getSimpleName(), download_url);
                                        }
                                    });

                                } else {
                                    Toast.makeText(UpdateCourseActivity.this, "Error in uploading image.", Toast.LENGTH_LONG).show();
                                }
                            }
                        });

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {

                Exception error = result.getError();
            }
        }
    }

    private void updateCourse(String course_name, String course_place, String course_price) {
        String course_code = String.valueOf(mCoursesListSpinner.getSelectedItem());
        mDatabase = FirebaseDatabase.getInstance().getReference()
                .child("Training").child(course_type).child(course_category).child(course_code);

        HashMap<String, String> courseMap = new HashMap<>();
        courseMap.put("image", courseImageUrl);
        courseMap.put("name", course_name);
        courseMap.put("place", course_place);
        courseMap.put("price", course_price);

        mDatabase.setValue(courseMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()){
                    mProgressDialog.dismiss();
                    Toast.makeText(UpdateCourseActivity.this, "تم تحديث الكورس بنجاح", Toast.LENGTH_LONG).show();
                }
                else {
                    mProgressDialog.dismiss();
                    Toast.makeText(UpdateCourseActivity.this, "حدث خطأ فى تحديث الكورس", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    private void addItemsOnSpinner() {

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item, coursesList);

        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mCoursesListSpinner.setAdapter(spinnerArrayAdapter);
    }


}
