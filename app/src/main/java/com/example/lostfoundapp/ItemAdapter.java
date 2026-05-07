package com.example.lostfoundapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    Context context;

    ArrayList<Item> itemList;

    public ItemAdapter(Context context,
                       ArrayList<Item> itemList) {

        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                         int viewType) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_layout,
                        parent,
                        false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,
                                 int position) {

        Item item = itemList.get(position);

        holder.tvType.setText(item.getType());

        holder.tvName.setText(item.getName());

        holder.tvLocation.setText(item.getLocation());

        holder.tvTimestamp.setText(item.getTimestamp());

        holder.itemView.setOnClickListener(v -> {

            Intent intent = new Intent(
                    context,
                    ItemDetailActivity.class
            );

            intent.putExtra("id", item.getId());
            intent.putExtra("type", item.getType());
            intent.putExtra("name", item.getName());
            intent.putExtra("phone", item.getPhone());
            intent.putExtra("description", item.getDescription());
            intent.putExtra("date", item.getDate());
            intent.putExtra("location", item.getLocation());

            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ViewHolder
            extends RecyclerView.ViewHolder {

        TextView tvType,
                tvName,
                tvLocation,
                tvTimestamp;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvType = itemView.findViewById(R.id.tvType);

            tvName = itemView.findViewById(R.id.tvName);

            tvLocation = itemView.findViewById(R.id.tvLocation);

            tvTimestamp = itemView.findViewById(R.id.tvTimestamp);
        }
    }
}
