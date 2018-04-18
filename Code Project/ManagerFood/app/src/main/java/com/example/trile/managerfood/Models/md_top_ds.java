package com.example.trile.managerfood.Models;

/**
 * Created by TRILE on 22/03/2018.
 */

public class md_top_ds {
    private int img;
    private String name;

    public md_top_ds() {
    }

    public md_top_ds(int img, String name) {
        this.img = img;
        this.name = name;
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


}
