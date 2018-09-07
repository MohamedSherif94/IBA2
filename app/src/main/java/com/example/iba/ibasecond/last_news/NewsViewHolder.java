package com.example.iba.ibasecond.last_news;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.iba.ibasecond.R;
import com.squareup.picasso.Picasso;

public class NewsViewHolder extends RecyclerView.ViewHolder {

    View mView;

    public NewsViewHolder(View itemView) {
        super(itemView);

        mView = itemView;
    }

    public void setNewsImage(String image){

        ImageView newsImageView = mView.findViewById(R.id.news_single_layout_image);
        Picasso.get().load(image).placeholder(R.drawable.account_im).into(newsImageView);
    }

    public void setNewsText(String text){

        TextView newsTextView = mView.findViewById(R.id.news_single_layout_text);
        newsTextView.setText(text);
    }
}
