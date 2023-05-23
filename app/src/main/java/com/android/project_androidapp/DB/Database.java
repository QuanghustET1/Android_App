package com.android.project_androidapp.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;

import com.android.project_androidapp.Domain.foodDomain;
import com.android.project_androidapp.Domain.userDomain;

import java.util.ArrayList;

//SQLiteOpenHelper cho phep ke thua lai va custom lai cach crud db theo cach rieng cua minh
public class Database extends SQLiteOpenHelper {
    private static final String DB_NAME = "Database";
    private static final int DB_VERSION = 1;
    private static final String DB_TABLE_CARTMANAGE = "cartTable";
    private static final String DB_TABLE_CATEGORYITEMLIST = "categoryTable";
    private static final String DB_USER = "userTable";
    //TEN cac truong trong db table
    private static String title = "title";
    private static String pic = "pic";
    private static String description = "description";
    private static String fee = "fee";
    private static String numberInCart = "numberInCart";
    private static String categoryID = "categoryID";
    //ten cac field trong user table
    private static String UserName = "userName";
    private static String UserPassword = "userPassword";
    public Database(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable_query_cartManage = String.format("CREATE TABLE %s(%s STRING PRIMARY KEY, %s STRING, %s STRING, %s DOUBLE, %s INTEGER, %s INTEGER)", this.DB_TABLE_CARTMANAGE, this.title, this.pic, this.description, this.fee, this.numberInCart, this.categoryID);
        String createTable_query_categoryItemList = String.format("CREATE TABLE %s(%s STRING PRIMARY KEY, %s STRING, %s STRING, %s DOUBLE, %s INTEGER, %s INTEGER)", this.DB_TABLE_CATEGORYITEMLIST, this.title, this.pic, this.description, this.fee, this.numberInCart, this.categoryID);
        String create_UserTable = String.format("CREATE TABLE %s(%s STRING PRIMARY KEY, %s STRING)", this.DB_USER, this.UserName, this.UserPassword);
        sqLiteDatabase.execSQL(createTable_query_cartManage);
        sqLiteDatabase.execSQL(createTable_query_categoryItemList);
        sqLiteDatabase.execSQL(create_UserTable);
        Log.i("create DBCart", "ok");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String upgradeTableCartTable = String.format("DROP TABLE IF EXISTS %s", DB_TABLE_CARTMANAGE);
        String upgradeTableCategoryTable = String.format("DROP TABLE IF EXISTS %s", DB_TABLE_CATEGORYITEMLIST);
        sqLiteDatabase.execSQL(upgradeTableCartTable);
        sqLiteDatabase.execSQL(upgradeTableCategoryTable);
        onCreate(sqLiteDatabase);
    }

    public boolean SignUpAccount(String username, String password){
        if(this.getUser(username) != null){
            return false;
        }
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(this.UserName,username);
        contentValues.put(this.UserPassword,password);
        sqLiteDatabase.insert(this.DB_USER, null,contentValues);
        sqLiteDatabase.close();
        return true;
    }
    
    public ArrayList<userDomain> getAllUsers(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ArrayList<userDomain> userDomainArrayList = new ArrayList<>();
        String query = "SELECT * FROM " + this.DB_USER;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if(cursor != null){
            cursor.moveToFirst();
            while(cursor.isAfterLast() == false){
                userDomain user = new userDomain(cursor.getString(0), cursor.getString(1));
                userDomainArrayList.add(user);
            }
            return userDomainArrayList;
        }
        else{
            return null;
        }
    }
    
    public userDomain getUser(String username){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(this.DB_USER,null,this.UserName+"=?",new String[]{username},null,null,null);
        try{
            if(cursor != null){
                cursor.moveToFirst();
                userDomain user = new userDomain(cursor.getString(0), cursor.getString(1));
                return user;
            }
        }
        catch (Exception e){
            Log.e("err", String.valueOf(e));
        }
        return null;
    }
    
    public boolean LoginUser(userDomain user){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        if(this.getUser(user.getUserName()) == null){
            return false;
        }
        else{
            if(this.getUser(user.getUserName()).getUserPassword().equals(user.getUserPassword())){
                return true;
            }
            return false;
        }
    } 

