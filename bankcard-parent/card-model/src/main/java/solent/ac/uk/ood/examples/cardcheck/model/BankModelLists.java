package solent.ac.uk.ood.examples.cardcheck.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class BankModelLists {

    @XmlElementWrapper(name = "bankList")
    @XmlElement(name = "bank")
    private List<Bank> bankList = new ArrayList<Bank>();

    @XmlElementWrapper(name = "accountList")
    @XmlElement(name = "account")
    private List<Account> accountList = new ArrayList<Account>();

    @XmlElementWrapper(name = "transactionList")
    @XmlElement(name = "transaction")
    private List<Transaction> transactionList = new ArrayList<Transaction>();

    /**
     * @return the bankList
     */
    public List<Bank> getBankList() {
        return bankList;
    }

    /**
     * @param bankList the bankList to set
     */
    public void setBankList(List<Bank> bankList) {
        this.bankList = bankList;
    }

    /**
     * @return the accountList
     */
    public List<Account> getAccountList() {
        return accountList;
    }

    /**
     * @param accountList the accountList to set
     */
    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

    /**
     * @return the transactionList
     */
    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    /**
     * @param transactionList the transactionList to set
     */
    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }
}
