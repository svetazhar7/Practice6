package com.example.practice6.data.repository;

import androidx.lifecycle.LiveData;

import com.example.practice6.data.datasources.AuthorsDataSource;
import com.example.practice6.data.models.Author;

import java.util.List;

public class AuthorsRepository {
    public LiveData<List<Author>> getAuthorsData() {
        return AuthorsDataSource.createList();
    }
}
