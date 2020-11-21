package com.rs132studio.meatgrocery.Model;

public class ChickenCategory {
    private int chickenImage;
    private String chickenBreed;
    private String chickenRate;
    private String chickenQuantity;

    public ChickenCategory(int chickenImage, String chickenBreed, String chickenRate, String chickenQuantity) {
        this.chickenImage = chickenImage;
        this.chickenBreed = chickenBreed;
        this.chickenRate = chickenRate;
        this.chickenQuantity = chickenQuantity;
    }

    public int getChickenImage() {
        return chickenImage;
    }

    public void setChickenImage(int chickenImage) {
        this.chickenImage = chickenImage;
    }

    public String getChickenBreed() {
        return chickenBreed;
    }

    public void setChickenBreed(String chickenBreed) {
        this.chickenBreed = chickenBreed;
    }

    public String getChickenRate() {
        return chickenRate;
    }

    public void setChickenRate(String chickenRate) {
        this.chickenRate = chickenRate;
    }

    public String getChickenQuantity() {
        return chickenQuantity;
    }

    public void setChickenQuantity(String chickenQuantity) {
        this.chickenQuantity = chickenQuantity;
    }
}
