package com.valian.cursospringboot.service;

import com.valian.cursospringboot.dao.UserDao;
import com.valian.cursospringboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> getAllUsers() {
        return userDao.selectAllUsers();
    }

    public Optional<User> getUser(UUID userId) {
        return userDao.selectUserbyUid(userId);
    }

    public int updateUser(User user) {
        Optional<User> optionalUser = getUser(user.getUserId());
        if (optionalUser.isPresent()) {
            userDao.updateUser(user);
            return 1;
        }
        return -1;
    }

    public int removeUser(UUID userId) {
        Optional<User> optionalUser = getUser(userId);
        if (optionalUser.isPresent()) {
            userDao.deleteUserByUid(userId);
            return 1;
        }
        return -1;
    }

    public int insertUser(UUID userId, User user) {
        return userDao.insertUser(UUID.randomUUID(), user);
    }
}
