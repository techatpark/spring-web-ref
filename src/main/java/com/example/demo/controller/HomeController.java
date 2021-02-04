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


    private final TemplateEngine templateEngine;

    public HomeController(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    @GetMapping(value = "/"
            , produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String welcomeAsHTML(final Model model) {
        return templateEngine.process("index",new Context());
    }

    @GetMapping("/login")
    public String login(final Model model) {
        return "login";
    }
}
