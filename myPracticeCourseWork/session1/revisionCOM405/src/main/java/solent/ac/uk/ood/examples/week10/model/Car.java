package solent.ac.uk.ood.examples.week10.model;

public class Car extends Vehicle {

    private double AVG_WEIGHT = 1590;
    private double EXCESS_PER100KG = 0.10;
    private double BASE_CAR_CHARGE = 5.00;

    @Override
    public Double calculateFee() {
        double fee = BASE_CAR_CHARGE;
        if (getWeight() > AVG_WEIGHT) {
            fee = fee + EXCESS_PER100KG * Math.round((getWeight() - AVG_WEIGHT) / 100);
        }
        return fee;

    }
}
