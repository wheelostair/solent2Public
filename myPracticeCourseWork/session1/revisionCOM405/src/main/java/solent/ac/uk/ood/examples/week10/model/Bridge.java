package solent.ac.uk.ood.examples.week10.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
public class Bridge {
    
    private static final int MAX_VEHICLES = 20;
    private static final int MAX_WEIGHT = 30000;
    
    private Map<String,Vehicle> vehicles = new LinkedHashMap<String,Vehicle>();

    public Double calcTotalWeight() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean requestAddVechicle(Vehicle vehicle) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean removeVehicle(String registration) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int numberOfVehicles() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Double calcTotalCost() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
