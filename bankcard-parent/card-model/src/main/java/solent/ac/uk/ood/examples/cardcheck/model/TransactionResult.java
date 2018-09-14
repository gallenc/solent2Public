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
