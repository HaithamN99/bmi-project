package com.example.myapplication.android;

import com.example.myapplication.foodInfo;
import com.example.myapplication.status;

import java.util.ArrayList;

public class user {


    String username;
    String pass_user;
    ArrayList<status> s;
    String Birth_date;
    int age;
    ArrayList<foodInfo>f;

    public user(String username, String pass_user, ArrayList<status> s, String birth_date, int age, ArrayList<foodInfo> f) {
        this.username = username;
        this.pass_user = pass_user;
        this.s = s;
        Birth_date = birth_date;
        this.age = age;
        this.f = f;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass_user() {
        return pass_user;
    }

    public void setPass_user(String pass_user) {
        this.pass_user = pass_user;
    }

    public ArrayList<status> getS() {
        return s;
    }

    public void setS(ArrayList<status> s) {
        this.s = s;
    }

    public String getBirth_date() {
        return Birth_date;
    }

    public void setBirth_date(String birth_date) {
        Birth_date = birth_date;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public ArrayList<foodInfo> getF() {
        return f;
    }

    public void setF(ArrayList<foodInfo> f) {
        this.f = f;
    }
}
