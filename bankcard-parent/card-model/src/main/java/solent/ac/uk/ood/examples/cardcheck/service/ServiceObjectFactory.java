package solent.ac.uk.ood.examples.cardcheck.service;

import solent.ac.uk.ood.examples.cardcheck.dao.AccountDAO;
import solent.ac.uk.ood.examples.cardcheck.dao.BankDAO;
import solent.ac.uk.ood.examples.cardcheck.dao.TransactionDAO;

public interface ServiceObjectFactory {

    public BankApi getBankApi();

    public TransactionApi getTransactonApi();

    public BankDAO getBankDAO();

    public AccountDAO getAccountDAO();

    public TransactionDAO getTransactionDAO();
}
