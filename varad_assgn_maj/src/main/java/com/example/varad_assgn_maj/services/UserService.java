package com.example.varad_assgn_maj.services;

import com.example.varad_assgn_maj.model.User;
import com.example.varad_assgn_maj.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public void createUser(User user) {
        userRepository.save(user); // Save the new user
    }
    public Optional<User> findUserById(Integer userID) {
        return userRepository.findById(userID); // Find user by ID
    }
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email); // Find user by email
    }
    public List<User> getAllUsers() {
        return userRepository.findAll(); // Fetch all users
    }

}
