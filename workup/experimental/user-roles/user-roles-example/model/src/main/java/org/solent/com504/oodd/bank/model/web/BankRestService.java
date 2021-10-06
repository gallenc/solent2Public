package org.solent.com504.oodd.bank.model.web;

import org.solent.com504.oodd.bank.model.dto.TransactionReplyMessage;
import org.solent.com504.oodd.bank.model.dto.TransactionRequestMessage;

public interface BankRestService {
    
    public TransactionReplyMessage transactionRequest(TransactionRequestMessage transactionRequestMessage);

}
