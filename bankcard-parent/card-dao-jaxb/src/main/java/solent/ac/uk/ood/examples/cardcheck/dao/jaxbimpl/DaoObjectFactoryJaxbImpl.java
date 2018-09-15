package solent.ac.uk.ood.examples.cardcheck.dao.jaxbimpl;

import solent.ac.uk.ood.examples.cardcheck.dao.AccountDAO;
import solent.ac.uk.ood.examples.cardcheck.dao.BankDAO;
import solent.ac.uk.ood.examples.cardcheck.dao.DaoObjectFactory;
import solent.ac.uk.ood.examples.cardcheck.dao.TransactionDAO;

public class DaoObjectFactoryJaxbImpl implements DaoObjectFactory {

    private final ModelJaxbPersistor modelJaxbPersistor;
    
    private final BankDAO bankDAO;
    
    private final TransactionDAO transactionDAO;
    
    private final AccountDAO accountDAO;

    public DaoObjectFactoryJaxbImpl(ModelJaxbPersistor modelJaxbPersistor) {
        super();
        if (modelJaxbPersistor == null) {
            throw new IllegalArgumentException("modelJaxbPersistor cannot be null");
        }
        this.modelJaxbPersistor = modelJaxbPersistor;
        
        bankDAO = new BankDAOJaxbImpl(modelJaxbPersistor);
        transactionDAO = new TransactionDAOJaxbImpl(modelJaxbPersistor);
        accountDAO = new AccountDAOJaxbImpl(modelJaxbPersistor);
    }

    @Override
    public BankDAO getBankDAO() {
        return bankDAO;
    }

    @Override
    public AccountDAO getAccountDAO() {
        return accountDAO;
    }

    @Override
    public TransactionDAO getTransactionDAO() {
        return transactionDAO;
    }

}
