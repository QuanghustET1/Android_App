package com.android.project_androidapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.project_androidapp.Adapter.categoryListAdapter;
import com.android.project_androidapp.DB.Database;
import com.android.project_androidapp.Domain.foodDomain;
import com.android.project_androidapp.R;

import java.util.ArrayList;

public class showCategoryList extends AppCompatActivity {
    private TextView listcategoryName;
    private RecyclerView recyclerListCategory;
    private Database foodCategory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_category_list);
        this.foodCategory = new Database(this);
        initView();
        showListItemCategory();
    }

    private void showListItemCategory() {
        ArrayList<foodDomain> arrFood = this.foodCategory.getFoodListwithCategoryID(getIntent().getIntExtra("categoryID",1));
        Log.i("categoryID", String.valueOf(getIntent().getIntExtra("categoryID",1)));
        this.recyclerListCategory.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        this.recyclerListCategory.setAdapter(new categoryListAdapter(arrFood));
    }

    private void initView() {
        this.listcategoryName = findViewById(R.id.listcategoryName);
        this.recyclerListCategory = findViewById(R.id.recyclerListCategory);
        int categoryID = this.foodCategory.getFoodListwithCategoryID(getIntent().getIntExtra("categoryID",1)).get(0).getCategoryID();
        this.listcategoryName.setText((categoryID == 1)?"Pizza":((categoryID == 2)?"Rice":((categoryID == 3)?"Salad":((categoryID == 4)?"Hotdog":"Hamburger"))));
    }
}