package com.android.project_androidapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.project_androidapp.DB.Database;
import com.android.project_androidapp.Domain.foodDomain;
import com.android.project_androidapp.R;
import com.bumptech.glide.Glide;

public class ShowDetailActivity extends AppCompatActivity {
    private TextView detailTitile, detailFoodFee, detailNumOrder, detailMinusCart, detailPlusCart, detailFoodDetail, detail_addToCartBtn;
    private ImageView detailFoodImg;
    private foodDomain object;
    private int numOder = 1;
    private Database database_;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);
        database_ = new Database(this);
        initView();
        getBundle();
    }

    private void getBundle() {
        //Lấy object có tên "object" từ đầu Intent bên kia sang
        this.object = (foodDomain) getIntent().getSerializableExtra("object");
        //Lấy từ drawable ảnh có tên this.object.getPic()
        int drawableResource = this.getResources().getIdentifier(this.object.getPic(), "drawable", this.getPackageName());
        //load ảnh lấy được ở trên vào detailFoodImg trong Activity này
        Glide.with(this).load(drawableResource).into(this.detailFoodImg);
        this.detailTitile.setText(object.getTitle());
        this.detailFoodDetail.setText(object.getDescription());
        this.detailFoodFee.setText(String.valueOf(object.getFee()));
        this.detailNumOrder.setText(String.valueOf(numOder));
        this.detailMinusCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ShowDetailActivity.this.numOder == 0){
                    ShowDetailActivity.this.detailNumOrder.setText(String.valueOf(0));
                }
                else{
                    ShowDetailActivity.this.numOder -= 1;
                    ShowDetailActivity.this.detailNumOrder.setText(String.valueOf(ShowDetailActivity.this.numOder));
                }
            }
        });
        this.detailPlusCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowDetailActivity.this.numOder += 1;
                ShowDetailActivity.this.detailNumOrder.setText(String.valueOf(ShowDetailActivity.this.numOder));
            }
        });
        this.detail_addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("addToCart", "clicked");
                object.setNumberInCart(ShowDetailActivity.this.numOder);
                database_.insertFoodToCart(object);
                startActivity(new Intent(ShowDetailActivity.this, CartManager.class).putExtra("cartManage", database_.getListFoodFromCart()));
            }
        });
    }

    private void initView() {
        this.detailTitile = findViewById(R.id.detailTitile);
        this.detailFoodFee = findViewById(R.id.detailFoodFee);
        this.detailFoodImg = findViewById(R.id.detailFoodImg);
        this.detailNumOrder = findViewById(R.id.detailNumOrder);
        this.detailPlusCart = findViewById(R.id.detailPlusCart);
        this.detailMinusCart = findViewById(R.id.detailMinusCart);
        this.detailFoodDetail = findViewById(R.id.detailFoodDetail);
        this.detail_addToCartBtn = findViewById(R.id.detail_addToCartBtn);
    }
}