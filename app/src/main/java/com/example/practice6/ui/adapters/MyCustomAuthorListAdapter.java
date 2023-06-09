package com.example.practice6.ui.adapters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practice6.R;
import com.example.practice6.data.models.Author;

import java.util.ArrayList;
import java.util.List;

public class MyCustomAuthorListAdapter extends RecyclerView.Adapter<MyCustomAuthorListAdapter.MyAuthorViewHolder> {
    private List<Author> authors;
    public MyCustomAuthorListAdapter() {
        this.authors =  new ArrayList<>();
    }


    public MyCustomAuthorListAdapter.MyAuthorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.author_list_item, parent, false);
        return new MyCustomAuthorListAdapter.MyAuthorViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull MyAuthorViewHolder holder, int position) {
        Author author = authors.get(position);
        holder.name.setText(author.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("Name",author.getName());
                bundle.putInt("Photo", author.getPhoto());
                Navigation.findNavController(view).navigate(R.id.action_author_list_fragment_to_single_author_fragment, bundle);
            }
        });

    }
    @Override
    public int getItemCount() {
        return authors.size();
    }

    public static class MyAuthorViewHolder extends RecyclerView.ViewHolder {
        public TextView name;

        public MyAuthorViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textView16);
        }
    }
    public void updateAuthors(List<Author> authors) {
        this.authors.clear();
        this.authors = authors;
        notifyDataSetChanged();
    }
}
