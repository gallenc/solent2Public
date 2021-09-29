package solent.ac.uk.ood.examples.week10.model;

public class Motorbike extends Vehicle {

    @Override
    public Double calculateFee() {
        //Motorbikes pay a fixed fee of £3.00
        return 3.00;

    }
}
