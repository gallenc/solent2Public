/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.oodd.bank.service;

import java.util.ArrayList;
import java.util.Collections;
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
import org.solent.com504.oodd.dao.impl.BankAccountRepository;
import org.solent.com504.oodd.dao.impl.BankTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author cgallen
 */
@Component
public class BankServiceImpl implements BankService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private BankTransactionRepository bankTransactionRepository;

    private List<String> supportedIssuerBanks
            = Collections.unmodifiableList(
                    new ArrayList(SupportedCardIssuerIdentificationNumbers.ISSUER_IDENTIFICATION_MAP.keySet()));

    @Override
    public BankAccount createBankAccount(User user, String supportedIssuerBank) {

        String iin = SupportedCardIssuerIdentificationNumbers.ISSUER_IDENTIFICATION_MAP.get(supportedIssuerBank);
        if (iin == null) {
            throw new IllegalArgumentException("unknown issuer " + supportedIssuerBank);
        }

        BankAccount account = new BankAccount();
        account.setSortcode(iin);
        account.setOwner(user);

        // nextInt is normally exclusive of the top value, so add 1 to make it inclusive
        int randomNum = ThreadLocalRandom.current().nextInt(0, 99999999);
        String individualAccountIdentifier = String.format("%08d", randomNum);; // 8 digits) 
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

        return account;
    }

    @Override
    public Double transferMoney(BankAccount fromAccount, BankAccount toAccount, Double amount) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Double transferMoney(CreditCard fromCard, CreditCard toCard, Double amount) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> getSupportedIssuerBanks() {
        return supportedIssuerBanks;
    }

    @Override
    @Transactional
    public boolean activateAccount(BankAccount account, boolean active) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // methods reflecting the DAOs
    @Override
    public List<BankAccount> findAllBankAccounts() {
        return bankAccountRepository.findAll();
    }

    @Override
    public Optional<BankAccount> findByBankAccountById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <S extends BankAccount> S saveBankAccount(S s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<BankTransaction> findAllBankTransactions() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Optional<BankTransaction> findByBankTransactionById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<BankTransaction> findByBankTransactionsByAccount(BankAccount account) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <S extends BankTransaction> S saveBankTransaction(S s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
