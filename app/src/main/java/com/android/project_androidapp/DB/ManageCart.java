package com.android.project_androidapp.DB;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.project_androidapp.Domain.foodDomain;

import java.util.ArrayList;

public class ManageCart {
    private Context context;
    private TinyDB tinyDB;

    public ManageCart(Context context) {
        this.context = context;
        this.tinyDB = new TinyDB(context);
    }
    public ArrayList<foodDomain> getListCart(){
        return tinyDB.getListObject("CartList");
    }
    public double totalcostOfListItem(){
        ArrayList<foodDomain> arrFood = getListCart();
        double sumCost = 0;
        for(int i = 0; i < arrFood.size(); i++){
            sumCost += arrFood.get(i).getFee()*arrFood.get(i).getNumberInCart();
        }
        return sumCost;
    }
    public void deleteFood(int position){
        ArrayList<foodDomain> arrFood = getListCart();
        arrFood.remove(position);
        tinyDB.putListObject("CartList", arrFood);
    }
    public void insertFoodToCart(foodDomain food){
        ArrayList<foodDomain> foodList = getListCart();
        boolean isExit = false;
        int n = 0;
        for (int i = 0; i < foodList.size(); i++) {
            if(foodList.get(i).getTitle().equals(food.getTitle())){
                isExit = true;
                n = i;
                break;
            }
        }
        if(isExit){
            foodList.get(n).setNumberInCart(food.getNumberInCart());
        }
        else {
            foodList.add(food);
        }
        tinyDB.putListObject("CartList", foodList);
        Log.i("inserted", "insertFoodToCart: ok");
        Toast.makeText(context, "Added to Your Cart!", Toast.LENGTH_SHORT).show();
    }
}
