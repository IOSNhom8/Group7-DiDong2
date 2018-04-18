package com.example.trile.managerfood.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.trile.managerfood.Models.md_top_ds;
import com.example.trile.managerfood.R;

import java.util.ArrayList;

/**
 * Created by TRILE on 22/03/2018.
 */

public class Adapter_ds_top extends RecyclerView.Adapter<Adapter_ds_top.ViewHolder> {

    ArrayList<md_top_ds> arrTop;
    Context context;

    public Adapter_ds_top(ArrayList<md_top_ds> arrTop, Context context) {
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
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.img.setImageResource(arrTop.get(position).getImg());
        holder.tvName.setText(arrTop.get(position).getName());
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
