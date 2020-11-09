package solent.ac.uk.ood.examples.week10.model;

public class Car extends Vehicle{
        
        double avarageWeight = 1590;
        double fee = 5.00;

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
