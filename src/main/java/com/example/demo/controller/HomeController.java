package com.example.demo.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.StringWriter;

@Controller
public class HomeController {


    @GetMapping
    public String welcomeAsHTML(final Model model) {
        return "index";
    }

    @GetMapping("/login")
    public String login(final Model model) {
        return "login";
    }
}
