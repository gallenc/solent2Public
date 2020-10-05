package solent.ac.uk.ood.examples.cardvalidator.model;

public interface CreditCardFactoryAndValidator {

    public CreditCard createCreditCard(String individualAccountIdentifier, String name, String endDate, String issueNo);

    public void setCvvAlgorythim(CvvAlgorythimStrategy cvvAlgorythimStrategy);

    public boolean cvvIsValid(CreditCard card);

    public CardOrganisation getCardOrganisation(CreditCard card);

    public boolean cardNumberLunnIsValid(CreditCard card);

    public void setIssuerIdentificationNumber(String iin);
    
}
