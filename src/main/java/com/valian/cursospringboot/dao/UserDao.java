package com.valian.cursospringboot.dao;

import com.valian.cursospringboot.model.User;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserDao {

    List<User> selectAllUsers();

    Optional<User> selectUserbyUid(UUID userId);

    int updateUser(User user);

    int deleteUserByUid(UUID userId);

    int insertUser(UUID UserId,User user);
}
