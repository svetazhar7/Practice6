package com.example.practice6.data.database.dao;

import java.util.List;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.practice6.data.database.Entity.AuthorEntity;

@Dao
public interface AuthorDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(AuthorEntity author);
    @Query("DELETE FROM author_table")
    void deleteAll();
    @Query("SELECT * FROM author_table ORDER BY id")
    LiveData<List<AuthorEntity>> getAllAuthors();
}

