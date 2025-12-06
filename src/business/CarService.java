package business;

import java.util.*;
import models.*;
import tools.Utils;

public class CarService extends ArrayList<Car> implements I_Car, I_File {

    private BrandService brandList;

    public CarService(BrandService brandList) {
        this.brandList = brandList;
    }

    @Override
    public List<Car> listCars() {
        if (this.isEmpty()) {
            System.out.println("No cars available!");
            return Collections.emptyList();
        }    
        Collections.sort(this, new Comparator<Car>() {
            @Override
            public int compare(Car c1, Car c2) {
                int cmp = c1.getBrand().getBrandName().compareToIgnoreCase(c2.getBrand().getBrandName());
                if (cmp != 0){ 
                    return cmp;
                }
                    return Double.compare(c2.getBrand().getPrice(), c1.getBrand().getPrice());
            }
            });

        System.out.println("-----------------------------------------------------------------------------------------------------------");
        System.out.printf("%-10s | %-35s | %-15s | %-10s | %-10s | %s%n", "CarID", "Brand Name", "Color", "FrameID", "EngineID", "Price");
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        for (Car c : this) 
            System.out.println(c);
        System.out.println("-----------------------------------------------------------------------------------------------------------");

        return this;
    }

    @Override
    public List<Car> searchCarsByBrandName(String partial) {
        List<Car> result = new ArrayList<>();
        for (Car c : this) {
            if (c.getBrand().getBrandName().toLowerCase().contains(partial.toLowerCase())) {
                result.add(c);
            }
        }
        if (result.isEmpty()) {
            System.out.println("No car found for that brand name!");
        } else {
            System.out.println("-----------------------------------------------------------------------------------------------------------");
            System.out.printf("%-10s | %-35s | %-15s | %-10s | %-10s | %s%n", "CarID", "Brand Name", "Color", "FrameID", "EngineID", "Price");
            System.out.println("-----------------------------------------------------------------------------------------------------------");
            for (Car c : result) System.out.println(c);
            System.out.println("-----------------------------------------------------------------------------------------------------------");
        }
        return result;
    }

    @Override
    public boolean addCar() {
        String carID, color, frameID, engineID;
        Brand brand;
        listCars();
        do {
            carID = Utils.getString("Enter car ID (Cxx): ");
            if (!Utils.isValidCarID(carID)) {
                System.out.println("Invalid car ID format!");
                carID = null;
                continue;
            }
            
        } while (carID == null);
        brandList.listBrands();
        do {
            String brandID = Utils.getString("Enter brand ID: ");
            brand = brandList.searchBrandByID(brandID);
            if (brand == null) 
                System.out.println("Brand not found!");
        } while (brand == null);
        color = Utils.getString("Enter color: ");
        do {
            frameID = Utils.getString("Enter frame ID (Fxxxxx): ");
        } while (!Utils.isValidFrameID(frameID) || isDuplicateFrame(frameID));

        do {
            engineID = Utils.getString("Enter engine ID (Exxxxx): ");
        } while (!Utils.isValidEngineID(engineID) || isDuplicateEngine(engineID));

        this.add(new Car(carID, brand, color, frameID, engineID));
        System.out.println("Car added successfully!");
        return true;
    }

    @Override
    public boolean removeCarByID(String carID) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getCarID().equalsIgnoreCase(carID)) {
                boolean confirm = Utils.confirmYesNo("Are you sure to delete this car?");
                if (confirm) {
                    this.remove(i);
                    System.out.println("Car removed successfully!");
                    return true;
                } else {
                    System.out.println("Cancelled!");
                    return false;
                }
            }
        }
        System.out.println("Car not found!");
        return false;
    }

    @Override
    public boolean updateCarByID(String carID) {
        Car c = searchCarByID(carID);
        if (c == null) {
            System.out.println("Car not found!");
            return false;
        }

        String newColor = Utils.updateString("Enter new color", c.getColor());
        String newFrame = Utils.updateString("Enter new frame ID", c.getFrameID());
        String newEngine = Utils.updateString("Enter new engine ID", c.getEngineID());
        if (!newFrame.equalsIgnoreCase(c.getFrameID())) {
            if (!Utils.isValidFrameID(newFrame) || isDuplicateFrame(newFrame)) 
                return false;
        }
        if (!newEngine.equalsIgnoreCase(c.getEngineID())) {
            if (!Utils.isValidEngineID(newEngine) || isDuplicateEngine(newEngine)) 
                return false;
        }
        c.setColor(newColor);
        c.setFrameID(newFrame);
        c.setEngineID(newEngine);
        System.out.println("Car updated successfully!");
        return true;
    }

    @Override
    public List<Car> listCarsByColor(String color) {
        List<Car> list = new ArrayList<>();
        for (Car c : this) {
            if (c.getColor().equalsIgnoreCase(color)) 
                list.add(c);
        }

        if (list.isEmpty()) {
            System.out.println("No cars found with color: " + color);
        } else {
            System.out.println("-----------------------------------------------------------------------------------------------------------");
            System.out.printf("%-10s | %-35s | %-15s | %-10s | %-10s | %s%n", "CarID", "Brand Name", "Color", "FrameID", "EngineID", "Price");
            System.out.println("-----------------------------------------------------------------------------------------------------------");
            for (Car c : list) System.out.println(c);
            System.out.println("-----------------------------------------------------------------------------------------------------------");
        }
        return list;
    }

    

    private Car searchCarByID(String id) {
        for (Car c : this) {
            if (c.getCarID().equalsIgnoreCase(id)) 
                return c;
        }
        return null;
    }

    private boolean isDuplicateFrame(String frame) {
        for (Car c : this) {
            if (c.getFrameID().equalsIgnoreCase(frame)) {
                System.out.println("Duplicate frame ID!");
                return true;
            }
        }
        return false;
    }

    private boolean isDuplicateEngine(String engine) {
        for (Car c : this) {
            if (c.getEngineID().equalsIgnoreCase(engine)) {
                System.out.println("Duplicate engine ID!");
                return true;
            }
        }
        return false;
    }
    @Override
    public void loadData(String path) {
        List<String> lines = Utils.readLines(path);
        this.clear();
        for (String line : lines) {
            try {
                String[] parts = line.split(",");
                if (parts.length < 5) 
                    continue;
                String id = parts[0].trim();
                String brandID = parts[1].trim();
                String color = parts[2].trim();
                String frame = parts[3].trim();
                String engine = parts[4].trim();

                Brand b = brandList.searchBrandByID(brandID);
                if (b != null) {
                    this.add(new Car(id, b, color, frame, engine));
                }
            } catch (Exception e) {
                System.out.println("Error reading car line: " + line);
            }
        }
    }

    @Override
    public boolean saveToFile(String path) {
        List<String> lines = new ArrayList<>();
        for (Car c : this) {
            lines.add(String.format("%s, %s, %s, %s, %s", c.getCarID(), c.getBrand().getBrandID(), c.getColor(), c.getFrameID(), c.getEngineID()));
        }
        return Utils.writeLines(path, lines);
    }
}
