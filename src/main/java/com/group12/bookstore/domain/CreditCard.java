package com.group12.bookstore.domain;
import java.math.BigInteger;

public class CreditCard {

    private Integer expireMonth;
    private Integer expireYear;
    private Integer securityCode;
    private BigInteger cardNumber;
        /* This is apparently supposed to link to the creditcard_pkey.
            I will rename is need be.
         */


    private String email; //the email is how we'll link to user accounts.
    private String firstName;
    private String lastName;
    private String address; //String to get the billing address


    public CreditCard(String email, String firstName, String lastName,  String address) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }





    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}