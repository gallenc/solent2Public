/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solent.ac.uk.ood.examples.cardcheck.dao.jaxbimpl;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import solent.ac.uk.ood.examples.cardcheck.dao.TransactionDAO;
import solent.ac.uk.ood.examples.cardcheck.model.Transaction;

/**
 *
 * @author gallenc
 */
public class TransactionDAOJaxbImpl implements TransactionDAO {

    private static final Logger LOG = LoggerFactory.getLogger(AccountDAOJaxbImpl.class);

    private final ModelJaxbPersistor modelJaxbPersistor;

    public TransactionDAOJaxbImpl(ModelJaxbPersistor modelJaxbPersistor) {
        super();
        if (modelJaxbPersistor == null) {
            throw new IllegalArgumentException("modelJaxbPersistor cannot be null");
        }
        this.modelJaxbPersistor = modelJaxbPersistor;
    }

    @Override
    public Transaction getById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Transaction> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Transaction add(Transaction transaction) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Transaction transaction) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Transaction update(Transaction transaction) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Transaction create() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
