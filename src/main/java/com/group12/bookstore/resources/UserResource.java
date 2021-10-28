package com.group12.bookstore.resources;

import com.group12.bookstore.domain.User;
import com.group12.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserResource {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody Map<String, Object> userMap){
       String firstName = (String) userMap.get("firstName");
       String lastName = (String) userMap.get("lastName");
       String email = (String) userMap.get("email");
       String password = (String) userMap.get("password");
       String address = (String) userMap.get("address");

       User user = userService.registerUser(firstName, lastName, email, password, address);
       Map<String, String> map = new HashMap<>();
       map.put("message", "Registered Successfully");
       return new ResponseEntity<>(map, HttpStatus.OK);

    }
}
