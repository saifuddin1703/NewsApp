package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Myadapter extends RecyclerView.Adapter<Myadapter.viewholder> {
ArrayList<News>list;
Context context;
    onitem activity;
    public void updatenews(ArrayList<News> updatedlist){
        list.clear();
        list.addAll(updatedlist);
        notifyDataSetChanged();
    }
    public Myadapter(ArrayList<News> list, Context context,onitem activity) {
        this.list = list;
        this.context = context;
        this.activity= (onitem) activity;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.newlayout,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, final int position) {
        Glide.with(context).load(list.get(position).image).into(holder.image);
        holder.title.setText(list.get(position).title);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.oniclick(list.get(position).getLink(),list.get(position).getTitle());
            }
        });
    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView title;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            image= itemView.findViewById(R.id.imageView);
            title= itemView.findViewById(R.id.textView);
        }
    }
}
