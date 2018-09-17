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

import java.util.ArrayList;
import solent.ac.uk.ood.examples.cardcheck.dao.AccountDAO;
import solent.ac.uk.ood.examples.cardcheck.model.Account;

import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.ThreadLocalRandom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author gallenc
 */
public class AccountDAOJaxbImpl implements AccountDAO {

    private static final Logger LOG = LoggerFactory.getLogger(AccountDAOJaxbImpl.class);

    private final ModelJaxbPersistor modelJaxbPersistor;

    public AccountDAOJaxbImpl(ModelJaxbPersistor modelJaxbPersistor) {
        super();
        if (modelJaxbPersistor == null) {
            throw new IllegalArgumentException("modelJaxbPersistor cannot be null");
        }
        this.modelJaxbPersistor = modelJaxbPersistor;
    }

    @Override
    public Account getById(Integer id) {
        synchronized (modelJaxbPersistor.Lock) {
            List<Account> allList = modelJaxbPersistor.getAccountList();
            for (Account account : allList) {
                if (account.getId().equals(id)) {
                    Account copy = modelJaxbPersistor.copy(account);
                    return copy;
                }
            }
        }
        return null; // not found
    }

    @Override
    public List<Account> getAll() {
        List<Account> returnList = new ArrayList<Account>();
        synchronized (modelJaxbPersistor.Lock) {

            List<Account> allList = modelJaxbPersistor.getAccountList();
            for (Account account : allList) {
                Account copy = modelJaxbPersistor.copy(account);
                returnList.add(copy);
            }
        }
        return returnList;
    }

    @Override
    public Account add(Account account) {
        synchronized (modelJaxbPersistor.Lock) {
            //TODO generate random id for account
            boolean duplicateId = true;
            int randomNum = 0;
            while (duplicateId) {
                randomNum = ThreadLocalRandom.current().nextInt(1, Integer.MAX_VALUE - 1);
                if (getById(randomNum) == null) {
                    duplicateId = false;
                }
            }
            account.setId(randomNum);
            modelJaxbPersistor.getAccountList().add(account);
            modelJaxbPersistor.save();
            return account;
        }
    }

    @Override
    public void delete(Account account) {
        synchronized (modelJaxbPersistor.Lock) {
            deleteById(account.getId());
            modelJaxbPersistor.save();
        }
    }

    @Override
    public void deleteById(Integer id) {
        synchronized (modelJaxbPersistor.Lock) {
            List<Account> allList = modelJaxbPersistor.getAccountList();
            ListIterator<Account> iter = allList.listIterator();
            while (iter.hasNext()) {
                if (iter.next().getId().equals(id)) {
                    iter.remove();
                }
            }
            modelJaxbPersistor.save();
        }
    }

    @Override
    public Account update(Account account) {
        synchronized (modelJaxbPersistor.Lock) {
            List<Account> allList = modelJaxbPersistor.getAccountList();
            ListIterator<Account> iterator = allList.listIterator();
            while (iterator.hasNext()) {
                Account next = iterator.next();
                if (next.getId().equals(account.getId())) {
                    //Replace element
                    iterator.set(account);
                }
            }
            return account;

        }
    }

    @Override
    public Account create() {
        return new Account();
    }

}
