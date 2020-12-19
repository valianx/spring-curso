package com.valian.cursospringboot.dao;

import com.valian.cursospringboot.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class FakeDataDaoTest {
    private FakeDataDao fakeDataDao;

    @BeforeEach
    void setUp() {
        fakeDataDao = new FakeDataDao();
    }

    @Test
    void shouldSelectAllUsers() {
        List<User> users = fakeDataDao.selectAllUsers();
        assertThat(users).hasSize(1);

        User user = users.get(0);

        assertThat(user.getAge()).isEqualTo(22);
        assertThat(user.getFirstName()).isEqualTo("Joe");
        assertThat(user.getLastName()).isEqualTo("Jones");
        assertThat(user.getGender()).isEqualTo(User.Gender.MALE);
        assertThat(user.getEmail()).isEqualTo("joe.jones@email.com");
        assertThat(user.getUserId()).isNotNull();
    }

    @Test
    void shouldSelectUserbyUid() {
        UUID annaId = UUID.randomUUID();
        User anna = new User(annaId, "anna", "montana", User.Gender.FEMALE,
                30, "anna@email.com");
        fakeDataDao.insertUser(annaId, anna);
        assertThat(fakeDataDao.selectAllUsers()).hasSize(2);

        Optional<User> annaOptional = fakeDataDao.selectUserbyUid(annaId);
        assertThat(annaOptional.isPresent()).isTrue();
        assertThat(annaOptional.get()).isEqualTo(anna);
    }

    @Test
    void shouldNotSelectUserbyRandomUid() {
        Optional<User> user = fakeDataDao.selectUserbyUid(UUID.randomUUID());
        assertThat(user.isPresent()).isFalse();
    }

    @Test
    void shouldUpdateUser() {
        UUID joeId = fakeDataDao.selectAllUsers().get(0).getUserId();
        User newJoe = new User(joeId, "anna", "montana", User.Gender.FEMALE,
                30, "anna@email.com");
        fakeDataDao.updateUser(newJoe);
        Optional<User> user = fakeDataDao.selectUserbyUid(joeId);
        assertThat(user.isPresent()).isTrue();
        assertThat(fakeDataDao.selectAllUsers()).hasSize(1);
        assertThat(user.get()).isEqualTo(newJoe);
    }

    @Test
    void deleteUserByUid() {
        UUID joeId = fakeDataDao.selectAllUsers().get(0).getUserId();
        fakeDataDao.deleteUserByUid(joeId);
        assertThat(fakeDataDao.selectUserbyUid(joeId).isPresent()).isFalse();
        assertThat(fakeDataDao.selectAllUsers()).isEmpty();
    }

    @Test
    void insertUser() {
        UUID userId = UUID.randomUUID();
        User user = new User(userId, "anna", "montana", User.Gender.FEMALE,
                30, "anna@email.com");
        fakeDataDao.insertUser(userId, user);

        List<User> users = fakeDataDao.selectAllUsers();
        assertThat(users).hasSize(2);
        assertThat(fakeDataDao.selectUserbyUid(userId).get()).isEqualTo(user);
    }
}