package com.example.practice6.data.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.example.practice6.data.datasources.Room.AuthorDao;
import com.example.practice6.data.datasources.Room.AuthorRoomDatabase;
import com.example.practice6.data.models.Author;
import com.example.practice6.data.models.Entity.AuthorEntity;

import java.util.List;
import java.util.stream.Collectors;

public class AuthorRepository {
    private final AuthorDao mAuthorDao;
    private final LiveData<List<Author>> mAllAuthors;

    private final Context context;

    AuthorRoomDatabase roomDatabase;
    public AuthorRepository(Context applicationContext) {
        context = applicationContext;
        roomDatabase = AuthorRoomDatabase.getDatabase(context);
        mAuthorDao = AuthorRoomDatabase.getDatabase(context).authorDao();
        mAllAuthors = Transformations.map(mAuthorDao.getAllAuthors(), entities -> entities.stream()
                .map(AuthorEntity::toAuthor).collect(Collectors.toList()));
    }
    public LiveData<List<Author>> getAllAuthors() {
        return mAllAuthors;
    }

    public void insert(AuthorEntity author) {
        AuthorRoomDatabase.databaseWriteExecutor.execute(() -> {
            mAuthorDao.insert(author);
        });
    }
}
