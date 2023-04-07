package com.example.practice6.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.practice6.R;
import com.example.practice6.databinding.AuthorInfoBinding;


public class SingleAuthorFragment extends Fragment {
    AuthorInfoBinding binding;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            String name = getArguments().getString("Name");
            int description = getArguments().getInt("Description");
            int image = getArguments().getInt("Photo");
            binding.textView15.setText(name);
            binding.textView17.setText(description);
            binding.imageView3.setImageResource(image);
        }
        binding.button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("Favorite", String.valueOf(binding.textView15.getText()));
                Navigation.findNavController(view)
                        .navigate(R.id.action_single_author_fragment_to_author_list_fragment, bundle);
            }
        });


    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = AuthorInfoBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}
