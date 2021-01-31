package com.example.demo.controller;

import com.example.demo.model.Station;
import com.example.demo.model.Trip;
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
                        new Station("CBE","Coimbatore yy")
                ));
        return "index";
    }

    @RequestMapping("/bus-tickets/from/{fromStation}/to/{toStation}")
    public String busTickets(final Model model) {
        model.addAttribute("trips"
                , List.of(
                        new Trip(3,"chenai to madurai","340","AcSleeper"),
        new Trip(5,"chenai to madurai -2","340","AcSleeper")
                ));
        return "trips";
    }
    /*
    * Routring ed point
    * */
    @RequestMapping("/bus-tickets/from/{fromStation}/to/{toStation}/sheet-selection")
    public String sheets(final Model model) {
       ;
        //accessing file name of the html
        return "seat_selection";
    }
    @RequestMapping("/passengers-list")
    public String passangers(final Model model) {
        return "passengers";
    }
}
