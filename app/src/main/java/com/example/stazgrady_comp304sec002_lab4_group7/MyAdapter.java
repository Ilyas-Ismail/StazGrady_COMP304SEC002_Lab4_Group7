package com.example.stazgrady_comp304sec002_lab4_group7;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stazgrady_comp304sec002_lab4_group7.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

<<<<<<< HEAD
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

    public class MyViewHolder extends RecyclerView.ViewHolder{
=======
    ArrayList<Books> bookList;
    Context context;

    public MyAdapter(ArrayList<Books> data) {
        this.bookList = (ArrayList<Books>) data;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
>>>>>>> 12125c334d4f076657d67bfc9e1f21639404f4be
        private final TextView name;
        private final TextView author;
        private final TextView description;
        private final TextView category;
        private final TextView quantity;

        public MyViewHolder(View v){
            super(v);
            name = v.findViewById(R.id.name);
            author = v.findViewById(R.id.author);
            description = v.findViewById(R.id.description);
            category = v.findViewById(R.id.category);
            quantity = v.findViewById(R.id.quantity);
<<<<<<< HEAD

            //unfinished onClickListener for when the user wants to edit a book
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

    public void setOnItemClickListener(onItemClickListener listener) {
        this.listener = listener;
=======
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        context = parent.getContext();
        View v = LayoutInflater.from(context).inflate(R.layout.booklist_row, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position){
        holder.name.setText(bookList.get(position).getBookName());
        holder.author.setText(bookList.get(position).getAuthorName());
        holder.description.setText(bookList.get(position).getBookDescription());
        holder.category.setText(bookList.get(position).getCategory());
        holder.quantity.setText(bookList.get(position).getQuantity());
    }

    public int getItemCount(){
        return bookList.size();
>>>>>>> 12125c334d4f076657d67bfc9e1f21639404f4be
    }
}
