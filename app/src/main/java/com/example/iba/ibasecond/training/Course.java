package com.example.iba.ibasecond.training;

public class Course {

    private String image;
    private String name;
    private String price;
    private String place;

    public Course() {
    }

    public Course(String image, String name, String price, String place) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.place = place;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getPlace() {
        return place;
    }
}
