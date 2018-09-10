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

public class UpdateBookActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    private DatabaseReference mDatabase;

    //firebase storage
    private StorageReference mImageStorage;

    private String library_category;

    private ArrayList<String> booksList;
    private Spinner mBooksListSpinner;

    //Progress Dialog
    private ProgressDialog mProgressDialog;

    private Button mUpdateImageBtn;
    private TextInputLayout mBookName;
    private TextInputLayout mBookUrl;
    private TextInputLayout mBookDescription;
    private Button mUpdateBookBtn;

    private String bookImageUrl = "";

    private static final int GALLERY_PICK = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_book);

        mToolbar = findViewById(R.id.update_book_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("تحديث كتاب");

        mImageStorage = FirebaseStorage.getInstance().getReference();

        //Intialization
        booksList = new ArrayList<>();
        mUpdateImageBtn = findViewById(R.id.update_book_image_btn);
        mBooksListSpinner = findViewById(R.id.update_book_spinner);
        mBookName = findViewById(R.id.update_book_name_text);
        mBookUrl = findViewById(R.id.update_book_url_text);
        mBookDescription = findViewById(R.id.update_book_description_text);
        mUpdateBookBtn = findViewById(R.id.update_book_btn);

        library_category = getIntent().getStringExtra(HelperClass.LIBRARY_CATEGORY);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle("تحميل الكتب");
        mProgressDialog.setMessage("من فضلك إنتظر حتى يتم تحميل الكتب.");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();

        //Query to get available courses list in database
        Query query = FirebaseDatabase.getInstance().getReference()
                .child("المكتبة").child(library_category);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot child: dataSnapshot.getChildren())
                {
                    String key = child.getKey().toString();
                    // String value = child.getValue().toString();
                    booksList.add(key);
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
                Toast.makeText(UpdateBookActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });

        mUpdateImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectBookImage();
            }
        });

        mUpdateBookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String book_name = mBookName.getEditText().getText().toString().trim();
                String book_url = mBookUrl.getEditText().getText().toString().trim();
                String book_description = mBookDescription.getEditText().getText().toString().trim();

                if( !TextUtils.isEmpty(book_name) || !TextUtils.isEmpty(book_url) || !TextUtils.isEmpty(book_description)){

                    mProgressDialog.setTitle("تحديث الكتاب");
                    mProgressDialog.setMessage("من فضلك إنتظر حتى يتم تحديث الكتاب...");
                    mProgressDialog.setCanceledOnTouchOutside(false);
                    mProgressDialog.show();

                    updateBook(book_name, book_url, book_description);
                } else {
                    Toast.makeText(UpdateBookActivity.this, "أدخل ع الأقل قيمة واحدة للتحديث", Toast.LENGTH_SHORT).show();
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
                                    Toast.makeText(UpdateBookActivity.this, "Error in uploading image.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {

                Exception error = result.getError();
            }
        }
    }

    private void updateBook(final String book_name, final String book_url, final String book_description) {

        final String book_code = String.valueOf(mBooksListSpinner.getSelectedItem());
        mDatabase = FirebaseDatabase.getInstance().getReference()
                .child("المكتبة").child(library_category).child(book_code);

         final HashMap<String, String> bookMap = new HashMap<>();
         bookMap.clear();

        Query query = mDatabase;
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot child: dataSnapshot.getChildren())
                {
                    String key = child.getKey().toString();
                    String value = child.getValue().toString();
                    bookMap.put(key, value);
                }

                setUpdateValuesOnDB(book_name, book_url, book_description, bookMap );
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void setUpdateValuesOnDB(String book_name, String book_url, String book_description, HashMap<String, String> bookMap) {

        if ( !bookImageUrl.isEmpty())
            bookMap.put("image", bookImageUrl);


        if ( !book_name.isEmpty())
            bookMap.put("name", book_name);

        if ( !book_url.isEmpty())
            bookMap.put("url", book_url);

        if  ( !book_description.isEmpty())
            bookMap.put("description", book_description);

        mDatabase.setValue(bookMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()){
                    mProgressDialog.dismiss();
                    Toast.makeText(UpdateBookActivity.this, "تم تحديث الكتاب بنجاح", Toast.LENGTH_SHORT).show();
                }
                else {
                    mProgressDialog.dismiss();
                    Toast.makeText(UpdateBookActivity.this, "حدث خطأ فى تحديث الكتاب", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void addItemsOnSpinner() {

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item, booksList);

        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mBooksListSpinner.setAdapter(spinnerArrayAdapter);
    }


}
