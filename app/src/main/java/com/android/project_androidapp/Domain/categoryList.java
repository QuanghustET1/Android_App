package com.android.project_androidapp.Domain;


//Class nay de tao ra Ten va Ten_anh_background de truyen sang Adapter, class nay khong can quan tam lam
public class categoryList {
    private int categoryID;
    private String name;
    private String picture;

    public categoryList(String name, String picture, int categoryID) {
        this.name = name;
        this.picture = picture;
        this.categoryID = categoryID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }
}
