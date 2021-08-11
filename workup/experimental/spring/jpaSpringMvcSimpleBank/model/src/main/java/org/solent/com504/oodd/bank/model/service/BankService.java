package org.solent.com504.oodd.bank.model.service;

import java.util.List;
import java.util.Optional;
import org.solent.com504.oodd.bank.model.dao.BankAccountDAO;
import org.solent.com504.oodd.bank.model.dto.BankAccount;
import org.solent.com504.oodd.bank.model.dto.BankTransaction;
import org.solent.com504.oodd.bank.model.dto.CreditCard;
import org.solent.com504.oodd.bank.model.dto.User;

public interface BankService {

    // methods reflecting service
    public BankAccount createBankAccount(User user, String supportedIssuerBank);

    public Double transferMoney(BankAccount fromAccount, BankAccount toAccount, Double amount);

    public Double transferMoney(CreditCard fromCard, CreditCard toCard, Double amount);

    public List<String> getSupportedIssuerBanks();

    public boolean activateAccount(BankAccount account, boolean active);

   // methods reflecting bankaccountDAO
    public List<BankAccount> findAllBankAccounts();

    public Optional<BankAccount> findByBankAccountById(Long id);
    
    public <S extends BankAccount> S saveBankAccount(S s);
    
    // methods reflecting banktransactionDAO

    public List<BankTransaction> findAllBankTransactions();

    public Optional<BankTransaction> findByBankTransactionById(Long id);
    
    public List<BankTransaction> findByBankTransactionsByAccount(BankAccount account);
    
    public <S extends BankTransaction> S saveBankTransaction(S s);

}
