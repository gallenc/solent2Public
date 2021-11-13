package org.solent.com504.oodd.cart.model.dto;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

    private String houseNumber;

    private String addresLine1;

    private String addressLine2;

    private String postcode;

    private String mobile;

    private String telephone;

    private String country;
}
