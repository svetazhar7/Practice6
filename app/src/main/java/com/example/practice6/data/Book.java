package com.example.practice6.data;


public class Book {

    int image;
    String name;
    String author;

    public Book(int image, String name, String author) {
        this.image = image;
        this.name = name;
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }
}

