package com.example.iba.ibasecond.Services;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.iba.ibasecond.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

public class ServicesActivity extends AppCompatActivity {

    private RecyclerView mServicesList;

    private DatabaseReference mUsersDatabase;

    //Progress Dialog
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        mUsersDatabase = FirebaseDatabase.getInstance().getReference().child("Users");

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle("Getting Users");
        mProgressDialog.setMessage("Please wait while we load all data");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();


        mServicesList = findViewById(R.id.services_recycler_view);
//        mServicesList.setHasFixedSize(true);
        //mServicesList.setLayoutManager(new StaggeredGridLayoutManager());
       // mServicesList.setLayoutManager(new LinearLayoutManager(this));

        mServicesList
                .setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));// Here 2 is no. of columns to be displayed



    }

    @Override
    protected void onStart() {
        super.onStart();
        startListening();

    }




    public void startListening(){
        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("Users")
                .limitToLast(50);

        FirebaseRecyclerOptions<Services> options =
                new FirebaseRecyclerOptions.Builder<Services>()
                        .setQuery(query, Services.class)
                        .build();
        FirebaseRecyclerAdapter adapter = new FirebaseRecyclerAdapter<Services, ServicesViewHolder>(options) {
            @Override
            public ServicesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                // Create a new instance of the ViewHolder, in this case we are using a custom
                // layout called R.layout.message for each item
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.service_single_layout, parent, false);

                return new ServicesViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(ServicesViewHolder servicesViewHolder, int position, Services services) {
                // Bind the Chat object to the ChatHolder
                servicesViewHolder.setCourseName(services.getName());
                servicesViewHolder.setCourseImage(services.getImage());
                // ...
            }

            @Override
            public void onDataChanged() {
                super.onDataChanged();
                mProgressDialog.dismiss();
            }
        };
        mServicesList.setAdapter(adapter);
        adapter.startListening();

    }




    public static class ServicesViewHolder extends RecyclerView.ViewHolder {

        View mView;

        public ServicesViewHolder(View itemView) {
            super(itemView);

            mView = itemView;
        }

        public void setCourseName(String name){

            TextView courseNameView = mView.findViewById(R.id.service_single_layout_name);
            courseNameView.setText(name);
        }

        public void setCourseImage(String image){

            ImageView courseImageView = mView.findViewById(R.id.service_single_layout_image);
            Picasso.get().load(image).placeholder(R.drawable.account_im).into(courseImageView);
        }
    }


}
