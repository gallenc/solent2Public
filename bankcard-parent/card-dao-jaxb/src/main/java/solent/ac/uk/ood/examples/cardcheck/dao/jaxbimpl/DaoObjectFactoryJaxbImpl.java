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
