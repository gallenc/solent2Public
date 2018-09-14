package solent.ac.uk.ood.examples.cardcheck.service;

import java.util.List;
import solent.ac.uk.ood.examples.cardcheck.model.Account;
import solent.ac.uk.ood.examples.cardcheck.model.Bank;
import solent.ac.uk.ood.examples.cardcheck.model.Transaction;

public interface BankApi {

    public Transaction getTransaction(Integer transactionId);

    public List<Transaction> getTransactions(Integer accountid);

    public Account getAccount(Integer accountId);

    public List<Account> getAccounts();

    public Bank getBank();

    public Account createAccount(Account accountDetails);
}
