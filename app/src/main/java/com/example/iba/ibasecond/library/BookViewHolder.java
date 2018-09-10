package com.example.iba.ibasecond.library;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.iba.ibasecond.R;
import com.squareup.picasso.Picasso;

public class BookViewHolder extends RecyclerView.ViewHolder {

    View mView;

    public BookViewHolder(View itemView) {
        super(itemView);

        mView = itemView;
    }

    public void setBookImage(String image){

        ImageView bookImageView = mView.findViewById(R.id.book_single_layout_image);
        Picasso.get().load(image).placeholder(R.drawable.account_im).into(bookImageView);
    }

    public void setBookName(String name){

        TextView nameTextView = mView.findViewById(R.id.book_single_layout_name);
        nameTextView.setText(name);
    }

    public void setBookDescription(String description){

        TextView descriptionTextView = mView.findViewById(R.id.book_single_layout_text);
        descriptionTextView.setText(description);
    }

}
