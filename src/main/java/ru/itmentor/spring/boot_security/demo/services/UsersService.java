package ru.itmentor.spring.boot_security.demo.services;

import ru.itmentor.spring.boot_security.demo.entities.User;

import java.util.List;

public interface UsersService {
    void addUser(User user);
    void editUser(User user, long id);
    void removeUser(long id);
    User getUserById(long id);
    List<User> usersList();
    User getUserByName(String name);
}
