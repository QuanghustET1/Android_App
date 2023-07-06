package com.android.project_androidapp.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.android.project_androidapp.Domain.foodDomain;
import com.android.project_androidapp.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class OrderItemAdapter extends RecyclerView.Adapter<OrderItemAdapter.ViewHolder> {
    private ArrayList<foodDomain> listOrderFood;

    public OrderItemAdapter(ArrayList<foodDomain> listOrderFood) {
        this.listOrderFood = listOrderFood;
        for (foodDomain foodDomain : listOrderFood){
            Log.i("kok", foodDomain.toString());
        }
    }

    @Override
    public OrderItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderItemAdapter.ViewHolder holder, int position) {
        holder.titleOrderItem.setText(this.listOrderFood.get(position).getTitle());
        holder.priceOrderItem.setText(String.valueOf(this.listOrderFood.get(position).getFee()));
        holder.numOrderItem.setText(String.valueOf(this.listOrderFood.get(position).getNumberInCart()));
        int ResourceImage = holder.itemView.getContext().getResources().getIdentifier(this.listOrderFood.get(position).getPic(), "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext()).load(ResourceImage).into(holder.imageOrderItem);
        switch (position){
            case 0:{
                holder.itemOrder.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.category_background1));
                break;
            }
            case 1:{
                holder.itemOrder.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.category_background2));
                break;
            }
            case 2:{
                holder.itemOrder.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.category_background3));
                break;
            }
            case 3:{
                holder.itemOrder.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.category_background4));
                break;
            }
            case 4:{
                holder.itemOrder.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.category_background5));
                break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return this.listOrderFood.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageOrderItem;
        private TextView titleOrderItem;
        private TextView priceOrderItem;
        private TextView numOrderItem;
        private ConstraintLayout itemOrder;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imageOrderItem = itemView.findViewById(R.id.imageOrderItem);
            this.titleOrderItem = itemView.findViewById(R.id.titleOrderItem);
            this.priceOrderItem = itemView.findViewById(R.id.priceOrderItem);
            this.numOrderItem = itemView.findViewById(R.id.numOrderItem);
            this.itemOrder = itemView.findViewById(R.id.itemOrder);
        }
    }
}
