package com.android.project_androidapp.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.android.project_androidapp.Activities.ShowDetailActivity;
import com.android.project_androidapp.Activities.showCategoryList;
import com.android.project_androidapp.Domain.foodDomain;
import com.android.project_androidapp.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

//Cho nay nhu kieu framwork, khong can hieu het
public class categoryListAdapter extends RecyclerView.Adapter<categoryListAdapter.ViewHolder> {
    //Tao ra list category de truyen vao ham tao de nhan tham so la list category truyen vao tu ben kia
    ArrayList<foodDomain> foodDomainsLists;

    //tao ra mot category duoc cau hinh va co data la list category
    public categoryListAdapter(ArrayList<foodDomain> foodDomainsLists) {
        this.foodDomainsLists = foodDomainsLists;
    }


    @Override
    public categoryListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //ham nay giong kieu set Layout nao de lam viec, giong ham setContentView(R.layout.activity_main) trong ham main
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_listitem, parent, false);
        return new ViewHolder(inflate);
    }

    //ham nay de cau hinh xem cac category se duoc hien thi nhu the nao
    @Override
    public void onBindViewHolder(@NonNull categoryListAdapter.ViewHolder holder, int position) {
        int n = position;
        //Set ten cua category bang cach lay ten theo vi tri cua category trong list(mang) truyen vao
        holder.categoryItemName.setText(foodDomainsLists.get(position).getTitle());
        holder.categoryItemFee.setText(foodDomainsLists.get(position).getFee().toString());
        switch (position){
            case 0:{
                holder.constrainCategoryItemView.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.category_background1));
                break;
            }
            case 1:{
                holder.constrainCategoryItemView.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.category_background2));
                break;
            }
            case 2:{
                holder.constrainCategoryItemView.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.category_background3));
                break;
            }
            case 3:{
                holder.constrainCategoryItemView.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.category_background4));
                break;
            }
            case 4:{
                holder.constrainCategoryItemView.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.category_background5));
                break;
            }
        }
        //Ham nay de lay avatar picUrl theo ten picUrl lay duoc ben tren
        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(foodDomainsLists.get(n).getPic(), "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.categoryItemImg);
        holder.btnShowCategoryItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.itemView.getContext().startActivity(new Intent(holder.itemView.getContext(), ShowDetailActivity.class).putExtra("object", foodDomainsLists.get(n)));
            }
        });
    }


    @Override
    public int getItemCount() {
        return foodDomainsLists.size();
    }

    //class nay chinh tao ra categoryAdapter.ViewHolder de truyen vao cac method tren
    //class này chính là để connect các attribute sang bên layout
    public class ViewHolder extends RecyclerView.ViewHolder {
        //Mot category tren layout se co ConstrainLayout, textView de hien thi ten category, ImageView de hien thi hinh icon
        //Xem file viewholder_category.xml
        //category mau duoc tao ra trong file viewholder_category.xml chi la de lay Id cua ConstrainLayout, ImageView voi TextView thoi
        ImageView categoryItemImg;
        TextView categoryItemName;
        TextView categoryItemFee;
        ConstraintLayout btnShowCategoryItem;
        ConstraintLayout constrainCategoryItemView;
        public ViewHolder(View inflate) {
            super(inflate);
            categoryItemImg = itemView.findViewById(R.id.categoryItemImg);
            categoryItemName = itemView.findViewById(R.id.categoryItemName);
            categoryItemFee = itemView.findViewById(R.id.categoryItemFee);
            btnShowCategoryItem = itemView.findViewById((R.id.btnShowCategoryItem));
            constrainCategoryItemView = itemView.findViewById(R.id.constrainCategoryItemView);
        }
    }
}
