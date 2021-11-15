package com.group12.bookstore.resources;

import com.group12.bookstore.domain.User;
import com.group12.bookstore.service.UserService;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
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
    
    @RequestMapping("/get")
    public User getUser(@RequestBody Map<String, Object> userMap){
      
       String email = (String) userMap.get("email");

       return userService.getUser(email);
      

    }
    
    @PostMapping("/registercard")
    public ResponseEntity<Map<String, String >> registerCard (@RequestBody Map<String, Object> userMap){
        
       String email = (String) userMap.get("email");
       String cardNumm = (String) userMap.get("cardNumber");
       String expMonthh = (String) userMap.get("expireMonth");
       String expYearr = (String) userMap.get("expYear");
       String securityCodee = (String) userMap.get("securityCode");
       
       long cardNum = parseLong(cardNumm);
       int expMonth = parseInt(expMonthh);
       int expYear = parseInt(expYearr);
       int securityCode = parseInt(securityCodee);
       
       User user = userService.registercreditcard(email, cardNum, expMonth, expYear, securityCode);
       Map<String, String> map = new HashMap<>();
       map.put("message", "Registered Successfully");
       return new ResponseEntity<>(map, HttpStatus.OK);
       
    }
    
}
