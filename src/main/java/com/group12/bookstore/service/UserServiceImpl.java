package com.group12.bookstore.service;

import com.group12.bookstore.domain.User;
import com.group12.bookstore.exeptions.AuthException;
import com.group12.bookstore.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Pattern;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User validateUser(String email, String password) throws AuthException {
        return null;
    }

    @Override
    public User registerUser(String firstName, String lastName, String email, String password, String address) throws AuthException {
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        if(email != null) email = email.toLowerCase();
        if(!pattern.matcher(email).matches()) throw new AuthException("Email format is invalid.");

        Integer count = userRepository.getCountByEmail(email);
        if(count > 0) {
            throw new AuthException("This email is already in use.");
        } else {
            Integer userId = userRepository.create(firstName, lastName, email, password, address);
            return userRepository.findByUserId(userId);
        }
    }
}
