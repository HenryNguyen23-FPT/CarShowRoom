package dispatcher;

import business.BrandService;
import business.CarService;
import java.io.IOException;
import java.util.List;
import models.I_Brand;
import models.I_Car;
import tools.Utils;

public class Main {
    public static void main(String[] args) throws IOException {
        String[] menuItems = {
            "1. List all brands",
            "2. Add a new brand",
            "3. Search a brand by ID",
            "4. Update a brand by ID",
            "5. List brands by price less than or equal to an input value",
            "6. List all cars in ascending order of brand names",
            "7. Search cars by partial brand name",
            "8. Add a new car",
            "9. Remove a car by ID",
            "10. Update a car by ID",
            "11. List all cars by a specific color",
            "12. Save data to files",
            "13. Quit program"
        };
        I_Brand brandList = new BrandService();
        I_Car carList = new CarService((BrandService) brandList);
        ((BrandService) brandList).loadData("brands.txt");
        ((CarService) carList).loadData("cars.txt");
        boolean changed = false;
        boolean quit = false;
        int choice;

        do {
            System.out.println("\n-------------------------------------");
            System.out.println("CAR SHOWROOM MANAGEMENT MENU");
            System.out.println("-------------------------------------");
            for (String item : menuItems) {
                System.out.println(item);
            }
            choice = Utils.getInt("Your choice (1-13): ", 1, 13);
            switch (choice) {
                case 1:
                    brandList.listBrands();
                    break;

                case 2:
                    if (brandList.addBrand()) 
                        changed = true;
                    break;

                case 3: {
                    String id = Utils.getString("Enter brand ID to search: ");
                    ((BrandService) brandList).printBrandByID(id);
                    break;
                    }

                case 4: {
                    String id = Utils.getString("Enter brand ID to update: ");
                    if (!brandList.updateBrandByID(id))
                        System.out.println("This brand does not exist!");
                    else
                        changed = true;
                    break;
                }

                case 5: {
                    String maxPrice = Utils.getString("Enter price (in billions): ");
                    brandList.listBrandsByPrice(maxPrice);
                    break;
                }
                
                case 6:
                    carList.listCars();
                    break;

                case 7: {
                    String keyword = Utils.getString("Enter partial brand name: ");
                    carList.searchCarsByBrandName(keyword);
                    break;
                }

                case 8:
                    if (carList.addCar()) 
                        changed = true;
                    break;

                case 9: {
                    String cid = Utils.getString("Enter car ID to remove: ");
                    if (!carList.removeCarByID(cid))
                        System.out.println("This car does not exist!");
                    else
                        changed = true;
                    break;
                }

                case 10: {
                    String cid = Utils.getString("Enter car ID to update: ");
                    if (!carList.updateCarByID(cid))
                        System.out.println("This car does not exist!");
                    else
                        changed = true;
                    break;
                }

                case 11: {
                    String color = Utils.getString("Enter color: ");
                    carList.listCarsByColor(color);
                    break;
                }

                case 12:
                    boolean saveB = ((BrandService) brandList).saveToFile("brands.txt");
                    boolean saveC = ((CarService) carList).saveToFile("cars.txt");
                    if (saveB && saveC) {
                        System.out.println("Data saved successfully to files!");
                        changed = false;
                    } else {
                        System.out.println("Failed to save data!");
                    }
                    break;

                case 13:
                    if (changed) {
                        boolean save = Utils.confirmYesNo("You have unsaved changes. Save before quitting?");
                        if (save) {
                            ((BrandService) brandList).saveToFile("brands.txt");
                            ((CarService) carList).saveToFile("cars.txt");
                            System.out.println("Changes saved successfully.");
                        }
                    }
                    quit = true;
                    break;
            }
        } while (!quit);
    }
}
