package com.example.practice6.data.models.Entity;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.practice6.data.models.Book;

@Entity(tableName = "book_table")
public class BookEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String author;
    private int cover;
    public BookEntity(@NonNull String name, String author, int cover) {
        this.name = name;
        this.author = author;
        this.cover = cover;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    @NonNull
    public String getName() {
        return this.name;
    }
    public void setName(@NonNull String name) {
        this.name = name;
    }
    public int getCover() {
        return cover;
    }
    public void setCover(int cover) {
        this.cover = cover;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public Book toBook(){
        return new Book(this.name,this.author,this.cover);
    }
}





