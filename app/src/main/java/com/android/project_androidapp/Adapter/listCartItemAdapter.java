package com.android.project_androidapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.android.project_androidapp.DB.Database;
import com.android.project_androidapp.Domain.foodDomain;
import com.android.project_androidapp.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

//Cho nay nhu kieu framwork, khong can hieu het
public class listCartItemAdapter extends RecyclerView.Adapter<listCartItemAdapter.ViewHolder> {
    //Tao ra list category de truyen vao ham tao de nhan tham so la list category truyen vao tu ben kia
    ArrayList<foodDomain> foodDomains;
    Database dbManageCart;
    OnItemClickListener onItemClickListener;

    //tao ra mot category duoc cau hinh va co data la list category
    public listCartItemAdapter(ArrayList<foodDomain> foodDomains, Database dbManageCart) {
        this.foodDomains = foodDomains;
        this.dbManageCart = dbManageCart;
    }

    //Tao interface cho onclickItem
    public interface OnItemClickListener{
        void OnItemClick(int position);
    }

    //Tao ham de su dung, anh xa this.onItemClickListener voi out class
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public listCartItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //ham nay giong kieu set Layout nao de lam viec, giong ham setContentView(R.layout.activity_main) trong ham main
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_cart, parent, false);
        return new ViewHolder(inflate, onItemClickListener);
    }

    //ham nay de cau hinh xem cac category se duoc hien thi nhu the nao
    @Override
    public void onBindViewHolder(@NonNull listCartItemAdapter.ViewHolder holder, int position) {
        //Set ten cua category bang cach lay ten theo vi tri cua category trong list(mang) truyen vao
        holder.nameItem.setText(foodDomains.get(position).getTitle());
        holder.costItem.setText(String.valueOf(foodDomains.get(position).getFee()));
        holder.numItem.setText(String.valueOf(foodDomains.get(position).getNumberInCart()));
        holder.totalcostItem.setText(String.format("%.2f", foodDomains.get(position).getNumberInCart()*foodDomains.get(position).getFee()));
        //Tao bien ten anh background
        String picUrl = foodDomains.get(position).getPic();
        //Set background theo vi tri category trong List(mang) truyen vao
        switch (position){
            case 0:{
                holder.constrainItemLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.category_background1));
                break;
            }
            case 1:{
                holder.constrainItemLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.category_background2));
                break;
            }
            case 2:{
                holder.constrainItemLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.category_background3));
                break;
            }
            case 3:{
                holder.constrainItemLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.category_background4));
                break;
            }
            case 4:{
                holder.constrainItemLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.category_background5));
                break;
            }
        }
        //Ham nay de lay avatar picUrl theo ten picUrl lay duoc ben tren
        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(picUrl, "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.avtItem);
        int n = position;
        holder.minusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(foodDomains.get(n).getNumberInCart() > 1){
                    foodDomains.get(n).setNumberInCart(foodDomains.get(n).getNumberInCart() - 1);
                    holder.numItem.setText(String.valueOf(foodDomains.get(n).getNumberInCart()));
                    holder.totalcostItem.setText(String.format("%.2f", foodDomains.get(n).getNumberInCart()*foodDomains.get(n).getFee()));
                    dbManageCart.updateFoodinCart(foodDomains.get(n),foodDomains.get(n).getNumberInCart());
                }
            }
        });
        holder.plusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                foodDomains.get(n).setNumberInCart(foodDomains.get(n).getNumberInCart() + 1);
                holder.numItem.setText(String.valueOf(foodDomains.get(n).getNumberInCart()));
                holder.totalcostItem.setText(String.format("%.2f", foodDomains.get(n).getNumberInCart()*foodDomains.get(n).getFee()));
                dbManageCart.updateFoodinCart(foodDomains.get(n),foodDomains.get(n).getNumberInCart());
            }
        });
//        holder.deleteItem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                cartManager.deleteFood(foodDomains.get(n));
//                Toast.makeText(holder.itemView.getContext(), "Deleted Item", Toast.LENGTH_SHORT).show();
//                notifyDataSetChanged();
//            }
//        });
    }


    @Override
    public int getItemCount() {
        return foodDomains.size();
    }

    //class nay chinh tao ra categoryAdapter.ViewHolder de truyen vao cac method tren
    public class ViewHolder extends RecyclerView.ViewHolder {
        //Mot category tren layout se co ConstrainLayout, textView de hien thi ten category, ImageView de hien thi hinh icon
        //Xem file viewholder_category.xml
        //category mau duoc tao ra trong file viewholder_category.xml chi la de lay Id cua ConstrainLayout, ImageView voi TextView thoi
        ConstraintLayout constrainItemLayout;
        ImageView avtItem;
        TextView nameItem;
        TextView minusItem, plusItem, numItem;
        TextView costItem, totalcostItem;
        TextView deleteItem;
        //Constructor
        //Ham nay la ham tao attributes, init View và setOnClick(dang khai bao, trien khai o cho khac)
        public ViewHolder(View inflate, OnItemClickListener onItemClickListener) {
            super(inflate);
            constrainItemLayout = itemView.findViewById(R.id.constrainItemLayout);
            avtItem = itemView.findViewById(R.id.avtItem);
            nameItem = itemView.findViewById(R.id.nameItem);
            minusItem = itemView.findViewById(R.id.minusItem);
            plusItem = itemView.findViewById(R.id.plusItem);
            numItem = itemView.findViewById(R.id.numItem);
            costItem = itemView.findViewById(R.id.costItem);
            totalcostItem = itemView.findViewById(R.id.totalcostItem);
            deleteItem = itemView.findViewById(R.id.deleteItem);
            deleteItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.OnItemClick(getAdapterPosition());
                }
            });
        }
        //Constructor
//        public ViewHolder(View inflate, OnItemClickListener onItemClickListener) {
//            super(inflate);
//            deleteItem.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    onItemClickListener.OnItemClick(getAdapterPosition());
//                }
//            });
//        }
    }
}
