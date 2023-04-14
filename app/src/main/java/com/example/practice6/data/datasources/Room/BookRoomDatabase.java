package com.example.practice6.data.datasources.Room;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.practice6.R;
import com.example.practice6.data.models.Entity.BookEntity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {BookEntity.class}, version = 1, exportSchema = false)
public abstract class BookRoomDatabase extends RoomDatabase {
    public abstract BookDao bookDao();
    private static volatile BookRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static BookRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (BookRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            BookRoomDatabase.class, "book_database")
                            .addCallback(sRoomDatabaseCallback).build();}}
        }
        return INSTANCE;
    }

    public static final RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseWriteExecutor.execute(() -> {
                BookDao dao = INSTANCE.bookDao();
                dao.deleteAll();
                BookEntity book = new BookEntity("Гарри Поттер и Философский камень","Дж. К. Роулинг",R.drawable.book_cover1);
                dao.insert(book);
                book = new BookEntity("Гарри Поттер и Тайная комната","Дж. К. Роулинг",R.drawable.book_cover2);
                dao.insert(book);
                book = new BookEntity("Гарри Поттер и узник Азкабана","Дж. К. Роулинг",R.drawable.book_cover3);
                dao.insert(book);
                book = new BookEntity("Гарри Поттер и Кубок огня","Дж. К. Роулинг",R.drawable.book_cover4);
                dao.insert(book);
                book = new BookEntity("Гарри Поттер и Орден Феникса","Дж. К. Роулинг",R.drawable.book_cover5);
                dao.insert(book);
                book = new BookEntity("Гарри Поттер и Принц-полукровка","Дж. К. Роулинг",R.drawable.book_cover6);
                dao.insert(book);
                book = new BookEntity("Гарри Поттер и Дары Смерти","Дж. К. Роулинг",R.drawable.book_cover7);
                dao.insert(book);
                book = new BookEntity("Голодные игры","С. Коллинз",R.drawable.book_cover8);
                dao.insert(book);
            });
        }
    };
}