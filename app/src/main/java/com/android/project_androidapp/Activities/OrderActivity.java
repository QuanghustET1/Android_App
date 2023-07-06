package com.android.project_androidapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.project_androidapp.Adapter.OrderItemAdapter;
import com.android.project_androidapp.Domain.OrderHistory;
import com.android.project_androidapp.Domain.foodDomain;
import com.android.project_androidapp.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;

public class OrderActivity extends AppCompatActivity {
    private EditText addressInput;
    private EditText phoneInput;
    private RecyclerView listItemOrder;
    private TextView totalPriceListItem;
    private TextView transportation_costs;
    private TextView reduce_transportation_costs;
    private TextView finalPrice;
    private AppCompatButton OrderNow;
    private ArrayList<foodDomain> listOrderFood;
    private DatabaseReference db_historyOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        this.db_historyOrder = FirebaseDatabase.getInstance().getReference("db_historyOrder");
        initView();
        showListItem();
        checkOrderInf();
    }

    private void checkOrderInf() {
        double sumPrice = 0;
        for(foodDomain food : this.listOrderFood){
            sumPrice += food.getFee()*food.getNumberInCart();
        }
        double shipCost = 100000;
        double reduceShipCost = 20000;
        this.totalPriceListItem.setText(String.format("%.2f", sumPrice));
        this.transportation_costs.setText(String.valueOf(shipCost));
        this.reduce_transportation_costs.setText(String.valueOf(reduceShipCost));
        this.finalPrice.setText(String.valueOf(sumPrice+shipCost-reduceShipCost));
        this.OrderNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(String.valueOf(OrderActivity.this.addressInput.getText()).equals("") || String.valueOf(OrderActivity.this.phoneInput.getText()).equals("")){
                    Toast.makeText(OrderActivity.this, "Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
                else {
                    for(foodDomain food : OrderActivity.this.listOrderFood){
                        String dateTime = LocalDateTime.now().toString();
                        String date = dateTime.split("T")[0];
                        String time = dateTime.split("T")[1].split("[.]")[0];
                        OrderHistory foodOrder = new OrderHistory(food.getTitle(), food.getPic(), food.getNumberInCart(), food.getFee()* food.getNumberInCart(),String.valueOf(OrderActivity.this.addressInput.getText()), String.valueOf(OrderActivity.this.phoneInput.getText()), date);
                        OrderActivity.this.db_historyOrder.child(MainActivity.user.getUserName()+"-"+foodOrder.getFoodName()+"-"+ time).setValue(foodOrder);
                    }

                    startActivity(new Intent(OrderActivity.this, OrderSuccessActivity.class));
                }
            }
        });
    }

    private void showListItem() {
        this.listOrderFood = (ArrayList<foodDomain>) getIntent().getSerializableExtra("orderFoodList");
        this.listItemOrder.setLayoutManager(new LinearLayoutManager(OrderActivity.this, LinearLayoutManager.VERTICAL, false));
        this.listItemOrder.setAdapter(new OrderItemAdapter(this.listOrderFood));
    }

    private void initView() {
        this.addressInput = findViewById(R.id.addressInput);
        this.phoneInput = findViewById(R.id.phoneInput);
        this.listItemOrder = findViewById(R.id.listItemOrder);
        this.totalPriceListItem = findViewById(R.id.totalPriceListItem);
        this.transportation_costs = findViewById(R.id.transportation_costs);
        this.reduce_transportation_costs = findViewById(R.id.reduce_transportation_costs);
        this.finalPrice = findViewById(R.id.finalPrice);
        this.OrderNow = findViewById(R.id.OrderNow);
        this.listOrderFood = new ArrayList<>();
    }
}