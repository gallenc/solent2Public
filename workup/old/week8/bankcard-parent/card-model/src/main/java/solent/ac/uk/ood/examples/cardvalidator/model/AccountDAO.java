package solent.ac.uk.ood.examples.cardvalidator.model;

import java.util.List;

public interface AccountDAO {

    public Account createAccount(String issuerIdentificationNumber, String name);

    public boolean deleteAccount(String issuerIdentificationNumber, String individualAccountIdentifier);

    public Account retrieveAccount(String issuerIdentificationNumber, String individualAccountIdentifier);

    public Account updateAccount(Account account);

    public List<Account> getAccountsForIssuer(String issuerIdentificationNumber);
    
    public List<Account> getAllAccounts();
}
