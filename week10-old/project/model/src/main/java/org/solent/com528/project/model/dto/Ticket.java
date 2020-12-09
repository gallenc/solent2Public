/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com528.project.model.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * THIS IS A VERY BASIC TICKET - YOU WILL NEED TO IMPROVE THIS
 * @author cgallen
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Ticket {
    
    private String startStation;
    
    private Double cost;
    
    private String encryptedHash;

    public String getStartStation() {
        return startStation;
    }

    public void setStartStation(String startStation) {
        this.startStation = startStation;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getEncryptedHash() {
        return encryptedHash;
    }

    public void setEncryptedHash(String encryptedHash) {
        this.encryptedHash = encryptedHash;
    }

    @Override
    public String toString() {
        return "Ticket{" + "startStation=" + startStation + ", cost=" + cost + ", encryptedHash=" + encryptedHash + '}';
    }


    

}
