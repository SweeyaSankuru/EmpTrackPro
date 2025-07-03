package com.sweeya.emptrackpro.service;

import com.sweeya.emptrackpro.model.Users;
import com.sweeya.emptrackpro.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void getAllUsersTest(){
        List<Users> users = new ArrayList<>();

        //Arrange
        Users user = new Users();
        user.setUserName("Sweeya");
        user.setAge(27);
        user.setPassword("xxxx");
        users.add(user);

        //Mock the behaviour
        when(userRepository.findAll()).thenReturn(users);

        //Assert
        //Act
        List<Users> result = userService.getAllUsers();
        assertEquals(1, result.size());
        assertEquals("Sweeya", result.get(0).getUserName());
    }


    @Test
    public void getAllUserByIdTest(){
        //Arrange

        Users user = new Users();
        user.setUserName("Sweeya");
        user.setAge(27);
        user.setPassword("xxxx");
        user.setId(1L);

        //Mock the behaviour
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        //Assert
        //Act
        Users u = userService.getUserById(1L);
        assertEquals("Sweeya",u.getUserName());
        assertEquals(27, u.getAge());
        assertEquals(1L, u.getId());

    }

}
