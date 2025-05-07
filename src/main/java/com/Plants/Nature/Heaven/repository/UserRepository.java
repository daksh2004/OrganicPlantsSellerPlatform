package com.Plants.Nature.Heaven.repository;

import com.Plants.Nature.Heaven.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByName(String name);

    List<User> findByEmail(String email); // Changed to List to handle duplicates
}
