package com.example.practice6.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.practice6.R;
import java.util.ArrayList;
import java.util.List;

public class BookDataSource {

    private static ArrayList<Book> bookList;


    public static LiveData<List<Book>> createList() {
        bookList = new ArrayList<>();
        bookList.add(new Book(R.drawable.book_cover1,"Гарри Поттер и Философский камень","Дж. К. Роулинг"));
        bookList.add(new Book(R.drawable.book_cover2,"Гарри Поттер и Тайная комната","Дж. К. Роулинг"));
        bookList.add(new Book(R.drawable.book_cover3,"Гарри Поттер и узник Азкабана","Дж. К. Роулинг"));
        bookList.add(new Book(R.drawable.book_cover4,"Гарри Поттер и Кубок огня","Дж. К. Роулинг"));
        bookList.add(new Book(R.drawable.book_cover5,"Гарри Поттер и Орден Феникса","Дж. К. Роулинг"));
        bookList.add(new Book(R.drawable.book_cover6,"Гарри Поттер и Принц-полукровка","Дж. К. Роулинг"));
        bookList.add(new Book(R.drawable.book_cover7,"Гарри Поттер и Дары Смерти","Дж. К. Роулинг"));
        MutableLiveData<List<Book>> list = new MutableLiveData<>();
        list.setValue(bookList);
        return list;
    }
}

