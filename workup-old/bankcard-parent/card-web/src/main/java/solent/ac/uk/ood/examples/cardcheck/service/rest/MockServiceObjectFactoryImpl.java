/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solent.ac.uk.ood.examples.cardcheck.service.rest;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import solent.ac.uk.ood.examples.cardcheck.model.Account;
import solent.ac.uk.ood.examples.cardcheck.model.Bank;
import solent.ac.uk.ood.examples.cardcheck.model.ResultCodes;
import solent.ac.uk.ood.examples.cardcheck.model.Transaction;
import solent.ac.uk.ood.examples.cardcheck.model.TransactionResult;
import solent.ac.uk.ood.examples.cardcheck.service.BankApi;
import solent.ac.uk.ood.examples.cardcheck.service.ServiceObjectFactory;
import solent.ac.uk.ood.examples.cardcheck.service.TransactionApi;

/**
 *
 * @author cgallen
 */
public class MockServiceObjectFactoryImpl implements ServiceObjectFactory {

    BankApi bankApi = new DummyBankApi();
    TransactionApi transactionApi = new MockTransactionApi();

    @Override
    public BankApi getBankApi() {
        return bankApi;
    }

    @Override
    public TransactionApi getTransactonApi() {
        return transactionApi;
    }

    private class MockTransactionApi implements TransactionApi {

        @Override
        public TransactionResult requestPreAuthorisation(Transaction requestTransaction) {
            TransactionResult result = new TransactionResult();
            result.setResultCode(ResultCodes.PRE_AUTHORISED);
            
            int randomNum = ThreadLocalRandom.current().nextInt(1, Integer.MAX_VALUE - 1);
            requestTransaction.setTransactionId(randomNum);
            result.setTransaction(requestTransaction);
            return result;
        }

        @Override
        public TransactionResult requestTransaction(Transaction requestTransaction) {
            TransactionResult result = new TransactionResult();
            result.setResultCode(ResultCodes.DECLINED);
            int randomNum = ThreadLocalRandom.current().nextInt(1, Integer.MAX_VALUE - 1);
            requestTransaction.setTransactionId(randomNum);
            result.setTransaction(requestTransaction);
            return result;
        }

    }

    private class DummyBankApi implements BankApi {

        @Override
        public Transaction getTransaction(Integer transactionId) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public List<Transaction> getTransactions(Integer accountid) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Account getAccount(Integer accountId) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public List<Account> getAccounts() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Bank getBank() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Account createAccount(Account accountDetails) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }

}
