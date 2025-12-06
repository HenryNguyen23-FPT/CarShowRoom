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
public class Car implements Serializable{
    private String carID;
    private Brand brand;
    private String color;
    private String frameID;
    private String engineID;

    public Car(String carID, Brand brand, String color, String frameID, String engineID) {
        this.carID = carID;
        this.brand = brand;
        this.color = color;
        this.frameID = frameID;
        this.engineID = engineID;
    }

    public Car() {
    }

    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFrameID() {
        return frameID;
    }

    public void setFrameID(String frameID) {
        this.frameID = frameID;
    }

    public String getEngineID() {
        return engineID;
    }

    public void setEngineID(String engineID) {
        this.engineID = engineID;
    }
    

    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Car) {
            return this.carID.equalsIgnoreCase(((Car) obj).carID);
        }
        return false;
    }

     @Override
    public String toString() {
        return String.format("%-10s | %-35s | %-15s | %-10s | %-10s | %.3fB", carID, brand.getBrandName(), color, frameID, engineID, brand.getPrice());
    }
}
