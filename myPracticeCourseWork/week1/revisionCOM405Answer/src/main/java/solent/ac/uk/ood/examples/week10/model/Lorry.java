package solent.ac.uk.ood.examples.week10.model;

public class Lorry extends Vehicle {

    private double THRESHOLD_WEIGHT = 8000; // KG

    @Override
    public Double calculateFee() {
        //Lorries pay a fee of £10.00 although this becomes £15.00 if the lorry exceeds 8000kg.
        return (getWeight() < THRESHOLD_WEIGHT) ? 10.00 : 15.00;
    }
}
