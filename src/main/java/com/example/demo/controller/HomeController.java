package com.example.demo.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

public class HomeController {
    
    @RequestMapping("/")
    public String index(final Model model) {
        return "index";
    }
}
