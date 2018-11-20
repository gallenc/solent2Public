package solent.ac.uk.ood.examples.cardvalidator.model;

import java.util.List;

public interface BankApi {

    public Account createAccount(String issuerIdentificationNumber, String name);

    public boolean deleteAccount(String issuerIdentificationNumber, String individualAccountIdentifier);

    public Account retrieveAccount(String issuerIdentificationNumber, String individualAccountIdentifier);

    public Account updateAccount(Account account);

    public List<Account> getAccountsForIssuer(String issuerIdentificationNumber);

    public List<String> getSupportedIssuerNames();

    public String getIssuerIdentifierNumberForName(String name);

    public String getNameForIssuerIdentificationNumber(String issuerIdentificationNumber);

    public CreditCard createNewCreditCard(Account account);
}
