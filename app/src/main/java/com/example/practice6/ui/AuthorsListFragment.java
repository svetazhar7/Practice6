package com.example.practice6.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practice6.MyCustomAuthorListAdapter;
import com.example.practice6.R;
import com.example.practice6.databinding.AuthorListBinding;
import com.example.practice6.viewmodels.AuthorListViewModel;

public class AuthorsListFragment extends Fragment{

        RecyclerView recyclerView;
         MyCustomAuthorListAdapter myCustomListAdapter;
        AuthorListBinding binding;
        AuthorListViewModel authorListViewModel;

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            authorListViewModel = new ViewModelProvider(this).get(AuthorListViewModel.class);
        }

        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                                 @Nullable Bundle savedInstanceState) {
            binding = AuthorListBinding.inflate(inflater, container, false);
            myCustomListAdapter = new MyCustomAuthorListAdapter();// создание адаптера
            return binding.getRoot();
        }

        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            if (getArguments()!=null)
            {
                Toast.makeText(getContext(), "Вы добавили автора в избранное", Toast.LENGTH_SHORT).show();
            }
            recyclerView = view.findViewById(R.id.recyclerView);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(myCustomListAdapter);// установка адаптера
            authorListViewModel.authors.observe(getViewLifecycleOwner(), authorList ->
                    myCustomListAdapter.updateAuthors(authorList));
        }
}


