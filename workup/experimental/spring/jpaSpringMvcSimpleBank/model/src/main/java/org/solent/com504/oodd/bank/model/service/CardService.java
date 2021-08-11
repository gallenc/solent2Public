package org.solent.com504.oodd.bank.model.service;

import org.solent.com504.oodd.bank.model.dto.BankAccount;
import org.solent.com504.oodd.bank.model.dto.CreditCard;
import solent.ac.uk.ood.examples.cardcheck.CardValidationResult;

public interface CardService {

    public CreditCard createCreditCard(BankAccount account);

    public CardValidationResult checkCreditcard(CreditCard card);
}
