package com.android.project_androidapp.Domain;

public class OrderHistoryFood {
    private String foodName;
    private String pic;
    private int numOrder;
    private double totalPay;
    private String address;
    private String phoneNumber;
    private String date;

    public OrderHistoryFood() {
    }

    public OrderHistoryFood(String foodName, String pic, int numOrder, double totalPay, String address, String phoneNumber, String date) {
        this.foodName = foodName;
        this.pic = pic;
        this.numOrder = numOrder;
        this.totalPay = totalPay;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.date = date;
    }


    public OrderHistoryFood(String address, String date, String foodName, int numOrder, String phoneNumber, String pic, double totalPay) {
        this.foodName = foodName;
        this.pic = pic;
        this.numOrder = numOrder;
        this.totalPay = totalPay;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.date = date;
    }

    public String getFoodName() {
        return foodName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getNumOrder() {
        return numOrder;
    }

    public void setNumOrder(int numOrder) {
        this.numOrder = numOrder;
    }

    public double getTotalPay() {
        return totalPay;
    }

    public void setTotalPay(double totalPay) {
        this.totalPay = totalPay;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
