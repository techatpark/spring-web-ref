package com.example.demo.controller;

import com.example.demo.model.Station;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@Controller
public class BusTicketController {
    @RequestMapping("/bus-tickets/get")
    public String getTicket(final Model model) {
        model.addAttribute("actionTitle","print/sms ticket");
        return "manage_ticket";
    }


    @RequestMapping("/bus-tickets/edit")
    public String editTicket(final Model model) {
        model.addAttribute("actionTitle","Reschdule ticket");
        return "manage_ticket";
    }

    @RequestMapping("/bus-tickets/cancel")
    public String cancelTicket(final Model model) {
        model.addAttribute("actionTitle","Cancel ticket");
        return "manage_ticket";
    }

}
