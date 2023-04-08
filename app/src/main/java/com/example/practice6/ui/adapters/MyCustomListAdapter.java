package com.example.practice6.ui.adapters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practice6.R;
import com.example.practice6.data.models.Book;

import java.util.ArrayList;
import java.util.List;

public class MyCustomListAdapter extends RecyclerView.Adapter<MyCustomListAdapter.MyViewHolder> {

    private List<Book> books;
    public MyCustomListAdapter() {
        this.books =  new ArrayList<>();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Book book = books.get(position);
        holder.name.setText(book.getName());
        holder.author.setText(book.getAuthor());
        holder.imageView.setImageResource(book.getImage());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("Name", book.getName());
                bundle.putString("Author", book.getAuthor());
                bundle.putInt("Image", book.getImage());
                Navigation.findNavController(view).navigate(R.id.action_book_list_fragment_to_single_book_fragment, bundle);
            }
        });

    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView author;
        public TextView name;
        public ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            author = itemView.findViewById(R.id.item_author);
            imageView = itemView.findViewById(R.id.item_image);
            name = itemView.findViewById(R.id.item_name);
        }
    }
    public void updateBooks(List<Book> books) {
        this.books.clear();
        this.books = books;
        notifyDataSetChanged();
    }
}

