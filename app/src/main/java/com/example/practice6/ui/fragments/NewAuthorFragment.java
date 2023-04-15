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
import com.example.practice6.databinding.NewAuthorFragmentBinding;

public class NewAuthorFragment extends Fragment {
    private NewAuthorFragmentBinding binding;
    private EditText mEditWordView;

    public NewAuthorFragment() {
        super(R.layout.new_author_fragment);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = NewAuthorFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        binding.buttonUpload.setOnClickListener(v ->{
            String authorName = binding.editTextAuthor.getText().toString();
            Bundle bundle = new Bundle();
            if (!TextUtils.isEmpty(authorName)){
                binding.editTextAuthor.setError(null);
                bundle.putString("RESULT_OK_NAME", authorName);
                bundle.putInt("RESULT_OK_IMG", R.drawable.avtor_img_not_exist);
                Navigation.findNavController(view).navigate(R.id.action_new_author_fragment_to_author_list_fragment,bundle);
            }
            else {
                binding.editTextAuthor.setError("Пустая строка!");
            }
        });
        /*  binding.buttonUpload.setOnClickListener(v ->{
            String authorName = binding.editTextName.getText().toString();
            if (!TextUtils.isEmpty(authorName)){
                binding.editTextName.setError(null);
                authorsRepository.createNewAuthorDao(new AuthorEntity(authorName.trim(), R.drawable.avtor_img_not_exist));
                Navigation.findNavController(view).popBackStack();
            }
            else {
                binding.editTextName.setError("Пустая строка!");
            }
        });*/
    }
    
}
