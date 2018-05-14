package com.example.trile.managerfood.Models;

/**
 * Created by TRILE on 31/03/2018.
 */

public class md_suggest {
    private String img;
    private String name;
    private String descip;
    private String type;

    public md_suggest() {
        //
    }

    public md_suggest(String img, String name, String descip, String type) {
        this.img = img;
        this.name = name;
        this.descip = descip;
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

    public String getDescip() {
        return descip;
    }

    public void setDescip(String descip) {
        this.descip = descip;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
