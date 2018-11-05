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

import solent.ac.uk.ood.examples.cardcheck.dao.DaoObjectFactory;
import solent.ac.uk.ood.examples.cardcheck.dao.jaxbimpl.ModelJaxbPersistor;
import solent.ac.uk.ood.examples.cardcheck.service.BankApi;
import solent.ac.uk.ood.examples.cardcheck.service.ServiceObjectFactory;
import solent.ac.uk.ood.examples.cardcheck.service.TransactionApi;

/**
 *
 * @author cgallen
 */
public class ServiceObjectFactoryImpl implements ServiceObjectFactory {

    private ModelJaxbPersistor modelJaxbPersistor; // used for transaction synchronisation
    private DaoObjectFactory daoObjectFactory;

    public ServiceObjectFactoryImpl(ModelJaxbPersistor modelJaxbPersistor, DaoObjectFactory daoObjectFactory) {
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
    public BankApi getBankApi() {
        return new BankApiImpl(modelJaxbPersistor, daoObjectFactory);
    }

    @Override
    public TransactionApi getTransactonApi() {
        return new TransactionApiImpl(modelJaxbPersistor, daoObjectFactory);
    }

}
