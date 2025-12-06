/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;

/**
 *
 * @author pc
 */
public class Brand implements Serializable{
    private String brandID;
    private String brandName;
    private String soundBrand;
    private double price;

    public Brand() {}
    public Brand(String brandID, String brandName, String soundBrand, double price) {
        this.brandID = brandID;
        this.brandName = brandName;
        this.soundBrand = soundBrand;
        this.price = price;
    }

    public String getBrandID() {
        return brandID;
    }

    public void setBrandID(String brandID) {
        this.brandID = brandID;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getSoundBrand() {
        return soundBrand;
    }

    public void setSoundBrand(String soundBrand) {
        this.soundBrand = soundBrand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

   
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Brand) {
            return this.brandID.equalsIgnoreCase(((Brand)obj).brandID);
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("%-10s | %-35s | %-20s | %.3fB", brandID, brandName, soundBrand, price);
    }

    public String toFileLine() {
        return String.format("%s, %s, %s: %.3fB",brandID, brandName, soundBrand, price);
    }
}
