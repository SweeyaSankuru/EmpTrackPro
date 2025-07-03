package com.sweeya.emptrackpro.service;

import com.sweeya.emptrackpro.dto.UserRequest;
import com.sweeya.emptrackpro.model.Users;
import com.sweeya.emptrackpro.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

@Service
public class UserService {

    private static final Logger logger = LogManager.getLogger(UserService.class);

    private static final int CHILD_MAX = 12;
    private static final int TEEN_MIN = 13;
    private static final int TEEN_MAX = 19;
    private static final int ADULT_MIN = 20;
    private static final int ADULT_MAX = 50;
    private static final int SENIOR_MIN = 51;
    private static final int SENIOR_MAX = 100;

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
        users.setAge(userRequest.getAge());
        users.setCreatedAt(String.valueOf(System.currentTimeMillis()));

        return userRepository.save(users);
    }

    public List<Users> getUserWithStartLetter(String startsWith) {
        if (startsWith != null) {
            String letter = startsWith.toLowerCase();
            List<Users> allUsers = userRepository.findAll();
            List<Users> fileteredUsers = new ArrayList<>();
            for (Users user : allUsers) {
                String name = user.getUserName();
                if (name != null && name.toLowerCase().startsWith(letter)) {
                    fileteredUsers.add(user);
                }
            }
            return fileteredUsers;
        }
        return Collections.emptyList();
    }

    public List<Users> getUserByAge(String category) {
        List<Users> allUsers = userRepository.findAll();
        List<Users> filteredUsers = new ArrayList<>();

        if (category != null) {
            for (Users user : allUsers){
                if (isInCategory(user, category))
                {
                    filteredUsers.add(user);
                }
            }
            return filteredUsers;
        }
        return Collections.emptyList();
    }

    private boolean isInCategory(Users user, String category) {
        int age = user.getAge();
        switch (category.toLowerCase()) {
            case "child": return age < CHILD_MAX;
            case "teen": return age >= TEEN_MIN && age <= TEEN_MAX;
            case "adult": return age >= ADULT_MIN && age <= ADULT_MAX;
            case "senior": return age >= SENIOR_MIN && age <= SENIOR_MAX;
            default: return false;
        }
    }

}
