/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.List;


public interface I_Brand {
    void listBrands();                    
    boolean addBrand();                   
    Brand searchBrandByID(String id);     
    boolean updateBrandByID(String id);   
    List<Brand> listBrandsByPrice(String maxPrice);
}
