package com.rs132studio.meatgrocery.Model;

public class CartItem {
    private int id;
    private String meatName;
    private String meatRate;
    private String meatQuantity;
    private String addDate;

    public CartItem() {

    }
    public CartItem(int id, String meatName, String meatRate, String meatQuantity, String addDate) {
        this.id = id;
        this.meatName = meatName;
        this.meatRate = meatRate;
        this.meatQuantity = meatQuantity;
        this.addDate = addDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMeatName() {
        return meatName;
    }

    public void setMeatName(String meatName) {
        this.meatName = meatName;
    }

    public String getMeatRate() {
        return meatRate;
    }

    public void setMeatRate(String meatRate) {
        this.meatRate = meatRate;
    }

    public String getMeatQuantity() {
        return meatQuantity;
    }

    public void setMeatQuantity(String meatQuantity) {
        this.meatQuantity = meatQuantity;
    }

    public String getAddDate() {
        return addDate;
    }

    public void setAddDate(String addDate) {
        this.addDate = addDate;
    }
}
