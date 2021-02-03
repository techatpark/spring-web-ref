package com.example.demo.controller;

import com.example.demo.model.Trip;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/bus-tickets")
public class BusTicketController {

    @RequestMapping("/from/{fromStation}/to/{toStation}")
    public String search(final Model model) {
        model.addAttribute("trips"
                , List.of(
                        new Trip(3,"chenai to madurai","340","AcSleeper"),
                        new Trip(5,"chenai to madurai -2","340","AcSleeper")
                ));

        return "trips";
    }

    @RequestMapping("/get")
    public String getTicket(final Model model) {
        model.addAttribute("actionTitle","print/sms ticket");
        return "manage_ticket";
    }


    @RequestMapping("/edit")
    public String editTicket(final Model model) {
        model.addAttribute("actionTitle","Reschdule ticket");
        return "manage_ticket";
    }

    @RequestMapping("/cancel")
    public String cancelTicket(final Model model) {
        model.addAttribute("actionTitle","Cancel ticket");
        return "manage_ticket";
    }

}
