/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Utils {
    private static final String BRAND_ID_REGEX = "^B.{3,7}$";
    private static final String CAR_ID_REGEX = "^C\\d{2,}$";             
    private static final String FRAME_ID_REGEX = "^F\\d{5}$";           
    private static final String ENGINE_ID_REGEX = "^E\\d{5}$";          

    public static boolean isValidBrandID(String id) {
        return id != null && id.matches(BRAND_ID_REGEX);
    }

    public static boolean isValidCarID(String id) {
        return id != null && id.matches(CAR_ID_REGEX);
    }

    public static boolean isValidFrameID(String id) {
        return id != null && id.matches(FRAME_ID_REGEX);
    }

    public static boolean isValidEngineID(String id) {
        return id != null && id.matches(ENGINE_ID_REGEX);
    }
    
    
    public static String getString(String msg){
        Scanner sc = new Scanner(System.in);
        String s;
        do {
            System.out.print(msg);
            s = sc.nextLine().trim();
            if(s.isEmpty()){
                System.out.println("Input cannot be empty!");
            }
        } while (s.isEmpty());
        return s;
    }

    public static int getInt(String msg, int min, int max){
        Scanner sc = new Scanner(System.in);
        int n;
        while (true){
            try {
                System.out.print(msg);
                String input = sc.nextLine().trim(); 
                n = Integer.parseInt(input);
                if(n < min || n > max){
                    System.out.println("Number must be in range ["+ min +","+ max +"]");
                } else {
                    return n;
                }
            } catch (NumberFormatException e){
                System.out.println("Enter a valid number!");
            }
        }
    }

    public static boolean confirmYesNo(String msg){
        String s;
        do {
            s = getString(msg + " (Y/N): ");
            if (s.equalsIgnoreCase("Y")) 
                return true;
            if (s.equalsIgnoreCase("N")) 
                return false;
            System.out.println("Please enter Y or N!");
        } while (true);
    }

    public static String updateString(String msg, String oldData){
        Scanner sc = new Scanner(System.in);
        System.out.print(msg+" (Enter to keep '"+ oldData +"'): ");
        String input = sc.nextLine().trim();
        if(input.isEmpty()){
            return oldData;
        } else {
            return input;
        }
    }
    public static double updateDouble(String msg, double oldVal) {
        Scanner sc = new Scanner (System.in);
        System.out.print(msg + " (Enter to keep " + oldVal + "): ");
        String s = sc.nextLine().trim();
        if (s.isEmpty()) 
            return oldVal;
        try {
            double val = Double.parseDouble(s);
            if (val <= 0) 
                throw new NumberFormatException();
                return val;
        } catch (Exception e) {
            System.out.println("Invalid number and keep old value.");
            return oldVal;
        }
    }

    public static List<String> readLines(String path) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) lines.add(line.trim());
            }
        } catch (IOException e) {
            System.out.println("Cannot read file: " + path);
        }
        return lines;
    }

    public static boolean writeLines(String path, List<String> lines) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path))) {
            for (String s : lines) 
                pw.println(s);
            return true;
        } catch (IOException e) {
            System.out.println("Cannot write file: " + path);
            return false;
        }
    }


    public static String formatPrice(double price) {
        return String.format("%.3fB", price);
    }
}
