package com.example.iba.ibasecond.last_news;

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
import android.widget.Button;
import android.widget.Toast;

import com.example.iba.ibasecond.HelperClass;
import com.example.iba.ibasecond.R;
import com.example.iba.ibasecond.courses_update.AddingCourseActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;

import java.util.HashMap;

public class NewsUpdateActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    private DatabaseReference mDatabase;

    //firebase storage
    private StorageReference mImageStorage;

    //Progress Dialog
    private ProgressDialog mProgressDialog;

    private TextInputLayout mNewsCode;
    private TextInputLayout mNewsText;

    private Button mNewsImageBtn;
    private Button mNewsPostBtn;

    private String newsImageUrl = null;
    private static final int GALLERY_PICK = 1;;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_update);

        mToolbar = findViewById(R.id.news_update_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("إضافة خبر جديد");

        mImageStorage = FirebaseStorage.getInstance().getReference();

        mProgressDialog = new ProgressDialog(this);

        mNewsCode = findViewById(R.id.news_update_code_text);
        mNewsText = findViewById(R.id.news_update_news_text);

        mNewsImageBtn = findViewById(R.id.news_update_image_btn);
        mNewsPostBtn = findViewById(R.id.news_update_post_btn);

        mNewsImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectCourseImage();
            }
        });
        mNewsPostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String news_code = mNewsCode.getEditText().getText().toString().trim();
                String news_text = mNewsText.getEditText().getText().toString().trim();

                if( !TextUtils.isEmpty(news_code) && !TextUtils.isEmpty(news_text)){

                    mProgressDialog.setTitle("نشر الخبر");
                    mProgressDialog.setMessage("من فضلك إنتظر حتى يتم نشر الخبر.");
                    mProgressDialog.setCanceledOnTouchOutside(false);
                    mProgressDialog.show();

                    postNews(news_code, news_text);
                } else {
                    Toast.makeText(NewsUpdateActivity.this, "من فضلك أدخل كود الخبر والنص.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void postNews(String news_code, String news_text) {

        mDatabase = FirebaseDatabase.getInstance().getReference().child(HelperClass.LAST_NEWS).child(news_code);

        HashMap<String, String> newsMap = new HashMap<>();
        newsMap.put("image", newsImageUrl);
        newsMap.put("text", news_text);

        mDatabase.setValue(newsMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()){
                    mProgressDialog.dismiss();
                    Toast.makeText(NewsUpdateActivity.this, "تم نشر الخبر بنجاح", Toast.LENGTH_LONG).show();
                }
                else {
                    mProgressDialog.dismiss();
                    Toast.makeText(NewsUpdateActivity.this, "حدث خطأ فى نشر الخبر", Toast.LENGTH_LONG).show();
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

                StorageReference filePath = mImageStorage.child("news_images").child( HelperClass.random()+ ".jpg");

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
                                            newsImageUrl = task.getResult().toString();
                                            //  Log.v(SettingsActivity.class.getSimpleName(), download_url);
                                        }
                                    });

                                } else {
                                    Toast.makeText(NewsUpdateActivity.this, "Error in uploading image.", Toast.LENGTH_LONG).show();
                                }
                            }
                        });

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {

                Exception error = result.getError();
            }
        }
    }

}
