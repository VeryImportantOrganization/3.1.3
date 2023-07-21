package ru.itmentor.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itmentor.spring.boot_security.demo.entities.User;
import ru.itmentor.spring.boot_security.demo.services.UsersServiceImpl;

import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
@RequestMapping("/api/moderator")
public class ModeratorsController {

    private final UsersServiceImpl usersService;

    @Autowired
    public ModeratorsController(UsersServiceImpl usersService) {
        this.usersService = usersService;
    }

    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> userList = usersService.usersList();
        ResponseEntity<List<User>> response = new ResponseEntity<>(userList, INTERNAL_SERVER_ERROR);
        return response;
    }
}
