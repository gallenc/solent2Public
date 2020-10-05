package solent.ac.uk.ood.examples.cardvalidator.model;

import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Transaction {

    private Integer transactionId;

    private Date date;

    private Double amount;

    private CreditCard fromCard;

    private CreditCard toCard;

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public CreditCard getFromCard() {
        return fromCard;
    }

    public void setFromCard(CreditCard fromCard) {
        this.fromCard = fromCard;
    }

    public CreditCard getToCard() {
        return toCard;
    }

    public void setToCard(CreditCard toCard) {
        this.toCard = toCard;
    }

    @Override
    public String toString() {
        return "Transaction{" + "transactionId=" + transactionId + ", date=" + date + ", amount=" + amount 
                + ", fromCard=" + fromCard + ", toCard=" + toCard + '}';
    }
    
    
}
