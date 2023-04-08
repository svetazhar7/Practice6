package com.example.practice6.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.practice6.data.Author;
import com.example.practice6.data.AuthorsRepository;
import com.example.practice6.data.Book;
import com.example.practice6.data.BookRepository;

import java.util.List;

public class AuthorListViewModel extends ViewModel {
    public LiveData<List<Author>> authors;

    public AuthorListViewModel() {
        AuthorsRepository authorsRepository = new AuthorsRepository();
        authors = authorsRepository.getAuthorsData();
    }
}
