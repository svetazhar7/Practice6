package com.example.practice6.ui.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.practice6.data.models.Author;
import com.example.practice6.data.repository.AuthorsRepository;


import java.util.List;

public class AuthorListViewModel extends ViewModel {
    public LiveData<List<Author>> authors;

    public AuthorListViewModel() {
        AuthorsRepository authorsRepository = new AuthorsRepository();
        authors = authorsRepository.getAuthorsData();
    }
}

