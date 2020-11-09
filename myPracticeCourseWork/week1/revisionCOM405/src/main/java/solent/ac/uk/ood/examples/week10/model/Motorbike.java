package solent.ac.uk.ood.examples.week10.model;

public class Motorbike extends Vehicle {

    double fee = 3.00;
    
    @Override
    public Double calculateFee() 
    {
        return fee;
    }
}
