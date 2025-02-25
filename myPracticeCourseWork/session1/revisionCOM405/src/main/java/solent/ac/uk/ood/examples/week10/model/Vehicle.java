package solent.ac.uk.ood.examples.week10.model;

public abstract class Vehicle {

    private Double weight;

    private String registration;

    public abstract Double calculateFee();

    public Double getWeight() {
        return weight;
    }

    public String getReg() {
        return registration;
    }

}
