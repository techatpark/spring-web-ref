package com.example.demo.controller;

import com.example.demo.model.Tenant;
import com.example.demo.service.TenantService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/{tenantCode}")
public class HomeController {

    private final TenantService tenantService;

    public HomeController(TenantService tenantService) {
        this.tenantService = tenantService;
    }


    @GetMapping
    public String index(final Tenant tenant,
                                final Model model) {
        model.addAttribute("tenant",tenant);
        return "index";
    }

    @GetMapping("/login")
    public String login(final Model model) {
        return "login";
    }
}
