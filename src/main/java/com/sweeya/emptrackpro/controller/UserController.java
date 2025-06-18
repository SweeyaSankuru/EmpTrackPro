package com.sweeya.emptrackpro.controller;

import com.sweeya.emptrackpro.dto.UserRequest;
import com.sweeya.emptrackpro.model.Users;
import com.sweeya.emptrackpro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private static final Logger logger = LogManager.getLogger(UserController.class);
    private long UUID;

    @Autowired
    private UserService userService;

    @GetMapping
    public List<Users> getusers(){
        logger.info("All the users are displayed received 200 OK");
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Users getUserById(@PathVariable Long id){
        logger.info("User is displayed received 200 OK");
        return userService.getUserById(id);
    }

    @PostMapping
    public ResponseEntity<Users> createUser(@RequestBody UserRequest userRequest){
        Users newUser = userService.createUser(userRequest);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }


}
