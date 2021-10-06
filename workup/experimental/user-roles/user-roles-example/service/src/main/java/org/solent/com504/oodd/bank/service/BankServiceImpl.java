/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.oodd.bank.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.solent.com504.oodd.bank.model.dao.BankAccountDAO;
import org.solent.com504.oodd.bank.model.dto.BankAccount;
import org.solent.com504.oodd.bank.model.dto.BankTransaction;
import org.solent.com504.oodd.bank.model.dto.CreditCard;
import org.solent.com504.oodd.bank.model.dto.User;
import org.solent.com504.oodd.bank.model.service.BankService;
import org.springframework.stereotype.Component;
import solent.ac.uk.ood.examples.cardcheck.CalculateLunnDigit;
import solent.ac.uk.ood.examples.cardvalidator.impl.SupportedCardIssuerIdentificationNumbers;
import java.util.concurrent.ThreadLocalRandom;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.solent.com504.oodd.bank.model.dto.BankTransactionStatus;
import org.solent.com504.oodd.dao.impl.BankAccountRepository;
import org.solent.com504.oodd.dao.impl.BankTransactionRepository;
import org.solent.com504.oodd.password.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import solent.ac.uk.ood.examples.cardcheck.CardValidationResult;
import solent.ac.uk.ood.examples.cardcheck.RegexCardValidator;

/**
 *
 * @author cgallen
 */
@Component
public class BankServiceImpl implements BankService {

    private static final Logger LOG = LogManager.getLogger(BankServiceImpl.class);

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private BankTransactionRepository bankTransactionRepository;

    private List<String> supportedIssuerBanks
            = Collections.unmodifiableList(
                    new ArrayList(SupportedCardIssuerIdentificationNumbers.ISSUER_IDENTIFICATION_MAP.keySet()));

    @Override
    @Transactional
    public void deleteAllData() {
        bankTransactionRepository.deleteAll();
        bankAccountRepository.deleteAll();
    }

    @Override
    public BankAccount createBankAccount(User user, String supportedIssuerBank) {

        // nextInt is normally exclusive of the top value, so add 1 to make it inclusive
        // reserving 10 numbers for test
        int randomNum = ThreadLocalRandom.current().nextInt(10, 99999999);
        String individualAccountIdentifier = String.format("%09d", randomNum);; // 8 digits) 

        return createBankAccount(user, supportedIssuerBank, individualAccountIdentifier);
    }

    @Override
    public BankAccount createBankAccount(User user, String supportedIssuerBank, String individualAccountIdentifier) {

        String iin = SupportedCardIssuerIdentificationNumbers.ISSUER_IDENTIFICATION_MAP.get(supportedIssuerBank);
        if (iin == null) {
            throw new IllegalArgumentException("unknown issuer " + supportedIssuerBank);
        }

        BankAccount account = new BankAccount();
        account.setSortcode(iin);
        account.setOwner(user);
        account.setAccountNo(individualAccountIdentifier);

        CreditCard card = new CreditCard();

        String pan = iin + individualAccountIdentifier;

        String check = CalculateLunnDigit.calculateCheckDigit(pan);

        String ccNumber = pan + check;

        card.setCardnumber(ccNumber);
        card.setName(user.getFirstName() + " " + user.getSecondName());
        card.setCvv("123"); // use algorythm here
        card.setEndDate("11/21");

        account.setCreditcard(card);

        account = bankAccountRepository.saveAndFlush(account);
        LOG.debug("created account " + account);

        return account;
    }

    @Override
    @Transactional
    public BankTransaction transferMoney(BankAccount fromAccount, BankAccount toAccount, Double amount) {
        BankTransaction bankTransaction = new BankTransaction();
        bankTransaction.setAmount(amount);
        BankAccount fromAcct = bankAccountRepository.findBankAccountByNumber(fromAccount.getSortcode(), fromAccount.getAccountNo());
        BankAccount toAcct = bankAccountRepository.findBankAccountByNumber(toAccount.getSortcode(), toAccount.getAccountNo());
        if (fromAcct == null) {
            bankTransaction.setStatus(BankTransactionStatus.FAIL);
            bankTransaction.setMessage("unknown FROM account " + fromAccount.getSortcode() + " " + fromAccount.getAccountNo());
            return bankTransaction;
        } else if (toAcct == null) {
            bankTransaction.setStatus(BankTransactionStatus.FAIL);
            bankTransaction.setMessage("unknown TO account " + toAccount.getSortcode() + " " + toAccount.getAccountNo());
            return bankTransaction;
        } else if (fromAcct.getBalance() - amount < 0) {
            bankTransaction.setFromAccount(fromAcct);
            bankTransaction.setFromAccount(toAcct);
            bankTransaction.setStatus(BankTransactionStatus.FAIL);
            bankTransaction.setMessage("insufficient balance in account " + fromAccount.getSortcode() + " " + fromAccount.getAccountNo());
            return bankTransaction;
        } else {
            double newFromBalance = fromAcct.getBalance() - amount;
            fromAcct.setBalance(newFromBalance);
            bankAccountRepository.save(fromAcct);

            double newToBalance = toAcct.getBalance() + amount;
            toAcct.setBalance(newToBalance);
            bankAccountRepository.save(toAcct);

            bankTransaction.setFromAccount(fromAcct);
            bankTransaction.setToAccount(toAcct);
            bankTransaction.setTransactionDate(new Date());
            bankTransaction.setStatus(BankTransactionStatus.SUCCESS);
            bankTransactionRepository.saveAndFlush(bankTransaction);
            return bankTransaction;
        }

    }

