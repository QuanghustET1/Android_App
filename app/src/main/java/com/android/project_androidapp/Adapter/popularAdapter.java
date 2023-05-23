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
import com.android.project_androidapp.Domain.categoryList;
import com.android.project_androidapp.Domain.foodDomain;
import com.android.project_androidapp.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

//Cho nay nhu kieu framwork, khong can hieu het
public class popularAdapter extends RecyclerView.Adapter<popularAdapter.ViewHolder> {
    //Tao ra list category de truyen vao ham tao de nhan tham so la list category truyen vao tu ben kia
    ArrayList<foodDomain> foodDomainsLists;

    //tao ra mot category duoc cau hinh va co data la list category
    public popularAdapter(ArrayList<foodDomain> foodDomainsLists) {
        this.foodDomainsLists = foodDomainsLists;
    }


    @Override
    public popularAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //ham nay giong kieu set Layout nao de lam viec, giong ham setContentView(R.layout.activity_main) trong ham main
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_popular, parent, false);
        return new ViewHolder(inflate);
    }

    //ham nay de cau hinh xem cac category se duoc hien thi nhu the nao
    @Override
    public void onBindViewHolder(@NonNull popularAdapter.ViewHolder holder, int position) {
        int n = position;
        //Set ten cua category bang cach lay ten theo vi tri cua category trong list(mang) truyen vao
        holder.foodTitle.setText(foodDomainsLists.get(position).getTitle());
        holder.foodFee.setText(foodDomainsLists.get(position).getFee().toString());
        //Tao bien ten anh background
//        String picUrl = "";
        //Set background theo vi tri category trong List(mang) truyen vao
        switch (position){
            case 0:{
                holder.foodConstrain.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.category_background1));
                break;
            }
            case 1:{
                holder.foodConstrain.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.category_background2));
                break;
            }
            case 2:{
                holder.foodConstrain.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.category_background3));
                break;
            }
            case 3:{
                holder.foodConstrain.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.category_background3));
                break;
            }
            case 4:{
                holder.foodConstrain.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.category_background2));
                break;
            }
            case 5:{
                holder.foodConstrain.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.category_background1));
                break;
            }
        }
        //Ham nay de lay avatar picUrl theo ten picUrl lay duoc ben tren
        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(foodDomainsLists.get(n).getPic(), "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.foodImage);
        holder.addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), ShowDetailActivity.class);

                intent.putExtra("object", foodDomainsLists.get(n));
                holder.itemView.getContext().startActivity(intent);
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
        TextView foodTitle;
        ImageView foodImage;
        TextView foodFee;
        ConstraintLayout addToCartBtn;
        ConstraintLayout foodConstrain;
        public ViewHolder(View inflate) {
            super(inflate);
            foodTitle = itemView.findViewById(R.id.foodTitle);
            foodImage = itemView.findViewById(R.id.foodImage);
            foodFee = itemView.findViewById(R.id.foodFee);
            addToCartBtn = itemView.findViewById((R.id.addToCartBtn));
            foodConstrain = itemView.findViewById(R.id.foodConstrain);
        }
    }
}
