package com.example.practice6.ui.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.practice6.data.models.Book;
import com.example.practice6.data.repository.BookRepository;

import java.util.List;

public class BookListViewModel extends ViewModel {
    public LiveData<List<Book>> books;

    public BookListViewModel() {

        BookRepository bookRepository = new BookRepository();
        books = bookRepository.getBooksData();
    }
}
