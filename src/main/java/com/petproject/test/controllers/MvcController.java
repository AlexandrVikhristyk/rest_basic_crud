package com.petproject.test.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MvcController {
    @GetMapping("/login")
    public String welcome(){
        return "index.html";
    }
}
