package solent.ac.uk.ood.examples.cardcheck.dao.jaxbimpl;

import solent.ac.uk.ood.examples.cardcheck.dao.AccountDAO;
import solent.ac.uk.ood.examples.cardcheck.dao.BankDAO;
import solent.ac.uk.ood.examples.cardcheck.dao.DaoObjectFactory;
import solent.ac.uk.ood.examples.cardcheck.dao.TransactionDAO;

public class DaoObjectFactoryJaxbImpl implements DaoObjectFactory {

    @Override
    public BankDAO getBankDAO() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AccountDAO getAccountDAO() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TransactionDAO getTransactionDAO() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

 
}
