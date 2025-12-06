/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.List;


public interface I_Car {
    List<Car> listCars();                     
    List<Car> searchCarsByBrandName(String partial); 
    boolean addCar();                          
    boolean removeCarByID(String carID);      
    boolean updateCarByID(String carID);       
    List<Car> listCarsByColor(String color);    
}
