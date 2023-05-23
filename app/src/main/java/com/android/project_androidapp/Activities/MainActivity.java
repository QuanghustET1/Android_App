package com.android.project_androidapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.project_androidapp.Adapter.categoryAdapter;
import com.android.project_androidapp.Adapter.popularAdapter;
import com.android.project_androidapp.DB.Database;
import com.android.project_androidapp.Domain.categoryList;
import com.android.project_androidapp.Domain.foodDomain;
import com.android.project_androidapp.R;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.AnimationTypes;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter recyclerAdapter;
    private RecyclerView recyclerViewCategoryList;
    private RecyclerView recyclerPopularViewFood;
    private RecyclerView.Adapter recyclerViewFoodAdapter;
    private FloatingActionButton btnShowListCart;
//    private ManageCart manageCart;
    private Database Database;
    private ImageSlider imageSlider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        this.manageCart = new ManageCart(this);
        this.Database = new Database(this);
        //Ham nay de set category vao Layout cho RecyclerView
        recyclerViewCategory();
        popularViewFood();
        showListCartManager();
        showSlider();
    }

    private void showSlider() {
        this.imageSlider = findViewById(R.id.imageSlider);
        ArrayList<SlideModel> imageList = new ArrayList<>();
        imageList.add(new SlideModel(this.getResources().getIdentifier("food1","drawable",this.getPackageName()), ScaleTypes.FIT));
        imageList.add(new SlideModel(this.getResources().getIdentifier("food2","drawable",this.getPackageName()), ScaleTypes.FIT));
        imageList.add(new SlideModel(this.getResources().getIdentifier("food3","drawable",this.getPackageName()), ScaleTypes.FIT));
        imageList.add(new SlideModel(this.getResources().getIdentifier("food4","drawable",this.getPackageName()), ScaleTypes.FIT));
        imageList.add(new SlideModel(this.getResources().getIdentifier("food5","drawable",this.getPackageName()), ScaleTypes.FIT));
        this.imageSlider.setImageList(imageList, ScaleTypes.FIT);
        this.imageSlider.setSlideAnimation(AnimationTypes.CUBE_IN);
    }

    private void showListCartManager() {
        this.btnShowListCart = findViewById(R.id.btnShowListCart);
        this.btnShowListCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CartManager.class).putExtra("cartManage", MainActivity.this.Database.getListFoodFromCart()));
            }
        });
    }

    private void popularViewFood() {
        recyclerPopularViewFood = findViewById(R.id.recyclerView1);
        recyclerPopularViewFood.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        ArrayList<foodDomain> foodDomainsList = new ArrayList<>();
        foodDomainsList.add(new foodDomain("Pepperoni Pizza", "pop_1", "pizza description", 9.6, 0, 1));
        foodDomainsList.add(new foodDomain("Potato Pizza", "pop_11", "potato description", 8.6, 0, 1));
        foodDomainsList.add(new foodDomain("Egg Pizza", "pop_12", "egg description", 9.5, 0, 1));
        foodDomainsList.add(new foodDomain("Meat Pizza", "pop_13", "meat description", 9.8, 0, 1));
        foodDomainsList.add(new foodDomain("Vegetable Pizza", "pop_2", "vegetable description", 8.9, 0, 1));
        foodDomainsList.add(new foodDomain("Rice", "pop_4", "rice description", 5.6, 0, 2));
        foodDomainsList.add(new foodDomain("Japan Rice", "pop_41", "japan rice description", 5.4, 0, 2));
        foodDomainsList.add(new foodDomain("Vietnam Rice", "pop_42", "viet nam rice description", 5.2, 0, 2));
        foodDomainsList.add(new foodDomain("Salad", "pop_5", "salad description", 8.8, 0, 3));
        foodDomainsList.add(new foodDomain("Mix Salad", "pop_52", "salad description", 8.6, 0, 3));
        foodDomainsList.add(new foodDomain("Vegetable Salad", "pop_51", "salad description", 8.1, 0, 3));
        foodDomainsList.add(new foodDomain("Hotdog", "pop_6", "Hotdog description", 7.5, 0, 4));
        foodDomainsList.add(new foodDomain("Cheese Burger", "pop_3", "burger description", 7.7, 0, 5));
        Database.insertListFoodToDBCategory(foodDomainsList);
        recyclerViewFoodAdapter = new popularAdapter(Database.getListFoodfromCategory());
        recyclerPopularViewFood.setAdapter(recyclerViewFoodAdapter);
    }

    private void recyclerViewCategory() {
        //Tao ra 1 LinearLayoutManager voi flow theo chieu ngang, vi cac item se sap xep theo chieu ngang
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        //Connect attribute recyclerViewCategoryList voi recyclerView tren layout
        recyclerViewCategoryList = findViewById(R.id.recyclerView);
        //Set LinearLayoutManager da setup vao recyclerViewList
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);
        //Tao ra List cac Category voi Ten va Ten_anh_background de truyen sang cho Adapter
        ArrayList<categoryList> arrayList = new ArrayList<>();
        arrayList.add(new categoryList("pizza", "pic_pizza", 1));
        arrayList.add(new categoryList("rice", "pic_rice", 2));
        arrayList.add(new categoryList("salad", "pic_salad", 3));
        arrayList.add(new categoryList("hotDog", "pic_hotdog", 4));
        arrayList.add(new categoryList("hambuger", "pic_hambuger", 5));
        //Truyen list Category vao Adapter de Config va tao ra doi tuong recyclerView.Adapter
        recyclerAdapter = new categoryAdapter(arrayList);
        //Set Adapter vao recyclerViewCategoryList
        recyclerViewCategoryList.setAdapter(recyclerAdapter);
    }
}