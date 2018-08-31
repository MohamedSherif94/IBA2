package com.example.iba.ibasecond.Services;

import android.graphics.Bitmap;

public class Services {

    private String name;
    private String image;

    public Services() {
    }

    public Services(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }


}
