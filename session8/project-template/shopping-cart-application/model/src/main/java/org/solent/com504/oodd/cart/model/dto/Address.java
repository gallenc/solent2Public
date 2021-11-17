package org.solent.com504.oodd.cart.model.dto;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

    private String houseNumber;

    private String addressLine1;

    private String addressLine2;
    
    private String county;
    
    private String city;

    private String postcode;

    private String mobile;

    private String telephone;

    private String country;

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }


    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Address{" + "houseNumber=" + houseNumber + ", addressLine1=" + addressLine1 + ", addressLine2=" + addressLine2 + ", county=" + county + ", city=" + city + ", postcode=" + postcode + ", mobile=" + mobile + ", telephone=" + telephone + ", country=" + country + '}';
    }
    
    


    
    
}
