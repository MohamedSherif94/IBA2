package com.example.iba.ibasecond.works_development.social_media_services;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.iba.ibasecond.HelperClass;
import com.example.iba.ibasecond.R;
import com.example.iba.ibasecond.library.Book;
import com.example.iba.ibasecond.library.BookViewHolder;
import com.example.iba.ibasecond.works_development.Package;
import com.example.iba.ibasecond.works_development.PackageViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class PackagesActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private RecyclerView mPackagesList;

    //Progress Dialog
    private ProgressDialog mProgressDialog;

    private String package_category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_packages);

        package_category = getIntent().getStringExtra(HelperClass.PACKAGE_CATEGORY);

        mToolbar = findViewById(R.id.packages_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(package_category);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle("تحميل الباقات");
        mProgressDialog.setMessage("من فضلك إنتظر حتى يتم تحميل الباقات...");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();

        mPackagesList = findViewById(R.id.packages_list);
        mPackagesList.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        super.onStart();
        startListening();

    }

    private void startListening(){
        Query query = FirebaseDatabase.getInstance().getReference()
                .child(HelperClass.WORKS_DEVELOPMENT).child(HelperClass.SOCIAL_MEDIA_SERVICES)
                .child(package_category);

        FirebaseRecyclerOptions<Package> options =
                new FirebaseRecyclerOptions.Builder<Package>()
                        .setQuery(query, Package.class)
                        .build();
        FirebaseRecyclerAdapter adapter = new FirebaseRecyclerAdapter<Package, PackageViewHolder>(options) {
            @Override
            public PackageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                // Create a new instance of the ViewHolder, in this case we are using a custom
                // layout called R.layout.message for each item
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.package_single_layout, parent, false);

                return new PackageViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(PackageViewHolder packageViewHolder, int position, Package _package) {
                // Bind the Chat object to the ChatHolder
                // Log.v(CoursesActivity.class.getSimpleName(), "Course Image : "+course.getImage());

                packageViewHolder.setPackageImage(_package.getImage());

                packageViewHolder.setPackageName(_package.getName());

                packageViewHolder.setPackageDescription(_package.getDescription());

                // ...
            }

            @Override
            public void onDataChanged() {
                super.onDataChanged();
                mProgressDialog.dismiss();
            }
        };
        mPackagesList.setAdapter(adapter);
        adapter.startListening();

    }
}
