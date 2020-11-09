package solent.ac.uk.ood.examples.week10.model;

public class Lorry extends Vehicle {

    double fee = 10.00;
    double avarageWeight = 8000;
    
    @Override
    public Double calculateFee() {
         if(this.weight > avarageWeight)
        {
            return fee + 5.00;
        }
        else
        {
            return fee;
        }
         
    }
}

// Answer

//   @Override
//    public Double calculateFee() {
//        //Lorries pay a fee of £10.00 although this becomes £15.00 if the lorry exceeds 8000kg.
//        return (getWeight() < THRESHOLD_WEIGHT) ? 10.00 : 15.00;
//    }