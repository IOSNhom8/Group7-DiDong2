package com.example.trile.managerfood.Models;

/**
 * Created by TRILE on 19/04/2018.
 */

public class mdSpinner {
    private int imgSpinner;
    private String strType;

    public mdSpinner(int imgSpinner, String strType) {
        this.imgSpinner = imgSpinner;
        this.strType = strType;
    }

    public mdSpinner() {
    }

    public int getImgSpinner() {
        return imgSpinner;
    }

    public void setImgSpinner(int imgSpinner) {
        this.imgSpinner = imgSpinner;
    }

    public String getStrType() {
        return strType;
    }

    public void setStrType(String strType) {
        this.strType = strType;
    }
}
