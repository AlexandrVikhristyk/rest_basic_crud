package com.petproject.test.controllers;

import com.petproject.test.entity.CustomUser;
import com.petproject.test.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/login")
    public String index() {
        return "and_chmo.html";
    }

    @PostMapping("/login")
    public String login(String login, String password, String confirmPassword) {

        CustomUser user = new CustomUser(login, password, null);
        user.setConfirmPassword(confirmPassword);
        userService.save(user);
        System.out.println("GGGGGGGGGGGGGGGGGG");
        return "and_chmo.html";
    }
}
