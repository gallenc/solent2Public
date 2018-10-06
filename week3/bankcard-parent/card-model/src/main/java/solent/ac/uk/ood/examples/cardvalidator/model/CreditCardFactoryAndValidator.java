package solent.ac.uk.ood.examples.cardvalidator.model;

public interface CreditCardFactoryAndValidator {

    public CreditCard createCreditCard(String individualAccountIdentifier, String name, String duration, String issueNo);

    public CvvAlgorythimStrategy setCvvAlgorythim(CvvAlgorythimStrategy cvvAlgorythimStrategy);

    public CreditCard cvvIsValid(CreditCard card);

    public CardOrganisation getCardOrganisation(CreditCard card);

    public CreditCard cardNumberLunnIsValid(CreditCard card);

    public String setIssuerIdentifiictionNumber(String iin);
}
