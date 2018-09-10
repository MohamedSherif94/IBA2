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

public class UpdateImageActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    private DatabaseReference mDatabase;

    //firebase storage
    private StorageReference mImageStorage;

    private String library_category;

    private ArrayList<String> imagesList;
    private Spinner mImagesListSpinner;

    //Progress Dialog
    private ProgressDialog mProgressDialog;


    private TextInputLayout mImageName;
    private TextInputLayout mImageDescription;

    private Button mChooseImageBtn;
    private Button mUpdateImageBtn;

    private String mImageUrl = "";

    private static final int GALLERY_PICK = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_image);

        mToolbar = findViewById(R.id.update_image_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("تحديث صورة");

        mImageStorage = FirebaseStorage.getInstance().getReference();

        //Intialization
        imagesList = new ArrayList<>();
        mImagesListSpinner = findViewById(R.id.update_image_spinner);

        mImageName = findViewById(R.id.update_image_name_text);
        mImageDescription = findViewById(R.id.update_image_description_text);

        mChooseImageBtn = findViewById(R.id.update_image_choose_btn);
        mUpdateImageBtn = findViewById(R.id.update_image_btn);

        library_category = getIntent().getStringExtra(HelperClass.LIBRARY_CATEGORY);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle("تحميل الصور");
        mProgressDialog.setMessage("من فضلك إنتظر حتى يتم تحميل الصور...");
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
                    imagesList.add(key);
                }

                addItemsOnSpinner();
                mProgressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                mProgressDialog.dismiss();
                Toast.makeText(UpdateImageActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });

        mChooseImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });

        mUpdateImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String image_name = mImageName.getEditText().getText().toString().trim();
                String image_description = mImageDescription.getEditText().getText().toString().trim();

                if( !TextUtils.isEmpty(image_name) || !TextUtils.isEmpty(image_description)){

                    mProgressDialog.setTitle("تحديث الصورة");
                    mProgressDialog.setMessage("من فضلك إنتظر حتى يتم تحديث الصورة...");
                    mProgressDialog.setCanceledOnTouchOutside(false);
                    mProgressDialog.show();

                    updateImage(image_name, image_description);
                } else {
                    Toast.makeText(UpdateImageActivity.this, "أدخل ع الأقل قيمة واحدة للتحديث", Toast.LENGTH_SHORT).show();
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
                                    Toast.makeText(UpdateImageActivity.this, "Error in uploading image.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {

                Exception error = result.getError();
            }
        }
    }

    private void updateImage(final String image_name, final String image_description) {

        final String image_code = String.valueOf(mImagesListSpinner.getSelectedItem());
        mDatabase = FirebaseDatabase.getInstance().getReference()
                .child("المكتبة").child(library_category).child(image_code);

        final HashMap<String, String> imageMap = new HashMap<>();
        imageMap.clear();

        Query query = mDatabase;
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot child: dataSnapshot.getChildren())
                {
                    String key = child.getKey().toString();
                    String value = child.getValue().toString();
                    imageMap.put(key, value);
                }

                setUpdateValuesOnDB(image_name, image_description, imageMap);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void setUpdateValuesOnDB(String image_name, String image_description, HashMap<String, String> imageMap) {

        if ( !mImageUrl.isEmpty())
            imageMap.put("image", mImageUrl);

        if ( !image_name.isEmpty())
            imageMap.put("name", image_name);

        if  ( !image_description.isEmpty())
            imageMap.put("description", image_description);

        mDatabase.setValue(imageMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()){
                    mProgressDialog.dismiss();
                    Toast.makeText(UpdateImageActivity.this, "تم تحديث الصورة بنجاح", Toast.LENGTH_SHORT).show();
                }
                else {
                    mProgressDialog.dismiss();
                    Toast.makeText(UpdateImageActivity.this, "حدث خطأ فى تحديث الصورة", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void addItemsOnSpinner() {

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item, imagesList);

        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mImagesListSpinner.setAdapter(spinnerArrayAdapter);
    }

}
