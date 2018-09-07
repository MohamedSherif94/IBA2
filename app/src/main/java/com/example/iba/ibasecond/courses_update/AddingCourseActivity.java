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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.iba.ibasecond.HelperClass;
import com.example.iba.ibasecond.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;

import java.util.HashMap;

public class AddingCourseActivity extends AppCompatActivity {

    HelperClass helperClass = new HelperClass(this);

    private Toolbar mToolbar;

    private String course_type;
    private String course_category;

    private DatabaseReference mDatabase;

    //firebase storage
    private StorageReference mImageStorage;

    //Progress Dialog
    private ProgressDialog mRegProgressDialog;

    private TextInputLayout mCourseCode;
    private TextInputLayout mCourseName;
    private TextInputLayout mCoursePlace;
    private TextInputLayout mCoursePrice;

    private Button mImageCourseBtn;
    private Button mAddCourseBtn;

    private String courseImageUrl = null;
    private static final int GALLERY_PICK = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_course);

        mToolbar = findViewById(R.id.adding_course_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("إضافة كورس جديد");

        course_type = getIntent().getStringExtra("course_type");
        course_category = getIntent().getStringExtra("course_category");
       // Log.v(this.getClass().getSimpleName(), "course_category : "+ course_category);

        mImageStorage = FirebaseStorage.getInstance().getReference();

        mRegProgressDialog = new ProgressDialog(this);

        mCourseCode = findViewById(R.id.adding_course_code_text);
        mCourseName = findViewById(R.id.adding_course_name_text);
        mCoursePlace = findViewById(R.id.adding_course_place_text);
        mCoursePrice = findViewById(R.id.adding_course_price_text);

        mImageCourseBtn = findViewById(R.id.adding_course_image_btn);
        mAddCourseBtn = findViewById(R.id.adding_course_btn);


        mImageCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectCourseImage();
            }
        });

        mAddCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String course_code = mCourseCode.getEditText().getText().toString().trim();
                String course_name = mCourseName.getEditText().getText().toString().trim();
                String course_place = mCoursePlace.getEditText().getText().toString().trim();
                String course_price = mCoursePrice.getEditText().getText().toString().trim();

                if( !TextUtils.isEmpty(course_code) || !TextUtils.isEmpty(course_name) || !TextUtils.isEmpty(course_place) || !TextUtils.isEmpty(course_price)){

                    mRegProgressDialog.setTitle("إضافة الكورس");
                    mRegProgressDialog.setMessage("من فضلك إنتظر حتى يتم إضافة الكورس.");
                    mRegProgressDialog.setCanceledOnTouchOutside(false);
                    mRegProgressDialog.show();

                    addCourse(course_code, course_name, course_place, course_price);
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
                                    Toast.makeText(AddingCourseActivity.this, "Error in uploading image.", Toast.LENGTH_LONG).show();
                                }
                            }
                        });

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {

                Exception error = result.getError();
            }
        }
    }


    private void addCourse(String course_code, String course_name, String course_place, String course_price) {

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
                    mRegProgressDialog.dismiss();
                    Toast.makeText(AddingCourseActivity.this, "تم إضافة الكورس بنجاح", Toast.LENGTH_LONG).show();
                }
                else {
                    mRegProgressDialog.dismiss();
                    Toast.makeText(AddingCourseActivity.this, "حدث خطأ فى إضافة الكورس", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

}
