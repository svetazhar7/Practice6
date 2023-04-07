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
import com.example.practice6.databinding.ActivityMainBinding;
import com.example.practice6.databinding.Screen2Binding;

public class FirstBookFragment extends Fragment {
    Screen2Binding binding;
    public FirstBookFragment()
    {
        super(R.layout.screen2);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = Screen2Binding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                Bundle bundle = new Bundle();
                bundle.putFloat("rating2",  binding.ratingBar.getRating());
                Navigation.findNavController(view)
                        .navigate(R.id.action_first_book_fragment_to_profile_fragment,bundle);
            }
        });
    }
}
