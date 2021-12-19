package com.example.myapplication;

public class foodInfo
{

    String foodName;
    String foodCatagory;
    int cal;
    String img;

    public foodInfo() {
    }

    public foodInfo(String foodName, String foodCatagory, int cal, String img) {
        this.foodName = foodName;
        this.foodCatagory = foodCatagory;
        this.cal = cal;
        this.img = img;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodCatagory() {
        return foodCatagory;
    }

    public void setFoodCatagory(String foodCatagory) {
        this.foodCatagory = foodCatagory;
    }

    public int getCal() {
        return cal;
    }

    public void setCal(int cal) {
        this.cal = cal;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}