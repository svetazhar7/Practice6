package com.example.practice6.data.repository;

import androidx.lifecycle.LiveData;

import com.example.practice6.data.datasources.BookDataSource;
import com.example.practice6.data.models.Book;

import java.util.List;

public class BookRepository {
    public LiveData<List<Book>> getBooksData() {
        return BookDataSource.createList();
    }
}


