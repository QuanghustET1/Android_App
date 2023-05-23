package com.android.project_androidapp.Domain;

import java.io.Serializable;

public class foodDomain implements Serializable {
    private String title;
    private String pic;
    private String description;
    private Double fee;
    private int numberInCart;
    private int categoryID;

    public foodDomain(String title, String pic, String description, Double fee, int numberInCart, int categoryID) {
        this.title = title;
        this.pic = pic;
        this.description = description;
        this.fee = fee;
        this.numberInCart = numberInCart;
        this.categoryID = categoryID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public int getNumberInCart() {
        return numberInCart;
    }

    public void setNumberInCart(int numberInCart) {
        this.numberInCart = numberInCart;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    @Override
    public String toString() {
        return "foodDomain{" +
                "title='" + title + '\'' +
                ", pic='" + pic + '\'' +
                ", description='" + description + '\'' +
                ", fee=" + fee +
                ", numberInCart=" + numberInCart +
                '}';
    }
}
