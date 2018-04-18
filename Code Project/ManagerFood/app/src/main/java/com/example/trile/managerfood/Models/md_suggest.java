package com.example.trile.managerfood.Models;

/**
 * Created by TRILE on 31/03/2018.
 */

public class md_suggest {
    private int img;
    private String name;
    private String descip;

    public md_suggest() {
        //
    }

    public md_suggest(int img, String name, String descip) {
        this.img = img;
        this.name = name;
        this.descip = descip;
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

    public String getDescip() {
        return descip;
    }

    public void setDescip(String descip) {
        this.descip = descip;
    }
}
