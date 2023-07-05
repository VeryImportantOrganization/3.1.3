package ru.itmentor.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itmentor.spring.boot_security.demo.services.UsersServiceImpl;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UsersController {
    private final UsersServiceImpl usersService;

    @Autowired
    public UsersController(UsersServiceImpl usersService) {
        this.usersService = usersService;
    }

    @GetMapping()
    public String userInfo(Principal principal, Model model) {
        model.addAttribute("user", usersService.getUserByName(principal.getName()));
        return "user";
    }
}
