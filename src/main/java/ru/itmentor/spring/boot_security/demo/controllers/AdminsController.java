package ru.itmentor.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.entities.User;
import ru.itmentor.spring.boot_security.demo.services.UsersServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminsController {
    private final UsersServiceImpl usersService;

    @Autowired
    public AdminsController(UsersServiceImpl usersService) {
        this.usersService = usersService;
    }

    @GetMapping()
    public List<User> usersList() {
        List<User> userList = usersService.usersList();
        return userList;
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") long id) {
        User user = usersService.getUserById(id);
        return user;
    }

    @PostMapping("/new")
    public User addUser(@RequestBody User user) {
        usersService.addUser(user);
        return user;
    }

    @PutMapping("/{id}")
    public User editUser(@RequestBody User user, @PathVariable("id") long id) {
        usersService.editUser(user, id);
        return user;
    }

    @DeleteMapping("/delete/{id}")
    public String removeUser(@PathVariable("id") long id) {
        usersService.removeUser(id);
        return "User deleted";
    }
}
