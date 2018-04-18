package com.example.trile.managerfood.Models;

/**
 * Created by TRILE on 22/03/2018.
 */

public class md_main_ds {
    private int img;
    private String name;
    private String desciption;

    public md_main_ds() {
    }

    public md_main_ds(int img, String name, String desciption) {
        this.img = img;
        this.name = name;
        this.desciption = desciption;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }
}
