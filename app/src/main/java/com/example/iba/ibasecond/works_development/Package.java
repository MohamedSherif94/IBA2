package com.example.iba.ibasecond.works_development;

public class Package {

    private String image;
    private String name;
    private String description;

    public Package() {
    }

    public Package(String image, String name, String description) {
        this.image = image;
        this.name = name;
        this.description = description;
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

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
