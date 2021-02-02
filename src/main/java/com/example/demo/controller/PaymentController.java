package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PaymentController {

    @RequestMapping("/bus-tickets/payment-response")
    public String pamentDetails(final Model model,@RequestParam String transactionCode, @RequestParam String orderCode, @RequestParam String status ) {

        model.addAttribute("actionTitle",status.equals("success") ? "Ticket booked successfully":"Payment failed");
        return status.equals("success") ?"ticket-details":"error";
    }
}
