package com.example.practice6.ui.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.practice6.R;
import com.example.practice6.databinding.NewBookFragmentBinding;

public class NewBookFragment extends Fragment {
    private NewBookFragmentBinding binding;

    public NewBookFragment() {
        super(R.layout.new_book_fragment);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = NewBookFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonUpload.setOnClickListener(v -> {
            String bookName = binding.editTextName.getText().toString();
            String bookAuthor = binding.editTextAuthor.getText().toString();
            Bundle bundle = new Bundle();
            if (!TextUtils.isEmpty(bookName) && !TextUtils.isEmpty(bookAuthor)) {
                binding.editTextName.setError(null);
                bundle.putString("RESULT_OK_NAME", bookName);
                bundle.putString("RESULT_OK_AUTHOR", bookAuthor);
                bundle.putInt("RESULT_OK_IMG", R.drawable.book_img_not_exist);
                Navigation.findNavController(view).navigate(R.id.action_new_book_fragment_to_book_list_fragment, bundle);
            }
            else if (TextUtils.isEmpty(bookName)) {
                binding.editTextName.setError("Пустая строка!");
            }
            else if (TextUtils.isEmpty(bookAuthor))
            {
                binding.editTextAuthor.setError("Пустая строка!");
            }
            else
            {
                binding.editTextName.setError("Пустая строка!");
                binding.editTextAuthor.setError("Пустая строка!");
            }
        });
    }
}