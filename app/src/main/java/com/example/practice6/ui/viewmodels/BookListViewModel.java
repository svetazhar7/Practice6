package com.example.practice6.ui.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.practice6.data.models.Book;
import com.example.practice6.data.models.Entity.BookEntity;
import com.example.practice6.data.repository.BookRepository;


import java.util.List;

public class BookListViewModel extends AndroidViewModel {
    private BookRepository mRepository;

    private final LiveData<List<Book>> mAllBooks;

    public BookListViewModel (Application application) {
        super(application);
        mRepository = new BookRepository(application);
        mAllBooks = mRepository.getAllBooks();
    }

    public LiveData<List<Book>> getAllBooks() { return mAllBooks; }

    public void insert(BookEntity book) { mRepository.createNewBookDao(book); }
}

