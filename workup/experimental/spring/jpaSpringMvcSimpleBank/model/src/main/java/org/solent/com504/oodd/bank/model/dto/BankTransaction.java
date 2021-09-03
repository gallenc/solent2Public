package org.solent.com504.oodd.bank.model.dto;

import java.util.Date;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class BankTransaction {

    private Long id;
    
    private String transactionId = UUID.randomUUID().toString();

    private BankAccount fromAccount;

    private BankAccount toAccount;

    private Date transactionDate = new Date();

    private Double amount;

    private BankTransactionStatus status;

    private String message;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToOne
    public BankAccount getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(BankAccount fromAccount) {
        this.fromAccount = fromAccount;
    }

    @OneToOne
    public BankAccount getToAccount() {
        return toAccount;
    }

    public void setToAccount(BankAccount toAccount) {
        this.toAccount = toAccount;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public BankTransactionStatus getStatus() {
        return status;
    }

    public void setStatus(BankTransactionStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    @Override
    public String toString() {
        return "BankTransaction{" + "id=" + id + ", transactionId=" + transactionId + ", fromAccount=" + fromAccount + ", toAccount=" + toAccount + ", transactionDate=" + transactionDate + ", amount=" + amount + ", status=" + status + ", message=" + message + '}';
    }


}
