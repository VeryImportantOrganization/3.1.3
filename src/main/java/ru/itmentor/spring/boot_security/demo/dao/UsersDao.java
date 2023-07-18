package ru.itmentor.spring.boot_security.demo.dao;

import ru.itmentor.spring.boot_security.demo.models.User;

import java.util.List;

public interface UsersDao {
    void addUser(User user);
    void editUser(User user, long id);
    void removeUser(long id);
    User getUserById(long id);
    List<User> getAllUsers();
    User getUserByName(String name);
}
