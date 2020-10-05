package solent.ac.uk.ood.examples.week10.model;

public class Car extends Vehicle {

    private double AVERAGE_WEIGHT = 1690; // KG
    private double EXCESS_PER100KG = 0.10; //10p
    private double BASE_CAR_CHARGE = 5.00; // £5
    
    // Cars pay a fee of £5.00. The average car is expected to weigh
    // approximately 1590kg. 
    // With this in mind, the bridge 
    // system will add an additional 10p for every additional 
    // 100kg.

    @Override
    public Double calculateFee() {
        double fee = BASE_CAR_CHARGE;
        if(getWeight()  > AVERAGE_WEIGHT ){
           fee = fee + EXCESS_PER100KG *  Math.round( (getWeight() - AVERAGE_WEIGHT) / 100 );
        }
        return fee;
    }
}
