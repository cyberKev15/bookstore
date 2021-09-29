package com.group12.bookstore.resources;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserResource {

    @PostMapping("/register")
    public String registerUser(@RequestBody Map<String, Object> userMap){
       String name = (String) userMap.get("name");
       String email = (String) userMap.get("email");
       String password = (String) userMap.get("password");
       String address = (String) userMap.get("address");
       return name + ", " + email + ", " + password + ", " + address;

    }
}
