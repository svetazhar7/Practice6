package com.example.practice6.data.datasources.Room;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


import com.example.practice6.R;

import com.example.practice6.data.models.Entity.AuthorEntity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {AuthorEntity.class}, version = 1, exportSchema = false)
public abstract class AuthorRoomDatabase extends RoomDatabase {
    public abstract AuthorDao authorDao();
    private static volatile AuthorRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static AuthorRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AuthorRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AuthorRoomDatabase.class, "author_database")
                            .addCallback(sRoomDatabaseCallback).build();
                }
            }
        }
        return INSTANCE;
    }

    public static final RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseWriteExecutor.execute(() -> {
                AuthorDao dao = INSTANCE.authorDao();
                dao.deleteAll();
                AuthorEntity author = new AuthorEntity("Дж.К. Роулинг",R.drawable.author1);
                dao.insert(author);
                author = new AuthorEntity("С. Коллинз",R.drawable.author2);
                dao.insert(author);
            });
        }
    };
}

