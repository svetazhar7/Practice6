package com.example.practice6.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.practice6.R;

import java.util.ArrayList;
import java.util.List;

public class AuthorsDataSource {
    private static ArrayList<Author> authorsList;

    public static LiveData<List<Author>> createList() {
        authorsList = new ArrayList<>();
        authorsList.add(new Author("Дж.К. Роулинг", R.string.author1_description,R.drawable.author1));
        authorsList.add(new Author("С. Коллинз", R.string.author2_description,R.drawable.author2));
        MutableLiveData<List<Author>> list = new MutableLiveData<>();
        list.setValue(authorsList);
        return list;
    }
}
