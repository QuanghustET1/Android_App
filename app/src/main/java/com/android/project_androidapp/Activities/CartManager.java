package com.android.project_androidapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.project_androidapp.Adapter.listCartItemAdapter;
import com.android.project_androidapp.DB.Database;
import com.android.project_androidapp.Domain.foodDomain;
import com.android.project_androidapp.R;

import java.util.ArrayList;

public class CartManager extends AppCompatActivity {
    private RecyclerView listItemInCart;
    private TextView numberOfItem;
    private TextView totalCostOfItem;
    private TextView checkoutBtn;
//    private ManageCart cartManage;
    private Database database_;
    private listCartItemAdapter listItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_manager);
//        cartManage = new ManageCart(this);
        database_ = new Database(this);
        initView();
        showListItem();
        checkoutItem();
    }

    public double sumOfCostListItem(Database dbManageCart){
        ArrayList<foodDomain> arrFood = dbManageCart.getListFoodFromCart();
        double sumCost = 0;
        for (int i = 0; i < arrFood.size(); i++) {
            sumCost += arrFood.get(i).getFee()*arrFood.get(i).getNumberInCart();
        }
        return sumCost;
    }

    private void checkoutItem() {
        this.checkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CartManager.this.totalCostOfItem.setText(String.format("%.2f", CartManager.this.sumOfCostListItem(CartManager.this.database_)));
            }
        });
    }

    private void showListItem() {
        this.listItemInCart.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        ArrayList<foodDomain> arrFood = (ArrayList<foodDomain>)getIntent().getSerializableExtra("cartManage");
        this.listItemAdapter = new listCartItemAdapter(arrFood, this.database_);
        this.listItemInCart.setAdapter(this.listItemAdapter);
        this.listItemAdapter.setOnItemClickListener(new listCartItemAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                arrFood.remove(position);
                CartManager.this.database_.deleteFoodinCart(position);
                CartManager.this.listItemAdapter.notifyItemRemoved(position);
            }
        });
        this.numberOfItem.setText(String.valueOf(arrFood.size()));
        this.totalCostOfItem.setText(String.format("%.2f", CartManager.this.sumOfCostListItem(CartManager.this.database_)));
    }

    private void initView() {
        this.listItemInCart = findViewById(R.id.recyclerViewCart);
        this.numberOfItem = findViewById(R.id.numberOfItem);
        this.totalCostOfItem = findViewById(R.id.totalCostOfItem);
        this.checkoutBtn = findViewById(R.id.checkoutBtn);
    }
}