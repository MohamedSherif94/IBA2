package com.example.iba.ibasecond.training;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.iba.ibasecond.HelperClass;
import com.example.iba.ibasecond.R;
import com.squareup.picasso.Picasso;

public class CourseViewHolder extends RecyclerView.ViewHolder {



    View mView;

    String courseImageUrl;
    String courseName;
    String courseDescription;

    public CourseViewHolder(View itemView) {
        super(itemView);

        mView = itemView;

        final HelperClass helperClass = new HelperClass(mView.getContext());

        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helperClass.openCourseDetailsActivity(courseImageUrl, courseName, courseDescription);

            }
        });
    }

    public void setCourseImage(String image){

        ImageView courseImageView = mView.findViewById(R.id.course_single_layout_image);
        Picasso.get().load(image).placeholder(R.drawable.account_im).into(courseImageView);
        courseImageUrl = image;
    }

    public void setCourseName(String name){

        TextView courseNameView = mView.findViewById(R.id.course_single_layout_name);
        courseNameView.setText(name);
        courseName  = name;
    }

    public void setCoursePrice(String price){

        TextView coursePriceView = mView.findViewById(R.id.course_single_layout_price);
        coursePriceView.setText(price);
    }
    public void setCoursePlace(String place){

        TextView coursePlaceeView = mView.findViewById(R.id.course_single_layout_place);
        coursePlaceeView.setText(place);
    }

    public void setCourseDescription(String description){
        courseDescription = description;
    }
}
