package solent.ac.uk.ood.examples.cardvalidator.model;

public interface CvvAlgorythimStrategy {

    public CreditCard addCvv(CreditCard card);

    public boolean checkCvv(CreditCard card);
}
