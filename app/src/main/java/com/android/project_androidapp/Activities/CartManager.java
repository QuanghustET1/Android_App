package com.android.project_androidapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.project_androidapp.Adapter.listCartItemAdapter;
import com.android.project_androidapp.DB.Database;
import com.android.project_androidapp.Domain.foodDomain;
import com.android.project_androidapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CartManager extends AppCompatActivity {
    private RecyclerView listItemInCart;
    private TextView numberOfItem;
    private TextView totalCostOfItem;
    private TextView checkoutBtn;
//    private ManageCart cartManage;
    private listCartItemAdapter listItemAdapter;
    private DatabaseReference db_cartManage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_manager);
//        cartManage = new ManageCart(this);
        db_cartManage = FirebaseDatabase.getInstance().getReference("db_cartManage");
        initView();
        showListItem();
    }


    private void showListItem() {
        CartManager.this.db_cartManage.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<foodDomain> listFoodinCart = new ArrayList<>();
                for(DataSnapshot ds : snapshot.getChildren()){
                    String idUser = ds.getKey().split("-")[1];
                    if(idUser.equals(MainActivity.user.getUserName())){
                        listFoodinCart.add(ds.getValue(foodDomain.class));
                    }
                }
                double sumCost = 0;
                for(foodDomain food : listFoodinCart){
                    sumCost += food.getFee()*food.getNumberInCart();
                }
                CartManager.this.listItemInCart.setLayoutManager(new LinearLayoutManager(CartManager.this, LinearLayoutManager.VERTICAL, false));
                CartManager.this.listItemAdapter = new listCartItemAdapter(listFoodinCart, MainActivity.user.getUserName());
                CartManager.this.listItemInCart.setAdapter(CartManager.this.listItemAdapter);
                CartManager.this.numberOfItem.setText(String.valueOf(listFoodinCart.size()));
                CartManager.this.totalCostOfItem.setText(String.format("%.2f", sumCost));
                new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                        // this method is called
                        // when the item is moved.
                        return false;
                    }

                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                        foodDomain itemToDelete = listFoodinCart.get(viewHolder.getAdapterPosition());

                        CartManager.this.db_cartManage.child(itemToDelete.getTitle()+"-"+MainActivity.user.getUserName()).setValue(null);
                        listFoodinCart.remove(viewHolder.getAdapterPosition());

                        CartManager.this.listItemAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());

                    }
                }).attachToRecyclerView(CartManager.this.listItemInCart);
                CartManager.this.checkoutBtn.setOnClickListener(view -> startActivity(new Intent(CartManager.this, OrderActivity.class).putExtra("orderFoodList", listFoodinCart)));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void initView() {
        this.listItemInCart = findViewById(R.id.recyclerViewCart);
        this.numberOfItem = findViewById(R.id.numberOfItem);
        this.totalCostOfItem = findViewById(R.id.totalCostOfItem);
        this.checkoutBtn = findViewById(R.id.checkoutBtn);
    }
}