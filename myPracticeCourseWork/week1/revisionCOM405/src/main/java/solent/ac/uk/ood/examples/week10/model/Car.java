package solent.ac.uk.ood.examples.week10.model;

public class Car extends Vehicle{
        
        double avarageWeight = 1590;
        double fee = 5.00;
        
        
    // Cars pay a fee of £5.00. The average car is expected to weigh
    // approximately 1590kg. 
    // With this in mind, the bridge 
    // system will add an additional 10p for every additional 
    // 100kg.

    @Override
    public Double calculateFee() {
        if(this.weight > avarageWeight)
        {
            for(; avarageWeight < this.weight; avarageWeight += 100)
            {
                fee += 0.10;
            }
        } else 
        {
            return fee;
        }
        
        return fee;
    }
}

//|Answer

// @Override
//    public Double calculateFee() {
//        double fee = BASE_CAR_CHARGE;
//        if(getWeight()  > AVERAGE_WEIGHT ){
//           fee = fee + EXCESS_PER100KG *  Math.round( (getWeight() - AVERAGE_WEIGHT) / 100 );
//        }
//        return fee;
//    }