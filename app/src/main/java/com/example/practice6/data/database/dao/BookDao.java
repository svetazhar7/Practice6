package com.example.practice6.data.database.dao;

import java.util.List;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.practice6.data.database.Entity.BookEntity;

@Dao
public interface BookDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(BookEntity book);
    @Query("DELETE FROM book_table")
    void deleteAll();
    @Query("SELECT * FROM book_table ORDER BY id")
    LiveData<List<BookEntity>> getAllBooks();
}
