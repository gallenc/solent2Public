package solent.ac.uk.ood.examples.cardcheck.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement
public class Transaction {

    private Date timestamp;

    private Integer transactionId;

    private Account from;

    private Account to;

    private Double amount;

    private Card cardFrom;

    private Card cardTo;

    private Integer id;


    /**
     * @return the transactionId
     */
    public Integer getTransactionId() {
        return transactionId;
    }

    /**
     * @param transactionId the transactionId to set
     */
    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    /**
     * @return the from
     */
    public Account getFrom() {
        return from;
    }

    /**
     * @param from the from to set
     */
    public void setFrom(Account from) {
        this.from = from;
    }

    /**
     * @return the to
     */
    public Account getTo() {
        return to;
    }

    /**
     * @param to the to to set
     */
    public void setTo(Account to) {
        this.to = to;
    }

    /**
     * @return the amount
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    /**
     * @return the cardFrom
     */
    public Card getCardFrom() {
        return cardFrom;
    }

    /**
     * @param cardFrom the cardFrom to set
     */
    public void setCardFrom(Card cardFrom) {
        this.cardFrom = cardFrom;
    }

    /**
     * @return the cardTo
     */
    public Card getCardTo() {
        return cardTo;
    }

    /**
     * @param cardTo the cardTo to set
     */
    public void setCardTo(Card cardTo) {
        this.cardTo = cardTo;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the timestamp
     */
    public Date getTimestamp() {
        return timestamp;
    }

    /**
     * @param timestamp the timestamp to set
     */
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
