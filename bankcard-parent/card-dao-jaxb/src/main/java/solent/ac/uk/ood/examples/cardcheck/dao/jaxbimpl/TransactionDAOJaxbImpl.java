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
import solent.ac.uk.ood.examples.cardcheck.dao.TransactionDAO;
import solent.ac.uk.ood.examples.cardcheck.model.Transaction;

import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.ThreadLocalRandom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author gallenc
 */
public class TransactionDAOJaxbImpl implements TransactionDAO {

    private static final Logger LOG = LoggerFactory.getLogger(TransactionDAOJaxbImpl.class);

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
        synchronized (modelJaxbPersistor.Lock) {
            List<Transaction> allList = modelJaxbPersistor.getTransactionList();
            for (Transaction transaction : allList) {
                if (transaction.getId().equals(id)) {
                    Transaction copy = modelJaxbPersistor.copy(transaction);
                    return copy;
                }
            }
        }
        return null; // not found
    }

    @Override
    public List<Transaction> getAll() {
        List<Transaction> returnList = new ArrayList<Transaction>();
        synchronized (modelJaxbPersistor.Lock) {

            List<Transaction> allList = modelJaxbPersistor.getTransactionList();
            for (Transaction transaction : allList) {
                Transaction copy = modelJaxbPersistor.copy(transaction);
                returnList.add(copy);
            }
        }
        return returnList;
    }

    @Override
    public Transaction add(Transaction transaction) {
        synchronized (modelJaxbPersistor.Lock) {
            //TODO generate random id for transaction
            boolean duplicateId=true;
            int randomNum=0;
            while(duplicateId){
                randomNum = ThreadLocalRandom.current().nextInt(1,Integer.MAX_VALUE - 1);
                 if (getById(randomNum)==null) duplicateId=false;
            }
            transaction.setId(randomNum);
            modelJaxbPersistor.getTransactionList().add(transaction);
            modelJaxbPersistor.save();
            return transaction;
        }
     }


    @Override
    public void delete(Transaction transaction) {
        synchronized (modelJaxbPersistor.Lock) {
            deleteById(transaction.getId());
            modelJaxbPersistor.save();
        }
     }

    @Override
    public void deleteById(Integer id) {
        synchronized (modelJaxbPersistor.Lock) {
            List<Transaction> allList = modelJaxbPersistor.getTransactionList();
            ListIterator<Transaction> iter = allList.listIterator();
            while (iter.hasNext()) {
                if (iter.next().getId().equals(id)) {
                    iter.remove();
                }
            }
            modelJaxbPersistor.save();
        }
    }

    @Override
    public Transaction update(Transaction transaction) {
        synchronized (modelJaxbPersistor.Lock) {
            List<Transaction> allList = modelJaxbPersistor.getTransactionList();
            ListIterator<Transaction> iterator = allList.listIterator();
            while (iterator.hasNext()) {
                Transaction next = iterator.next();
                if (next.getId().equals(transaction.getId())) {
                    //Replace element
                    iterator.set(transaction);
                }
            }
            return transaction;

        }
    }

    @Override
    public Transaction create() {
        return new Transaction();
    }

}
