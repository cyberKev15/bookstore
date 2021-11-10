package com.group12.bookstore.repositories;

import com.group12.bookstore.exeptions.AuthException;
import com.group12.bookstore.domain.User;

public interface UserRepository {

    Integer create(String firstName, String lastName, String email, String password, String address) throws AuthException;

    User findByUserId(Integer userId) throws AuthException;

    User findByUsername(String email) throws AuthException;

    //Method to check email count - this keeps from registering the same email
    Integer getCountByEmail(String email);
    
    void registercreditcard(String email, String cardNum, String expMonth, String expYear, String securityCode) throws AuthException;

}
