package solent.ac.uk.ood.examples.cardcheck.service;

import solent.ac.uk.ood.examples.cardcheck.model.Transaction;

public interface TransactionApi {

    public Transaction requestPreAuthorisation(Transaction requestTransaction);

    public Transaction requestTransaction(Transaction transactionRequest);
}
