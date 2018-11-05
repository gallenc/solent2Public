package solent.ac.uk.ood.examples.cardvalidator.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Account {

    private String issuerIdentificationNumber;

    private String individualAccountIdentifier;
    
    private String name;

    private Double balance;

    private Integer currentCardIssueNumber;

    public String getIssuerIdentificationNumber() {
        return issuerIdentificationNumber;
    }

    public void setIssuerIdentificationNumber(String issuerIdentificationNumber) {
        this.issuerIdentificationNumber = issuerIdentificationNumber;
    }

    public String getIndividualAccountIdentifier() {
        return individualAccountIdentifier;
    }

    public void setIndividualAccountIdentifier(String individualAccountIdentifier) {
        this.individualAccountIdentifier = individualAccountIdentifier;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Integer getCurrentCardIssueNumber() {
        return currentCardIssueNumber;
    }

    public void setCurrentCardIssueNumber(Integer currentCardIssueNumber) {
        this.currentCardIssueNumber = currentCardIssueNumber;
    }

    @Override
    public String toString() {
        return "Account{" + "issuerIdentificationNumber=" + issuerIdentificationNumber 
                + ", individualAccountIdentifier=" + individualAccountIdentifier 
                + ", name=" + name 
                + ", balance=" + balance 
                + ", currentCardIssueNumber=" + currentCardIssueNumber + '}';
    }
    
    
    
}
