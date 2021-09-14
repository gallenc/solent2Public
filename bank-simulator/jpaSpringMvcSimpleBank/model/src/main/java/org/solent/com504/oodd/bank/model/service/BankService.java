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
    
    BankAccount createBankAccount(User user, String supportedIssuerBank, String individualAccountIdentifier);

    public BankTransaction  transferMoney(BankAccount fromAccount, BankAccount toAccount, Double amount);

    public BankTransaction  transferMoney(CreditCard fromCard, CreditCard toCard, Double amount);
    
    public BankTransaction transferMoneyAuth(CreditCard fromCard, CreditCard toCard, Double amount, String toUsername, String toPassword);

    public List<String> getSupportedIssuerBanks();

    public boolean activateAccount(BankAccount account, boolean active);
    
    public void deleteAllData();

   // methods reflecting bankaccountDAO
    
    public BankAccount findBankAccountByNumber(String sortcode, String accountNo);

    public BankAccount findBankAccountByCreditCardNo(String cardnumber);
    
    public List<BankAccount> findAllBankAccounts();

    public Optional<BankAccount> findByBankAccountById(Long id);
    
    public <S extends BankAccount> S saveBankAccount(S s);
    
    // methods reflecting banktransactionDAO
    
    public List<BankTransaction> findBankTransactionsFromCreditCardNumber(String cardnumber);

    public List<BankTransaction> findAllBankTransactions();

    public Optional<BankTransaction> findByBankTransactionById(Long id);
    
    public <S extends BankTransaction> S saveBankTransaction(S s);

}