    public void insertFoodToCart(foodDomain food){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues record = new ContentValues();
        record.put("title", food.getTitle());
        record.put("pic", food.getPic());
        record.put("description", food.getDescription());
        record.put("fee", food.getFee());
        record.put("numberInCart", food.getNumberInCart());
        record.put("categoryID", food.getCategoryID());
        db.insert(DB_TABLE_CARTMANAGE, null, record);
        db.close();
    }
    public foodDomain getFoodFromCart(String foodName){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(DB_TABLE_CARTMANAGE, null,title+"=?",new String[]{foodName},null,null,null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        return new foodDomain(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getDouble(3), cursor.getInt(4), cursor.getInt(5));
    }
    public ArrayList<foodDomain> getListFoodFromCart(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ArrayList<foodDomain> foodList = new ArrayList<>();
        String query = "SELECT * FROM " + DB_TABLE_CARTMANAGE;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        cursor.moveToFirst();
        while(cursor.isAfterLast() == false){
            foodDomain food = new foodDomain(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getDouble(3), cursor.getInt(4), cursor.getInt(5));
            foodList.add(food);
            cursor.moveToNext();
        }
        return foodList;
    }
    public void updateFoodinCart(foodDomain food, int numberInCart){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues newFood = new ContentValues();
        newFood.put("pic", food.getPic());
        newFood.put("description", food.getDescription());
        newFood.put("fee", food.getFee());
        newFood.put("numberInCart", food.getNumberInCart());
        newFood.put("categoryID", food.getCategoryID());
        sqLiteDatabase.update(DB_TABLE_CARTMANAGE,newFood,title+"=?", new String[]{food.getTitle()});
        sqLiteDatabase.close();
    }
    public void deleteFoodinCart(int position){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ArrayList<foodDomain> foodList = getListFoodFromCart();
        foodDomain food = foodList.get(position);
        sqLiteDatabase.delete(DB_TABLE_CARTMANAGE,title+"=?",new String[]{food.getTitle()});
        sqLiteDatabase.close();
    }
    public void insertFoodToCategory(@NonNull foodDomain food){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues record = new ContentValues();
        record.put("title", food.getTitle());
        record.put("pic", food.getPic());
        record.put("description", food.getDescription());
        record.put("fee", food.getFee());
        record.put("numberInCart", food.getNumberInCart());
        record.put("categoryID", food.getCategoryID());
        db.insert(DB_TABLE_CATEGORYITEMLIST, null, record);
        db.close();
    }
    public void insertListFoodToDBCategory(@NonNull ArrayList<foodDomain> arrFood){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        for (int i = 0; i < arrFood.size(); i++) {
            ContentValues foodObj = new ContentValues();
            foodObj.put(Database.this.title, arrFood.get(i).getTitle());
            foodObj.put(Database.this.pic, arrFood.get(i).getPic());
            foodObj.put(Database.this.description, arrFood.get(i).getDescription());
            foodObj.put(Database.this.fee, arrFood.get(i).getFee());
            foodObj.put(Database.this.numberInCart, arrFood.get(i).getNumberInCart());
            foodObj.put(Database.this.categoryID, arrFood.get(i).getCategoryID());
            long j = sqLiteDatabase.insert(DB_TABLE_CATEGORYITEMLIST,null,foodObj);
            Log.i("insert", String.valueOf(j));
        }
        sqLiteDatabase.close();
    }
    public foodDomain getFoodfromCategory(String foodName){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(DB_TABLE_CATEGORYITEMLIST, null,title+"=?",new String[]{foodName},null,null,null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        return new foodDomain(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getDouble(3), cursor.getInt(4), cursor.getInt(5));
    }
    public ArrayList<foodDomain> getListFoodfromCategory(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ArrayList<foodDomain> foodList = new ArrayList<>();
        String query = "SELECT * FROM " + DB_TABLE_CATEGORYITEMLIST;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        cursor.moveToFirst();
        while(cursor.isAfterLast() == false){
            foodDomain food = new foodDomain(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getDouble(3), cursor.getInt(4), cursor.getInt(5));
            foodList.add(food);
            cursor.moveToNext();
        }
        return foodList;
    }
    public ArrayList<foodDomain> getFoodListwithCategoryID(int categoryID){
        ArrayList<foodDomain> arrayList = this.getListFoodfromCategory();
        ArrayList<foodDomain> foodList = new ArrayList<>();
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).getCategoryID() == categoryID){
                Log.i("arrayList.get(i).getCategoryID()", String.valueOf(arrayList.get(i).getCategoryID()));
                foodList.add(arrayList.get(i));
            }
        }
        return foodList;
    }
}
