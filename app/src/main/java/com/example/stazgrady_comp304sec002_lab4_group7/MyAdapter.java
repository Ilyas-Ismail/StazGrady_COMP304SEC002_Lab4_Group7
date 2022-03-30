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

    ArrayList<Books> bookList;
    Context context;

    public MyAdapter(ArrayList<Books> data) {
        this.bookList = (ArrayList<Books>) data;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
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
    }
}
