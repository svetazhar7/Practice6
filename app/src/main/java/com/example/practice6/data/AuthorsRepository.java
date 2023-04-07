package com.example.practice6.data;

import androidx.lifecycle.LiveData;

import java.util.List;

public class AuthorsRepository {
    public LiveData<List<Author>> getAuthorsData() {
        return AuthorsDataSource.createList();
    }
}
