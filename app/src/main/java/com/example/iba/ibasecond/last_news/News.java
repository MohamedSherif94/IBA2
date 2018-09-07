package com.example.iba.ibasecond.last_news;

public class News {

    private String image;
    private String text;

    public News() {
    }

    public News(String image, String text) {
        this.image = image;
        this.text = text;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImage() {
        return image;
    }

    public String getText() {
        return text;
    }
}
