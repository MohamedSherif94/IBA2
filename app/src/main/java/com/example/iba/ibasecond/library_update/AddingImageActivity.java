package com.example.iba.ibasecond.library_update;

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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;

import java.util.HashMap;

public class AddingImageActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    private String library_category;

    private DatabaseReference mDatabase;

    //firebase storage
    private StorageReference mImageStorage;

    //Progress Dialog
    private ProgressDialog mProgressDialog;

    private TextInputLayout mImageCode;
    private TextInputLayout mImageName;
    private TextInputLayout mImageDescription;



    private Button mChooseImageBtn;
    private Button mAddImageBtn;

    private String mImageUrl = null;
    private static final int GALLERY_PICK = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_image);


        mToolbar = findViewById(R.id.adding_image_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("إضافة صورة جديدة");

        library_category = getIntent().getStringExtra(HelperClass.LIBRARY_CATEGORY);

        mImageStorage = FirebaseStorage.getInstance().getReference();

        mProgressDialog = new ProgressDialog(this);

        mImageCode = findViewById(R.id.adding_image_code_text);
        mImageName = findViewById(R.id.adding_image_name_text);
        mImageDescription = findViewById(R.id.adding_image_description_text);

        mChooseImageBtn = findViewById(R.id.adding_image_choose_btn);
        mAddImageBtn = findViewById(R.id.adding_image_btn);


        mChooseImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });

        mAddImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String image_code = mImageCode.getEditText().getText().toString().trim();
                String image_name = mImageName.getEditText().getText().toString().trim();
                String image_description = mImageDescription.getEditText().getText().toString().trim();

                if( !TextUtils.isEmpty(image_code) && !TextUtils.isEmpty(image_name) && !TextUtils.isEmpty(image_description) ){

                    mProgressDialog.setTitle("إضافة الصورة");
                    mProgressDialog.setMessage("من فضلك إنتظر حتى يتم إضافة الصورة...");
                    mProgressDialog.setCanceledOnTouchOutside(false);
                    mProgressDialog.show();

                    addImage(image_code, image_name, image_description );
                }
                else {
                    Toast.makeText(AddingImageActivity.this, "من فضلك أدخل بيانات الصورة كاملة", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void selectImage() {

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

                StorageReference filePath = mImageStorage.child("صور الدفعات").child( HelperClass.random()+ ".jpg");

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
                                            mImageUrl = task.getResult().toString();
                                            //  Log.v(SettingsActivity.class.getSimpleName(), download_url);
                                        }
                                    });

                                } else {
                                    Toast.makeText(AddingImageActivity.this, "حدث خطأ فى تخزين الصورة ع الداتابايز", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {

                Exception error = result.getError();
            }
        }
    }


    private void addImage(String image_code, String image_name, String image_description) {

        mDatabase = FirebaseDatabase.getInstance().getReference()
                .child("المكتبة").child(library_category).child(image_code);

        HashMap<String, String> imageMap = new HashMap<>();
        imageMap.put("image", mImageUrl);
        imageMap.put("name", image_name);
        imageMap.put("description", image_description);

        mDatabase.setValue(imageMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()){
                    mProgressDialog.dismiss();
                    Toast.makeText(AddingImageActivity.this, "تم إضافة الصورة بنجاح", Toast.LENGTH_SHORT).show();
                }
                else {
                    mProgressDialog.dismiss();
                    Toast.makeText(AddingImageActivity.this, "حدث خطأ فى إضافة الصورة", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}
