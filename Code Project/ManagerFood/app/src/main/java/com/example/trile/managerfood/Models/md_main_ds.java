package com.example.trile.managerfood.Models;

/**
 * Created by TRILE on 22/03/2018.
 */

public class md_main_ds {
    private String img;
    private String name;
    private String desciption;
    private String type;

    public md_main_ds() {
        //
    }

    public md_main_ds(String img, String name, String desciption, String type) {
        this.img = img;
        this.name = name;
        this.desciption = desciption;
        this.type = type;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
