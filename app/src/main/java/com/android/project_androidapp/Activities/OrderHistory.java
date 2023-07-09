package com.android.project_androidapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.project_androidapp.Adapter.historyOrderAdapter;
import com.android.project_androidapp.Domain.OrderHistoryFood;
import com.android.project_androidapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class OrderHistory extends AppCompatActivity {
    private DatabaseReference historyOrderDB;
    private RecyclerView listHistoryOrder;
    private AppCompatButton backToHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);
        this.historyOrderDB = FirebaseDatabase.getInstance().getReference("db_historyOrder");
        showListHistoryOrder();
        backToHomePage();
    }

    private void backToHomePage() {
        this.backToHome = findViewById(R.id.backToHomePage);
        this.backToHome.setOnClickListener(view -> startActivity(new Intent(OrderHistory.this, MainActivity.class).putExtra("user", MainActivity.user)));
    }

    private void showListHistoryOrder() {
        this.listHistoryOrder = findViewById(R.id.listHistoryorder);
        this.listHistoryOrder.setLayoutManager(new LinearLayoutManager(OrderHistory.this, LinearLayoutManager.VERTICAL, false));
        this.historyOrderDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<OrderHistoryFood> orderHistories = new ArrayList<>();
                for(DataSnapshot ds : snapshot.getChildren()){
                    String key = ds.getKey();
                    String user = key.split("-")[0];
                    if(user.equals(MainActivity.user.getUserName())){
                        orderHistories.add(ds.getValue(OrderHistoryFood.class));
                    }
                }
                OrderHistory.this.listHistoryOrder.setAdapter(new historyOrderAdapter(orderHistories));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}