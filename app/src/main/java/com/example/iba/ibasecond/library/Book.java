package com.example.iba.ibasecond.library;

public class Book {

    private String image;
    private String name;
    private String description;
    private String url;

    public Book() {
    }

    public Book(String image, String name, String description, String url) {
        this.image = image;
        this.name = name;
        this.description = description;
        this.url = url;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }
}
