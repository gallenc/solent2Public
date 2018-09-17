/* ***************************************************************************
 * Copyright 2018 Craig Gallen
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 ****************************************************************************/
package solent.ac.uk.ood.examples.cardcheck.service.impl;

import java.util.List;
import solent.ac.uk.ood.examples.cardcheck.dao.DaoObjectFactory;
import solent.ac.uk.ood.examples.cardcheck.dao.jaxbimpl.ModelJaxbPersistor;
import solent.ac.uk.ood.examples.cardcheck.model.Account;
import solent.ac.uk.ood.examples.cardcheck.model.Bank;
import solent.ac.uk.ood.examples.cardcheck.model.Transaction;
import solent.ac.uk.ood.examples.cardcheck.service.BankApi;

/**
 *
 * @author cgallen
 */
public class BankApiImpl implements BankApi {

    private DaoObjectFactory daoObjectFactory;
    private ModelJaxbPersistor modelJaxbPersistor;

    public BankApiImpl(ModelJaxbPersistor modelJaxbPersistor, DaoObjectFactory daoObjectFactory) {
        super();
        if (modelJaxbPersistor == null) {
            throw new IllegalArgumentException("modelJaxbPersistor cannot be null");
        }
        if (modelJaxbPersistor == null) {
            throw new IllegalArgumentException("daoObjectFactory cannot be null");
        }
        this.daoObjectFactory = daoObjectFactory;
        this.modelJaxbPersistor = modelJaxbPersistor;
    }

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
