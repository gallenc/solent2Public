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

package solent.ac.uk.ood.examples.cardcheck.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TransactionResult {
    
    private ResultCodes resultCode;

    private Transaction transaction;

    private String debugInformation;

    /**
     * @return the resultCode
     */
    public ResultCodes getResultCode() {
        return resultCode;
    }

    /**
     * @param resultCode the resultCode to set
     */
    public void setResultCode(ResultCodes resultCode) {
        this.resultCode = resultCode;
    }

    /**
     * @return the transaction
     */
    public Transaction getTransaction() {
        return transaction;
    }

    /**
     * @param transaction the transaction to set
     */
    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    /**
     * @return the debugInformation
     */
    public String getDebugInformation() {
        return debugInformation;
    }

    /**
     * @param debugInformation the debugInformation to set
     */
    public void setDebugInformation(String debugInformation) {
        this.debugInformation = debugInformation;
    }

}
