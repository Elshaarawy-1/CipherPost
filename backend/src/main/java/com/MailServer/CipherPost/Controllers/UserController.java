package com.MailServer.CipherPost.Controllers;


import com.MailServer.CipherPost.Services.UserService;
import com.MailServer.CipherPost.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> loginData) {
        Map<String, Object> response = new HashMap<>();
        String username = loginData.get("username");
        User user = userService.getUserByUsername(username);
        if (user == null) {
            // Username doesn't exist
            response.put("error", "Invalid username");
            return ResponseEntity.badRequest().body(response);
        }
        else {
            Long userId = user.getId();
            response.put("user_id", userId);
            response.put("redirectUrl", "/");
        }
        return ResponseEntity.ok(response);
    }
    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody Map<String, String> registerData) {
        String username = registerData.get("username");
        User user = userService.getUserByUsername(username);
        Map<String, Object> response = new HashMap<>();
        if(user == null){
            user = new User(username);
            userService.saveUser(user);
            Long userId = user.getId();
            response.put("user_id", userId);
            response.put("redirectUrl", "/");
            return ResponseEntity.ok(response);
        }
        else{
            response.put("error", "Username already exists");
            return ResponseEntity.badRequest().body(response);
        }
        }

        @GetMapping("/user-data")
        public ResponseEntity<Map<String, String>> getUserData(@RequestHeader("Authorization") String authorizationHeader) {
            // Extract the user_id from the Authorization header
            String userId = extractUserId(authorizationHeader);

            // Simulate fetching user-specific data
            Map<String, String> userData = new HashMap<>();
            userData.put("message", "This data is for user " + userId);

            return ResponseEntity.ok(userData);
        }

        private String extractUserId(String authorizationHeader) {
            System.out.println(authorizationHeader);
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                return authorizationHeader.substring(7);
            }
            throw new IllegalArgumentException("Invalid Authorization header format");
        }
}