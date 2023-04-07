package com.example.practice6.data;

import androidx.lifecycle.LiveData;

import java.util.List;

public class BookRepository {
    public LiveData<List<Book>> getBooksData() {
        return BookDataSource.createList();
    }
}


