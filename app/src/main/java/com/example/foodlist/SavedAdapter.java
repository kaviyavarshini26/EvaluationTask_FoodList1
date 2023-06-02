package com.example.foodlist;

import android.annotation.SuppressLint;
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
    private List<Category> savedList;
    Bottom1 listner;
//    private OnDeleteClickListener onDeleteClickListener;
    //    private ViewHolder.OnDeleteClickListener onDeleteClickListener;
    private List<ItemEntity> items = new ArrayList<>();

    public SavedAdapter(Context context, List<Category> savedList, Bottom1 listner) {
        this.context = context;
        this.savedList = savedList;
        this.listner = listner;
//        this.onDeleteClickListener = onDeleteClickListener;
    }

    void setData(List<Category> savedList) {
        this.savedList = savedList;
    }

//    public SavedAdapter(Context context) {
//        this.context = context;
//    }

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
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ItemEntity item = items.get(position);
        holder.itemNameTextView.setText(item.getItemName());
//      holder.itemDescTextView.setText(item.getItemDesc());
        Glide.with(context)

                .load(item.getItemImage())

                .into(holder.itemImageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Open bottom sheet with description
                listner.onItemClicked1(savedList.get(position));
            }
        });
    }

//        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (onDeleteClickListener != null) {
//                    int position = holder.getAdapterPosition();
//                    if (position != RecyclerView.NO_POSITION) {
//                        onDeleteClickListener.onDeleteClick(position);
//                    }
//                }
//            }
//        });


    @Override
    public int getItemCount() {
        return items.size();
    }

//    public void onDeleteClick(Category category) {
//
//    }


//    public void setOnDeleteClickListener(ViewHolder.OnDeleteClickListener onDeleteClickListener) {
//
//        this.onDeleteClickListener = onDeleteClickListener;
//    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView itemNameTextView;
        TextView itemDescTextView;
        ImageView itemImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemNameTextView = itemView.findViewById(R.id.textview3desc);
//           itemDescTextView=itemView.findViewById(R.id.item_name_desc);
            itemImageView = itemView.findViewById(R.id.imageview1thumb);
        }
    }
}
//    public interface OnDeleteClickListener {
//        void onDeleteClick(ItemEntity item);
//    }
//    }
