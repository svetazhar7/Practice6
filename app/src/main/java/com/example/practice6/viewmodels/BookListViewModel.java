package com.example.practice6.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.practice6.data.Book;
import com.example.practice6.data.BookRepository;

import java.util.List;

public class BookListViewModel extends ViewModel {
    public LiveData<List<Book>> books;

    public BookListViewModel() {

        BookRepository bookRepository = new BookRepository();
        books = bookRepository.getBooksData();
    }
}