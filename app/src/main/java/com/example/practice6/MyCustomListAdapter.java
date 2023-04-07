package com.example.practice6;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practice6.data.Book;

import java.util.ArrayList;

public class MyCustomListAdapter extends RecyclerView.Adapter<MyCustomListAdapter.MyViewHolder> {

    private ArrayList<Book> books;

    public MyCustomListAdapter(ArrayList<Book> books) {
        this.books = books;
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
          /* itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(), "Нажатие на: " + textView .getText(), Toast.LENGTH_SHORT).show();
                    Log.i("FragmentScreenThree", "Нажатие на: " + textView .getText());

                }
            });*/
        }
    }
}

