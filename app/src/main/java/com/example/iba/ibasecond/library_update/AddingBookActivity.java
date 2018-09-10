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

public class AddingBookActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    private String library_category;

    private DatabaseReference mDatabase;

    //firebase storage
    private StorageReference mImageStorage;

    //Progress Dialog
    private ProgressDialog mProgressDialog;

    private TextInputLayout mBookCode;
    private TextInputLayout mBookName;
    private TextInputLayout mBookLink;
    private TextInputLayout mBookDescription;

    private Button mImageBookBtn;
    private Button mAddBookBtn;

    private String bookImageUrl = null;
    private static final int GALLERY_PICK = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_book);

        mToolbar = findViewById(R.id.adding_book_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("إضافة كتاب جديد");

        library_category = getIntent().getStringExtra(HelperClass.LIBRARY_CATEGORY);
        // Log.v(this.getClass().getSimpleName(), "course_category : "+ course_category);

        mImageStorage = FirebaseStorage.getInstance().getReference();

        mProgressDialog = new ProgressDialog(this);

        mBookCode = findViewById(R.id.adding_book_code_text);
        mBookName = findViewById(R.id.adding_book_name_text);
        mBookLink = findViewById(R.id.adding_book_link_text);
        mBookDescription = findViewById(R.id.adding_book_description_text);

        mImageBookBtn = findViewById(R.id.adding_book_image_btn);
        mAddBookBtn = findViewById(R.id.adding_book_btn);


        mImageBookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectBookImage();
            }
        });

        mAddBookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String book_code = mBookCode.getEditText().getText().toString().trim();
                String book_name = mBookName.getEditText().getText().toString().trim();
                String book_link = mBookLink.getEditText().getText().toString().trim();
                String book_description = mBookDescription.getEditText().getText().toString().trim();

                if( !TextUtils.isEmpty(book_code) && !TextUtils.isEmpty(book_name) && !TextUtils.isEmpty(book_link) && !TextUtils.isEmpty(book_description)){

                    mProgressDialog.setTitle("إضافة الكتاب");
                    mProgressDialog.setMessage("من فضلك إنتظر حتى يتم إضافة الكتاب.");
                    mProgressDialog.setCanceledOnTouchOutside(false);
                    mProgressDialog.show();

                    addBook(book_code, book_name, book_link, book_description);
                }
                else {
                    Toast.makeText(AddingBookActivity.this, "من فضلك أدخل بيانات الكتاب كاملة", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    private void selectBookImage() {

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

                StorageReference filePath = mImageStorage.child("books_images").child( HelperClass.random()+ ".jpg");

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
                                            bookImageUrl = task.getResult().toString();
                                            //  Log.v(SettingsActivity.class.getSimpleName(), download_url);
                                        }
                                    });

                                } else {
                                    Toast.makeText(AddingBookActivity.this, "Error in uploading image.", Toast.LENGTH_LONG).show();
                                }
                            }
                        });

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {

                Exception error = result.getError();
            }
        }
    }


    private void addBook(String book_code, String book_name, String book_link, String book_description) {

        mDatabase = FirebaseDatabase.getInstance().getReference()
                .child("المكتبة").child(library_category).child(book_code);

        HashMap<String, String> courseMap = new HashMap<>();
        courseMap.put("image", bookImageUrl);
        courseMap.put("name", book_name);
        courseMap.put("url", book_link);
        courseMap.put("description", book_description);

        mDatabase.setValue(courseMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()){
                    mProgressDialog.dismiss();
                    Toast.makeText(AddingBookActivity.this, "تم إضافة الكتاب بنجاح", Toast.LENGTH_LONG).show();
                }
                else {
                    mProgressDialog.dismiss();
                    Toast.makeText(AddingBookActivity.this, "حدث خطأ فى إضافة الكتاب", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

}

