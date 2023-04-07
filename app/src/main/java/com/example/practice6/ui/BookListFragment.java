package com.example.practice6.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practice6.MyCustomListAdapter;
import com.example.practice6.R;
import com.example.practice6.data.Book;
import com.example.practice6.databinding.BookListBinding;
import com.example.practice6.viewmodels.BookListViewModel;

import java.util.ArrayList;
import java.util.List;

public class BookListFragment extends Fragment {
    RecyclerView recyclerView;
    MyCustomListAdapter myCustomListAdapter;
    BookListBinding binding;
    BookListViewModel bookListViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bookListViewModel = new ViewModelProvider(this).get(BookListViewModel.class);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = BookListBinding.inflate(inflater, container, false);
        myCustomListAdapter = new MyCustomListAdapter();// создание адаптера
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments()!=null)
        {
            Toast.makeText(getContext(), "Вы оценили книгу на "+getArguments().getFloat("Rating"), Toast.LENGTH_SHORT).show();
        }
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(myCustomListAdapter);// установка адаптера
        bookListViewModel.books.observe(getViewLifecycleOwner(), booksList ->
                myCustomListAdapter.updateBooks(booksList));
    }
}
