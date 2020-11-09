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
