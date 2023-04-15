package com.example.practice6.data.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.example.practice6.data.database.dao.BookDao;
import com.example.practice6.data.database.RoomDatabase.BookRoomDatabase;
import com.example.practice6.data.models.Book;
import com.example.practice6.data.database.Entity.BookEntity;

import java.util.List;
import java.util.stream.Collectors;

public class BookRepository {
    private final BookDao mBookDao;
    private final LiveData<List<Book>> mAllBooks;

    private final Context context;

    BookRoomDatabase roomDatabase;
    public BookRepository(Context applicationContext) {
        context = applicationContext;
        roomDatabase = BookRoomDatabase.getDatabase(context);
        mBookDao = BookRoomDatabase.getDatabase(context).bookDao();
        mAllBooks = Transformations.map(mBookDao.getAllBooks(), entities -> entities.stream()
                .map(BookEntity::toBook).collect(Collectors.toList()));
    }
    public LiveData<List<Book>> getAllBooks() {
        return mAllBooks;
    }

    public void insert(BookEntity book) {
        BookRoomDatabase.databaseWriteExecutor.execute(() -> {
            mBookDao.insert(book);
        });
    }
}

