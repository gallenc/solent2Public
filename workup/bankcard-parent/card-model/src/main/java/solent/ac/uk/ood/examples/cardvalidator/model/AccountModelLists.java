/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solent.ac.uk.ood.examples.cardvalidator.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author cgallen
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class AccountModelLists {
    
    private Integer latestAccountId=0;

    @XmlElementWrapper(name = "accountList")
    @XmlElement(name = "account")
    private List<Account> accountList = new ArrayList();

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

    public Integer getLatestAccountId() {
        return latestAccountId;
    }

    public void setLatestAccountId(Integer latestAccountId) {
        this.latestAccountId = latestAccountId;
    }


}
