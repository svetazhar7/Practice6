package com.example.practice6.data.models.Entity;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.practice6.data.models.Author;

@Entity(tableName = "author_table")
public class AuthorEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private int photo;
    public AuthorEntity(@NonNull String name, int photo) {
        this.name = name;
        this.photo = photo;
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

    public int getPhoto() {
        return photo;
    }
    public void setPhoto(int photo) {
        this.photo = photo;
    }
    public Author toAuthor(){
        return new Author(this.name,this.photo);
    }
}






