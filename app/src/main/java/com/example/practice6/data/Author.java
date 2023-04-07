package com.example.practice6.data;

public class Author {
    String name;
    int photo;
    int description;

    public Author(String name, int description, int photo)
    {
        this.name = name;
        this.description = description;
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public  int getDescription() {
        return description;
    }

    public int getPhoto() {
        return photo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(int description) {
        this.description = description;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }
}
