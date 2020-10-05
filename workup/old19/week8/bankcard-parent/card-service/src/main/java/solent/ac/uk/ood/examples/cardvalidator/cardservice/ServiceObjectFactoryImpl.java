/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solent.ac.uk.ood.examples.cardvalidator.cardservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import solent.ac.uk.ood.examples.cardvalidator.model.AccountDAO;
import solent.ac.uk.ood.examples.cardvalidator.model.BankApi;
import solent.ac.uk.ood.examples.cardvalidator.model.CardFactoryDAO;
import solent.ac.uk.ood.examples.cardvalidator.model.ServiceObjectFactory;
import solent.ac.uk.ood.examples.cardvalidator.model.TransactionApi;

/**
 *
 * @author cgallen
 */
public class ServiceObjectFactoryImpl implements ServiceObjectFactory {
    private static final Logger LOG = LoggerFactory.getLogger(ServiceObjectFactoryImpl.class);

    private static CardFactoryDAO cardFactoryDao;

    private static AccountDAO accountDAO;

    private TransactionApi transactionApi;

    private BankApi bankApi;

    public static void setCardFactoryDao(CardFactoryDAO cardFactoryDao) {
        ServiceObjectFactoryImpl.cardFactoryDao = cardFactoryDao;
    }

    public static void setAccountDAO(AccountDAO accountDAO) {
        ServiceObjectFactoryImpl.accountDAO = accountDAO;
    }

    @Override
    public TransactionApi getTransactionApi() {

        if (transactionApi == null) {
            synchronized (this) {
                if (transactionApi == null) {
                    transactionApi = new TransactionApiImpl(cardFactoryDao, accountDAO);
                }
            }
        }
        return transactionApi;
    }

    @Override
    public BankApi getBankApi() {
        if (bankApi == null) {
            synchronized (this) {
                if (bankApi == null) {
                    bankApi = new BankApiImpl(cardFactoryDao, accountDAO);
                }
            }
        }
        return bankApi;
    }

}
