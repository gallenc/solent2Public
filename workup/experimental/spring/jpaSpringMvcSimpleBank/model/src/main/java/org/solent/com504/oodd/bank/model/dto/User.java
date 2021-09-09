package org.solent.com504.oodd.bank.model.dto;

import javax.persistence.Embeddable;
import javax.persistence.Transient;
import org.solent.com504.oodd.password.PasswordUtils;

@Embeddable
public class User {

    private String firstName = "";

    private String secondName = "";

    private String address = "";

    private String username = "";

    private String password = "";

    private String hashedPassword = "";

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
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // passwords not saved in database only passwordhash is saved
    @Transient
    public String getPassword() {
        return password;
    }

    // generate hashed password to save in database
    public void setPassword(String password) {
        this.password = password;
        setHashedPassword(PasswordUtils.hashPassword(password));
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    // no password or hashed password
    @Override
    public String toString() {
        return "User{" + "firstName=" + firstName + ", secondName=" + secondName + ", address=" + address + ", username=" + username + '}';
    }

}
