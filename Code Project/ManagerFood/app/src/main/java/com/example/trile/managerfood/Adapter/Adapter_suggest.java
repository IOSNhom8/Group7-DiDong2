package com.example.trile.managerfood.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.trile.managerfood.Models.md_suggest;
import com.example.trile.managerfood.R;

import java.util.ArrayList;

/**
 * Created by TRILE on 31/03/2018.
 */

public class Adapter_suggest extends RecyclerView.Adapter<Adapter_suggest.ViewHolder> {
    ArrayList<md_suggest> arrSuggest;
    Context context;

    public Adapter_suggest(ArrayList<md_suggest> arrSuggest, Context context) {
        this.arrSuggest = arrSuggest;
        this.context = context;
    }

    @Override
    public Adapter_suggest.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.custom_ds_suggest,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(Adapter_suggest.ViewHolder holder, int position) {
        holder.img.setImageResource(arrSuggest.get(position).getImg());
        holder.name.setText(arrSuggest.get(position).getName());
        //holder.descip.setText(arrSuggest.get(position).getDescip());
    }

    @Override
    public int getItemCount() {
        return arrSuggest.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name;
        TextView descip;
        public ViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.imgSuggest);
            name = (TextView) itemView.findViewById(R.id.tvNameSuggest);
            //descip = (TextView) itemView.findViewById(R.id.desciption);
        }
    }
}
