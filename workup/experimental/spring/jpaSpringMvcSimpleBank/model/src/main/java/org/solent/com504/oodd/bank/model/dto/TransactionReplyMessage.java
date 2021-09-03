package org.solent.com504.oodd.bank.model.dto;

public class TransactionReplyMessage {

    private Integer code;

    private String message;

    private BankTransaction result;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BankTransaction getResult() {
        return result;
    }

    public void setResult(BankTransaction result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "TransactionReplyMessage{" + "code=" + code + ", message=" + message + ", result=" + result + '}';
    }
    
    
}
