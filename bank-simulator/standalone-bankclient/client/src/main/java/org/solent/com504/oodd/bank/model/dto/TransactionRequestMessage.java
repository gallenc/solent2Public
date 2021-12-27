package org.solent.com504.oodd.bank.model.dto;

public class TransactionRequestMessage {

    private CreditCard fromCard;

    private CreditCard toCard;

    private Double amount;

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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "TransactionRequestMessage{" + "fromCard=" + fromCard + ", toCard=" + toCard + ", amount=" + amount + '}';
    }
    
    
}
