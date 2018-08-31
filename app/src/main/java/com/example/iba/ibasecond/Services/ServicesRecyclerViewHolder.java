package com.example.iba.ibasecond.Services;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.iba.ibasecond.R;

public class ServicesRecyclerViewHolder extends RecyclerView.ViewHolder {

    // View holder for gridview recycler view as we used in listview
    public TextView title;
    public ImageView imageview;


    public ServicesRecyclerViewHolder(View view) {

        super(view);

        // Find all views ids
        this.title =  view.findViewById(R.id.title);
        this.imageview =  view.findViewById(R.id.image);
    }


}
