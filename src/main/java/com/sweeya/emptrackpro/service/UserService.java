package com.sweeya.emptrackpro.service;

import com.sweeya.emptrackpro.controller.UserController;
import com.sweeya.emptrackpro.dto.UserRequest;
import com.sweeya.emptrackpro.model.Users;
import com.sweeya.emptrackpro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

@Service
public class UserService {

    private static final Logger logger = LogManager.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    public List<Users> getAllUsers() {
        logger.info("Fetching all users from the database");
        return userRepository.findAll();
    }

    public Users getUserById(Long id) {
        logger.info("Fetching user from the database");
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public Users createUser(UserRequest userRequest) {
        Users users = new Users();
        users.setUserName(userRequest.getUsername());
        users.setPassword(userRequest.getPassword());
        users.setEmail(userRequest.getEmail());
        users.setCreatedAt(String.valueOf(System.currentTimeMillis()));

        return userRepository.save(users);
    }
}
