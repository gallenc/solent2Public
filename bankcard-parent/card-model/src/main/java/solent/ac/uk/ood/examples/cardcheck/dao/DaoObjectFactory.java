package solent.ac.uk.ood.examples.cardcheck.dao;

public interface DaoObjectFactory {

    public BankDAO getBankDAO();

    public AccountDAO getAccountDAO();

    public TransactionDAO getTransactionDAO();
}
