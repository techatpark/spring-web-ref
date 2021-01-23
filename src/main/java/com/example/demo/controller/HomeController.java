package com.example.demo.controller;

import com.example.demo.model.Station;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController {
    
    @RequestMapping("/")
    public String index(final Model model) {
        model.addAttribute("fromStations"
                , List.of(
                        new Station("CHN","Chennai"),
                        new Station("BLR","Bengaluru"),
                        new Station("MDU","Madurai"),
                        new Station("CBE","Coimbatore")
                ));
        return "index";
    }

    @RequestMapping("/bus-tickets/from/{fromStation}/to/{toStation}")
    public String busTickets(final Model model) {
        model.addAttribute("fromStations"
                , List.of(
                        new Station("CHN","Chennai"),
                        new Station("BLR","Bengaluru"),
                        new Station("MDU","Madurai"),
                        new Station("CBE","Coimbatore")
                ));
        return "index";
    }
}
