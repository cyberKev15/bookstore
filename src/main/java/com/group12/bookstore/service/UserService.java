package com.group12.bookstore.service;

import com.group12.bookstore.domain.User;
import com.group12.bookstore.exeptions.AuthException;

public interface UserService {

    User validateUser(String email, String password) throws AuthException;

    User registerUser(String firstName, String lastName, String email, String password, String address) throws AuthException;
    
    User getUser(String email) throws AuthException;
}