    @Override
    public BankTransaction transferMoneyAuth(CreditCard fromCard, CreditCard toCard, Double amount, String toUsername, String toPassword) throws SecurityException {
        // check password and user are allowed to make transaction - note this is TO account as they own cash machine
        BankAccount toAccount = bankAccountRepository.findBankAccountByCreditCardNo(toCard.getCardnumber());
        if (!toUsername.equals(toAccount.getOwner().getUsername())) {
            throw new SecurityException("Unauthorised user " + toUsername);
        }

        if (!PasswordUtils.checkPassword(toPassword, toAccount.getOwner().getHashedPassword())) {
            throw new SecurityException("Unauthorised password for user " + toUsername);
        }
        return transferMoney(fromCard, toCard, amount);
    }

    @Override
    public BankTransaction transferMoney(CreditCard fromCard, CreditCard toCard, Double amount) {
        //check creditccdlunn etc first

        //some problem with card validation check for amex
        CardValidationResult cardValidationResult = RegexCardValidator.isValid(fromCard.getCardnumber());
        if (!cardValidationResult.isValid()) {
            BankTransaction bankTransaction = new BankTransaction();
            bankTransaction.setStatus(BankTransactionStatus.FAIL);
            bankTransaction.setMessage("invalid from card number :" + fromCard.getCardnumber() + " " + cardValidationResult.getMessage());
            return bankTransaction;
        }

        cardValidationResult = RegexCardValidator.isValid(toCard.getCardnumber());
        if (!cardValidationResult.isValid()) {
            BankTransaction bankTransaction = new BankTransaction();
            bankTransaction.setStatus(BankTransactionStatus.FAIL);
            bankTransaction.setMessage("invalid to card number :" + fromCard.getCardnumber());
            return bankTransaction;
        }

        // now do transfer
        BankAccount fromAccount = bankAccountRepository.findBankAccountByCreditCardNo(fromCard.getCardnumber());
        BankAccount toAccount = bankAccountRepository.findBankAccountByCreditCardNo(toCard.getCardnumber());
        return transferMoney(fromAccount, toAccount, amount);
    }

    @Override
    public List<String> getSupportedIssuerBanks() {
        return supportedIssuerBanks;
    }

    @Override
    @Transactional
    public boolean activateAccount(BankAccount account, boolean active) {
        BankAccount fromAcct = bankAccountRepository.findBankAccountByNumber(account.getSortcode(), account.getAccountNo());
        fromAcct.setActive(active);
        bankAccountRepository.saveAndFlush(fromAcct);
        return active;
    }

    // methods reflecting the DAOs
    @Override
    public BankAccount findBankAccountByNumber(String sortcode, String accountNo) {
        return bankAccountRepository.findBankAccountByNumber(sortcode, accountNo);
    }

    @Override
    public BankAccount findBankAccountByCreditCardNo(String cardnumber) {
        return bankAccountRepository.findBankAccountByCreditCardNo(cardnumber);
    }

    @Override
    public List<BankAccount> findAllBankAccounts() {
        return bankAccountRepository.findAll();
    }

    @Override
    public Optional<BankAccount> findByBankAccountById(Long id) {
        return bankAccountRepository.findById(id);
    }

    @Override
    public <S extends BankAccount> S saveBankAccount(S s) {
        return bankAccountRepository.saveAndFlush(s);
    }

    @Override
    public List<BankTransaction> findAllBankTransactions() {
        return bankTransactionRepository.findAll();
    }

    @Override
    public Optional<BankTransaction> findByBankTransactionById(Long id) {
        return bankTransactionRepository.findById(id);

    }

    @Override
    public <S extends BankTransaction> S saveBankTransaction(S s) {
        return bankTransactionRepository.save(s);
    }

    @Override
    public List<BankTransaction> findBankTransactionsFromCreditCardNumber(String cardnumber) {
        return bankTransactionRepository.findBankTransactionsFromCreditCardNumber(cardnumber);
    }

}
