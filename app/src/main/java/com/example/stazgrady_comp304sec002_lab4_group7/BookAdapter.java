package com.example.stazgrady_comp304sec002_lab4_group7;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.MyViewHolder> {

    private ArrayList<Books> bookList = new ArrayList<>();
    private onItemClickListener listener;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.booklist_row, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Books currentBook = bookList.get(position);
        holder.name.setText(currentBook.getBookName());
        holder.author.setText(currentBook.getAuthorName());
        holder.description.setText(currentBook.getBookDescription());
        holder.category.setText(currentBook.getCategory());
        holder.quantity.setText(String.valueOf(currentBook.getQuantity()));
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public void setBookList(ArrayList<Books> bookList) {
        this.bookList = bookList;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        Context context;

        private final TextView name;
        private final TextView author;
        private final TextView description;
        private final TextView category;
        private final TextView quantity;

        public MyViewHolder(View v) {
            super(v);
            name = v.findViewById(R.id.name);
            author = v.findViewById(R.id.author);
            description = v.findViewById(R.id.description);
            category = v.findViewById(R.id.category);
            quantity = v.findViewById(R.id.quantity);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    //to avoid crashing if there is no item
                    if (listener != null && pos != RecyclerView.NO_POSITION) {
                        listener.onItemClick(bookList.get(pos));
                    }
                }
            });
        }
    }

    //set-up for the on-click listener to be used on the books
    public interface onItemClickListener {
        void onItemClick(Books book);
    }

    public void setOnItemClickListener(onItemClickListener listener) { this.listener = listener; }
}

