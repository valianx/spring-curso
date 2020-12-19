package com.valian.cursospringboot.service;

import com.valian.cursospringboot.dao.FakeDataDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class UserServiceTest {

    @Mock
    private FakeDataDao fakeDateDao;

    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserService(fakeDateDao);
    }

    @Test
    void shouldGetAllUsers() {
        userService.getAllUsers();
    }

    @Test
    void getUser() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void removeUser() {
    }

    @Test
    void insertUser() {
    }
}