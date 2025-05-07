package com.Plants.Nature.Heaven.controller;

import com.Plants.Nature.Heaven.entity.User;
import com.Plants.Nature.Heaven.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService service;

    // 🔹 REGISTER SINGLE USER
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            User savedUser = service.saveUser(user);
            return ResponseEntity.ok(savedUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Collections.singletonMap("error", e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "Registration failed. Please try again."));
        }
    }


    // 🔹 REGISTER MULTIPLE USERS
    @PostMapping("/register/bulk")
    public ResponseEntity<List<User>> registerMultipleUsers(@RequestBody List<User> users) {
        return ResponseEntity.ok(service.saveUsers(users));
    }

    // 🔹 GET ALL USERS
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(service.getUsers());
    }

    // 🔹 GET USER BY ID
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        User user = service.getUserById(id);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    // 🔹 GET USER BY NAME
    @GetMapping("/user/name/{name}")
    public ResponseEntity<User> getUserByName(@PathVariable String name) {
        User user = service.getUserByName(name);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    // 🔹 UPDATE USER
    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        return ResponseEntity.ok(service.updateUser(user));
    }

    // 🔹 DELETE USER
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        String result = service.deleteUser(id);
        return ResponseEntity.ok(Collections.singletonMap("message", result));
    }

    // 🔹 LOGIN USER
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginUser(@RequestBody User loginData) {
        Map<String, Object> response = new HashMap<>();
        User user = service.getUserByEmail(loginData.getEmail());

        if (user != null && user.getPassword().equals(loginData.getPassword())) {
            response.put("success", true);
            response.put("name", user.getName());
            response.put("role", user.getRole());
            return ResponseEntity.ok(response);
        } else {
            response.put("success", false);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
}
