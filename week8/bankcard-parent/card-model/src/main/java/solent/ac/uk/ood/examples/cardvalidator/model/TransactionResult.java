package solent.ac.uk.ood.examples.cardvalidator.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TransactionResult {

    private ResultCode resultCode;

    private Transaction transactionRequest;

    private String debugInfo;

    public ResultCode getResultCode() {
        return resultCode;
    }

    public void setResultCode(ResultCode resultCode) {
        this.resultCode = resultCode;
    }

    public Transaction getTransactionRequest() {
        return transactionRequest;
    }

    public void setTransactionRequest(Transaction transactionRequest) {
        this.transactionRequest = transactionRequest;
    }

    public String getDebugInfo() {
        return debugInfo;
    }

    public void setDebugInfo(String debugInfo) {
        this.debugInfo = debugInfo;
    }

    @Override
    public String toString() {
        return "TransactionResult{" + "resultCode=" + resultCode 
                + ", transactionRequest=" + transactionRequest
                + ", debugInfo=" + debugInfo + '}';
    }
    
    
}
