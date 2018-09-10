package com.example.iba.ibasecond.works_development;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.iba.ibasecond.R;
import com.squareup.picasso.Picasso;

public class PackageViewHolder extends RecyclerView.ViewHolder {

    View mView;

    public PackageViewHolder(View itemView) {
        super(itemView);

        mView = itemView;
    }

    public void setPackageImage(String image){

        ImageView packageImageView = mView.findViewById(R.id.package_single_layout_image);
        Picasso.get().load(image).placeholder(R.drawable.account_im).into(packageImageView);
    }

    public void setPackageName(String name){

        TextView packageNameView = mView.findViewById(R.id.package_single_layout_name);
        packageNameView.setText(name);
    }

    public void setPackageDescription(String price){

        TextView packageDescriptionView = mView.findViewById(R.id.package_single_layout_description);
        packageDescriptionView.setText(price);
    }

}
