package com.example.practice6.ui.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.practice6.data.models.Author;
import com.example.practice6.data.database.Entity.AuthorEntity;
import com.example.practice6.data.repository.AuthorRepository;


import java.util.List;

public class AuthorListViewModel extends AndroidViewModel {
    private AuthorRepository mRepository;

    private final LiveData<List<Author>> mAllAuthors;

    public AuthorListViewModel (Application application) {
        super(application);
        mRepository = new AuthorRepository(application);
        mAllAuthors = mRepository.getAllAuthors();
    }

    public LiveData<List<Author>> getAllAuthors() { return mAllAuthors; }

    public void insert(Author author) { mRepository.insert(new AuthorEntity(author.getName(), author.getPhoto())); }
}

