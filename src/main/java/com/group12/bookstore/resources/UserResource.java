package com.group12.bookstore.resources;

import com.group12.bookstore.domain.User;
import com.group12.bookstore.service.user.UserService;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserResource {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody Map<String, Object> userMap) {
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
    public User getUser(@RequestBody Map<String, Object> userMap) {

        String email = (String) userMap.get("email");

        return userService.getUser(email);
    }
    
    @PostMapping("/registercard")
    public ResponseEntity<Map<String, String>> registerCard (@RequestBody Map<String, Object> userMap){
        
       String email = (String) userMap.get("email");
       long cardNum = (long) userMap.get("cardNumber");
       int expMonth = (int) userMap.get("expireMonth");
       int expYear = (int) userMap.get("expYear");
       int securityCode = (int) userMap.get("securityCode");
       

       User user = userService.registercreditcard(email, cardNum, expMonth, expYear, securityCode);
       Map<String, String> map = new HashMap<>();
       map.put("message", "Registered Successfully");
       return new ResponseEntity<>(map, HttpStatus.OK);
       
    }
    


 @PutMapping("/{userId}")
    public ResponseEntity<Map<String, Boolean>> updateUser(HttpServletRequest request, @PathVariable("userId") Integer userId, @RequestBody User user) {
        userService.updateUser(userId, user);
        Map<String, Boolean> map = new HashMap<>();
        map.put("Success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }


}
