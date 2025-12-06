package business;

import java.util.*;
import models.*;
import tools.Utils;

public class BrandService extends ArrayList<Brand> implements I_Brand, I_File {

    @Override
    public void listBrands() {
        if (this.isEmpty()) {
            System.out.println("No brands available!");
            return;
        }
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        System.out.printf("%-10s | %-35s | %-20s | %-10s%n", "BrandID", "Brand Name", "Sound Brand", "Price");
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        for (Brand b : this) {
            System.out.println(b);
        }
        System.out.println("-----------------------------------------------------------------------------------------------------------");
    }

    @Override
    public boolean addBrand() {
        String id, name, sound;
        double price;

        do {
            id = Utils.getString("Enter brand ID: ");
            if (!Utils.isValidBrandID(id)) {
                System.out.println("Invalid Brand ID format!");
                id = null;
                continue;
            }
            if (searchBrandByID(id) != null) {
                System.out.println("Duplicate Brand ID!");
                id = null;
            }
        } while (id == null);
        listBrands();
        name = Utils.getString("Enter brand name: ");
        sound = Utils.getString("Enter sound brand: ");
        price = Utils.updateDouble("Enter price", 0);

        this.add(new Brand(id, name, sound, price));
        System.out.println("Brand added successfully!");
        return true;
    }

    @Override
    public Brand searchBrandByID(String id) {       
        for (Brand b : this) {
            if (b.getBrandID().equalsIgnoreCase(id)) {
                return b;
            }
        }
        return null;
    
    }
    public void printBrandByID(String id) {
        Brand b = searchBrandByID(id);
        listBrands();
        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.printf("| %-10s | %-35s | %-20s | %-10s  |\n","Brand ID", "Brand Name", "Sound Brand", "Price");
        System.out.println("-------------------------------------------------------------------------------------------");
        if (b != null) {
            System.out.printf("| %-10s | %-35s | %-20s | %-10s  |\n",b.getBrandID(), b.getBrandName(), b.getSoundBrand(), b.getPrice());
        } else {
            System.out.printf("| %-100s |\n", " Brand not found!");
        }
        System.out.println("-------------------------------------------------------------------------------------------");
    }

    @Override
    public boolean updateBrandByID(String id) {
        Brand b = searchBrandByID(id);
        if (b == null) {
            System.out.println("Brand not found!");
            return false;
        }

        String newName = Utils.updateString("Enter new brand name", b.getBrandName());
        String newSound = Utils.updateString("Enter new sound brand", b.getSoundBrand());
        double newPrice = Utils.updateDouble("Enter new price", b.getPrice());

        b.setBrandName(newName);
        b.setSoundBrand(newSound);
        b.setPrice(newPrice);

        System.out.println("Brand updated successfully!");
        return true;
    }

    @Override
    public List<Brand> listBrandsByPrice(String maxPrice) {
        List<Brand> result = new ArrayList<>();
        double max = Double.parseDouble(maxPrice);
        for (Brand b : this) {
            if (b.getPrice() <= max) {
                result.add(b);
        }
    }

        if (result.isEmpty()) {
            System.out.println("No brand found with price ≤ " + maxPrice);
        } else {
            System.out.println("-------------------------------------------------------------------------------------");
            System.out.printf("%-10s | %-35s | %-20s | %-10s%n", "BrandID", "Brand Name", "Sound Brand", "Price");
            System.out.println("-------------------------------------------------------------------------------------");
            for (Brand b : result) System.out.println(b);
            System.out.println("-------------------------------------------------------------------------------------");
        }
        return result;
    }

    @Override
    public void loadData(String path) {
        List<String> lines = Utils.readLines(path);
        this.clear();

        for (String line : lines) {
            try {
                String[] parts = line.split(",|:");
                if (parts.length < 3) continue;
                String id = parts[0].trim();
                String name = parts[1].trim();
                String sound = parts[2].trim();
                double price = 0;
                if (parts.length > 3) {
                    String priceStr = parts[3].replace("B", "").trim();
                    price = Double.parseDouble(priceStr);
                }

                this.add(new Brand(id, name, sound, price));
                } catch (Exception e) {
                    System.out.println("Error reading line: " + line);
                }
        }
    }

    @Override
    public boolean saveToFile(String path) {
        List<String> lines = new ArrayList<>();
        for (Brand b : this) {
            lines.add(b.toFileLine());
        }
        return Utils.writeLines(path, lines);
    }
}
