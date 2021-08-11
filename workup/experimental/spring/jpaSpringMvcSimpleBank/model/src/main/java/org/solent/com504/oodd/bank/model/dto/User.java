package org.solent.com504.oodd.bank.model.dto;

import javax.persistence.Embeddable;

@Embeddable
public class User {

    private String firstName;

    private String secondName;

    private String Address;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    @Override
    public String toString() {
        return "User{" + "firstName=" + firstName + ", secondName=" + secondName + ", Address=" + Address + '}';
    }
    
    
    
}
