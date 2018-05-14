package com.example.trile.managerfood.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.trile.managerfood.DetailCookActivity;
import com.example.trile.managerfood.Models.md_main_ds;
import com.example.trile.managerfood.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by TRILE on 22/03/2018.
 */

public class Adapter_ds_top extends RecyclerView.Adapter<Adapter_ds_top.ViewHolder> {

    ArrayList<md_main_ds> arrTop;
    Context context;

    public Adapter_ds_top(ArrayList<md_main_ds> arrTop, Context context) {
        this.arrTop = arrTop;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.custom_ds_top,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        Picasso.with(context).load(arrTop.get(position).getImg()).into(holder.img);
        holder.tvName.setText(arrTop.get(position).getName());
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(context, DetailCookActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("tour",arrTop.get(position).getName());
                myIntent.putExtra("bundle", bundle);
                myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(myIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrTop.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView tvName;
        public ViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.imgTop);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
        }
    }
}
