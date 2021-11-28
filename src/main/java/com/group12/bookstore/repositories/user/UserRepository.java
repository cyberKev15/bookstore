package com.group12.bookstore.repositories.user;

import com.group12.bookstore.domain.User;
import com.group12.bookstore.exeptions.AuthException;
import com.group12.bookstore.exeptions.BadRequestException;

public interface UserRepository {

    Integer create(String firstName, String lastName, String email, String password, String address) throws AuthException;

    User findByUserId(Integer userId) throws AuthException;

    User findByUsername(String email) throws AuthException;

    //Method to update user address
    void update(Integer userId, User user) throws BadRequestException;

    //Method to check email count - this keeps from registering the same email
    Integer getCountByEmail(String email);

    //Method to register credit card for a user
    void registerCreditCard(String email, long cardNum, int expMonth, int expYear, int securityCode) throws AuthException;
}