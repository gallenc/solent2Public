package solent.ac.uk.ood.examples.cardvalidator.model;

import java.util.List;

public interface CardFactoryDAO {

    public CreditCardFactoryAndValidator getCreditCardFactoryAndValidator(String issuerIdentifierNumber);
    
    List<String> getSupportedIssuerNames();
    
    String getIssuerIdentifierNumberForName(String name);
    
    public String getNameForIssuerIdentificationNumber(String issuerIdentificationNumber);
    
}
