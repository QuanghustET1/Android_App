package com.android.project_androidapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.project_androidapp.Domain.OrderHistoryFood;
import com.android.project_androidapp.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class historyOrderAdapter extends RecyclerView.Adapter<historyOrderAdapter.Viewholder> {
    ArrayList<OrderHistoryFood> foodHistoryOrder;
    public historyOrderAdapter(ArrayList<OrderHistoryFood> foodHistoryOrder){
        this.foodHistoryOrder = foodHistoryOrder;
    }

    @Override
    public historyOrderAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history_order, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull historyOrderAdapter.Viewholder holder, int position) {
        int ResourceImg = holder.itemView.getContext().getResources().getIdentifier(this.foodHistoryOrder.get(position).getPic(), "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext()).load(ResourceImg).into(holder.foodImg);
        holder.foodName.setText(this.foodHistoryOrder.get(position).getFoodName());
        holder.foodNumberOrder.setText(String.valueOf(this.foodHistoryOrder.get(position).getNumOrder()));
        holder.foodTotalPay.setText(String.format("%.2f", this.foodHistoryOrder.get(position).getTotalPay()));
        holder.foodAddressOrder.setText(this.foodHistoryOrder.get(position).getAddress());
        holder.historyPhoneNum.setText(this.foodHistoryOrder.get(position).getPhoneNumber());
        String[] timeRaw = this.foodHistoryOrder.get(position).getDate().split("-");
        holder.foodTimeOrder.setText(String.format("%s-%s-%s", timeRaw[2], timeRaw[1], timeRaw[0]));
    }

    @Override
    public int getItemCount() {
        return foodHistoryOrder.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        private ImageView foodImg;
        private TextView foodName;
        private TextView foodNumberOrder;
        private TextView foodTotalPay;
        private TextView historyPhoneNum;
        private TextView foodTimeOrder;
        private TextView foodAddressOrder;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            this.foodImg = itemView.findViewById(R.id.foodImg);
            this.foodName = itemView.findViewById(R.id.foodName);
            this.foodNumberOrder = itemView.findViewById(R.id.foodNumberOrder);
            this.foodTotalPay = itemView.findViewById(R.id.foodTotalPay);
            this.historyPhoneNum = itemView.findViewById(R.id.historyPhoneNum);
            this.foodTimeOrder = itemView.findViewById(R.id.foodTimeOrder);
            this.foodAddressOrder = itemView.findViewById(R.id.foodAddressOrder);
        }
    }
}
