package solent.ac.uk.ood.examples.cardcheck.service;

public interface ServiceObjectFactory {

    public BankApi getBankApi();

    public TransactionApi getTransactonApi();
}
