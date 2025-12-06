/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.util.ArrayList;
import java.util.List;
import models.I_Menu;
import tools.Utils;

/**
 *
 * @author pc
 */
public class Menu implements I_Menu{
   private List<String> items = new ArrayList<>();
    @Override
    public void addItem(String s) {
        items.add(s);
    }

    @Override
    public int getChoice() {
        return Utils.getInt("Your choice: ",1,items.size());
    }

    @Override
    public void showMenu() {
        for (String item : items){
            System.out.println(item);
        }
    }
    
}
