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
import solent.ac.uk.ood.examples.cardcheck.dao.BankDAO;
import solent.ac.uk.ood.examples.cardcheck.model.Bank;

import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.ThreadLocalRandom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author gallenc
 */
public class BankDAOJaxbImpl implements BankDAO {

    private static final Logger LOG = LoggerFactory.getLogger(BankDAOJaxbImpl.class);

    private final ModelJaxbPersistor modelJaxbPersistor;

    public BankDAOJaxbImpl(ModelJaxbPersistor modelJaxbPersistor) {
        super();
        if (modelJaxbPersistor == null) {
            throw new IllegalArgumentException("modelJaxbPersistor cannot be null");
        }
        this.modelJaxbPersistor = modelJaxbPersistor;
    }

    @Override
    public Bank getById(Integer id) {
        synchronized (modelJaxbPersistor.Lock) {
            List<Bank> allList = modelJaxbPersistor.getBankList();
            for (Bank bank : allList) {
                if (bank.getId().equals(id)) {
                    Bank copy = modelJaxbPersistor.copy(bank);
                    return copy;
                }
            }
        }
        return null; // not found
    }

    @Override
    public List<Bank> getAll() {
        List<Bank> returnList = new ArrayList<Bank>();
        synchronized (modelJaxbPersistor.Lock) {

            List<Bank> allList = modelJaxbPersistor.getBankList();
            for (Bank bank : allList) {
                Bank copy = modelJaxbPersistor.copy(bank);
                returnList.add(copy);
            }
        }
        return returnList;
    }

    @Override
    public Bank add(Bank bank) {
        synchronized (modelJaxbPersistor.Lock) {
            //TODO generate random id for bank
            boolean duplicateId=true;
            int randomNum=0;
            while(duplicateId){
                randomNum = ThreadLocalRandom.current().nextInt(1,Integer.MAX_VALUE - 1);
                 if (getById(randomNum)==null) duplicateId=false;
            }
            bank.setId(randomNum);
            modelJaxbPersistor.getBankList().add(bank);
            modelJaxbPersistor.save();
            return bank;
        }
     }


    @Override
    public void delete(Bank bank) {
        synchronized (modelJaxbPersistor.Lock) {
            deleteById(bank.getId());
            modelJaxbPersistor.save();
        }
     }

    @Override
    public void deleteById(Integer id) {
        synchronized (modelJaxbPersistor.Lock) {
            List<Bank> allList = modelJaxbPersistor.getBankList();
            ListIterator<Bank> iter = allList.listIterator();
            while (iter.hasNext()) {
                if (iter.next().getId().equals(id)) {
                    iter.remove();
                }
            }
            modelJaxbPersistor.save();
        }
    }

    @Override
    public Bank update(Bank bank) {
        synchronized (modelJaxbPersistor.Lock) {
            List<Bank> allList = modelJaxbPersistor.getBankList();
            ListIterator<Bank> iterator = allList.listIterator();
            while (iterator.hasNext()) {
                Bank next = iterator.next();
                if (next.getId().equals(bank.getId())) {
                    //Replace element
                    iterator.set(bank);
                }
            }
            return bank;

        }
    }

    @Override
    public Bank create() {
        return new Bank();
    }

}
