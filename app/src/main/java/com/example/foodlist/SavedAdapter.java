package com.example.foodlist;

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
import java.util.List;


public class SavedAdapter extends RecyclerView.Adapter<SavedAdapter.ViewHolder> {

    private Context context;
    private List<ItemEntity> items=new ArrayList<>();


    public SavedAdapter(Context context) {
        this.context = context;
    }

    public void setItems(List<ItemEntity> items) {
        this.items = items;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_design1, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemEntity item = items.get(position);
        holder.itemNameTextView.setText(item.getItemName());
        holder.itemDescTextView.setText(item.getItemDesc());
        Glide.with(context)

                .load(item.getItemImage())

                .into(holder.itemImageView);

    }




    @Override
    public int getItemCount() {
        return  items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView itemNameTextView;
        TextView itemDescTextView;
        ImageView itemImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemNameTextView = itemView.findViewById(R.id.item_name_text);
            itemDescTextView=itemView.findViewById(R.id.item_name_desc);
            itemImageView=itemView.findViewById(R.id.item_name_image);
        }
    }
}
