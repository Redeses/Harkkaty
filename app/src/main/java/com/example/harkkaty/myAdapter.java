package com.example.harkkaty;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

//TODO   DOOOOOOOOOOOOOOOOOOO THIIIIIIIIIIIIIIIIIIIIIIIIIIS
public class myAdapter extends RecyclerView.Adapter<myAdapter.MyVIewHolder> {
    private String[] recycSrings;

    public static class MyVIewHolder extends RecyclerView.ViewHolder{
        public View textView;
        public MyVIewHolder (View v){
            super(v);
            textView = v;
        }
    }

    public myAdapter(String[] recycData){
        recycSrings = recycData;
    }

    @Override
    public myAdapter.MyVIewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.eventrecyc, parent, false);

        MyVIewHolder vh = new MyVIewHolder(v);
        return vh;
    }

    @Override
   public void onBindViewHolder(MyVIewHolder holder, int position){
        //add stuff
        String input = recycSrings[position];
        holder.itemView.findViewById(R.id.EventRecyc);
    }

    @Override
    public int getItemCount(){
        return  recycSrings.length;
    }
}
