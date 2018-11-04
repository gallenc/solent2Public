package solent.ac.uk.ood.examples.cardvalidator.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Account {

    private String issuerIdentificationNumber;

    private String individualAccountIdentifier;

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
    
}
