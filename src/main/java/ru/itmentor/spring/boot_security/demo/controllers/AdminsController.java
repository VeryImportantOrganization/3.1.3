package ru.itmentor.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.dao.RolesDao;
import ru.itmentor.spring.boot_security.demo.models.User;
import ru.itmentor.spring.boot_security.demo.services.UsersServiceImpl;

@Controller
@RequestMapping("/admin")
public class AdminsController {
    private final UsersServiceImpl usersService;

    @Autowired
    public AdminsController(UsersServiceImpl usersService) {
        this.usersService = usersService;
    }

    @GetMapping()
    public String usersList(Model model) {
        model.addAttribute("user", usersService.usersList());
        return "start";
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", usersService.getUserById(id));
        return "show";
    }

    @GetMapping("/new")
    public String creationUser(@ModelAttribute("user") User user) {
        return "new";
    }

    @PostMapping
    public String addUser(@ModelAttribute("user") User user) {
        usersService.addUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/edit")
    public String editionUser(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", usersService.getUserById(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String editUser(@ModelAttribute("user") User user, @PathVariable("id") long id) {
        usersService.editUser(user, id);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}/delete")
    public String removeUser(@PathVariable("id") long id) {
        usersService.removeUser(id);
        return "redirect:/admin";
    }
}
