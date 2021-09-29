package solent.ac.uk.ood.examples.week10.model;

public class Lorry extends Vehicle {

    private double MAX_LORRY_WEIGHT = 8000;

    @Override
    public Double calculateFee() {
        return (getWeight() < MAX_LORRY_WEIGHT) ? 10.00 : 15.00 ;
    }
}
