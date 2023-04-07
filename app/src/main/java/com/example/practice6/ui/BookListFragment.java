package com.example.practice6.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practice6.MyCustomListAdapter;
import com.example.practice6.R;
import com.example.practice6.data.Book;
import com.example.practice6.databinding.BookListBinding;

import java.util.ArrayList;

public class BookListFragment extends Fragment {
    RecyclerView recyclerView;
    MyCustomListAdapter myCustomListAdapter;
    BookListBinding binding;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = BookListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArrayList<Book>bookList = new ArrayList<>();
        bookList.add(new Book(R.drawable.book_cover1,"Гарри Поттер и Философский камень","Дж. К. Роулинг"));
        bookList.add(new Book(R.drawable.book_cover2,"Гарри Поттер и Тайная комната","Дж. К. Роулинг"));
        bookList.add(new Book(R.drawable.book_cover3,"Гарри Поттер и узник Азкабана","Дж. К. Роулинг"));
        bookList.add(new Book(R.drawable.book_cover4,"Гарри Поттер и Кубок огня","Дж. К. Роулинг"));
        bookList.add(new Book(R.drawable.book_cover5,"Гарри Поттер и Орден Феникса","Дж. К. Роулинг"));
        bookList.add(new Book(R.drawable.book_cover6,"Гарри Поттер и Принц-полукровка","Дж. К. Роулинг"));
        bookList.add(new Book(R.drawable.book_cover7,"Гарри Поттер и Дары Смерти","Дж. К. Роулинг"));
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        myCustomListAdapter = new MyCustomListAdapter(bookList);// создание адаптера
        recyclerView.setAdapter(myCustomListAdapter);// установка адаптера
    }
}
