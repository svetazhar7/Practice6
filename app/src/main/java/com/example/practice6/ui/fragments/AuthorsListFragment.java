package com.example.practice6.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practice6.data.models.Author;
import com.example.practice6.data.models.Entity.AuthorEntity;
import com.example.practice6.ui.adapters.MyCustomAuthorListAdapter;
import com.example.practice6.R;

import com.example.practice6.databinding.AuthorListBinding;
import com.example.practice6.ui.viewmodels.AuthorListViewModel;

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
            Bundle args = getArguments();
            if (args != null && args.containsKey("RESULT_OK_NAME") && args.containsKey("RESULT_OK_IMG")) {
                Author author = new Author(args.getString("RESULT_OK_NAME"),args.getInt("RESULT_OK_IMG"));
                authorListViewModel.insert(author);
            }
            if (args != null && args.containsKey("Favorite"))
            {
                Toast.makeText(getContext(), "Вы добавили " +args.getString("Favorite")+" в избранное", Toast.LENGTH_SHORT).show();
            }
            //args.clear();
            return binding.getRoot();
        }

        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            binding.button8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Navigation.findNavController(view).navigate(R.id.action_author_list_fragment_to_profile_fragment);
                }
            });
            binding.button9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Navigation.findNavController(view).navigate(R.id.action_author_list_fragment_to_new_author_fragment);
                }
            });

            recyclerView = view.findViewById(R.id.recyclerView);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(myCustomListAdapter);// установка адаптера
            authorListViewModel.getAllAuthors().observe(getViewLifecycleOwner(), authorList ->
                    myCustomListAdapter.updateAuthors(authorList));
        }
}


