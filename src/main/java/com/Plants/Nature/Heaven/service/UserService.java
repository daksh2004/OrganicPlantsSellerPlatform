package com.Plants.Nature.Heaven.service;

import com.Plants.Nature.Heaven.entity.User;
import com.Plants.Nature.Heaven.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    // Save a single user with email check
    public User saveUser(User user) {
        List<User> existingUsers = repository.findByEmail(user.getEmail());
        if (!existingUsers.isEmpty()) {
            throw new IllegalArgumentException("Email already registered: " + user.getEmail());
        }
        return repository.save(user);
    }

    // Save multiple users
    public List<User> saveUsers(List<User> users) {
        return repository.saveAll(users);
    }

    // Get all users
    public List<User> getUsers() {
        return repository.findAll();
    }

    // Get user by ID
    public User getUserById(int userID) {
        return repository.findById(userID).orElse(null);
    }

    // Get user by name
    public User getUserByName(String name) {
        return repository.findByName(name);
    }

    // Get user by email
    public User getUserByEmail(String email) {
        List<User> users = repository.findByEmail(email);
        return users.isEmpty() ? null : users.get(0);
    }

    // Delete user by ID
    public String deleteUser(int userID) {
        if (repository.existsById(userID)) {
            repository.deleteById(userID);
            return "User removed! ID: " + userID;
        } else {
            return "User not found with ID: " + userID;
        }
    }

    // Update user
    public User updateUser(User user) {
        User existingUser = repository.findById(user.getUserID()).orElse(null);

        if (existingUser == null) {
            throw new RuntimeException("User with ID " + user.getUserID() + " not found.");
        }

        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        existingUser.setAddress(user.getAddress());
        existingUser.setRole(user.getRole());

        return repository.save(existingUser);
    }
}
