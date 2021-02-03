package com.example.demo.controller;

import com.example.demo.model.PaymentStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PaymentController {

    @RequestMapping("/payment-response")
    public String paymentDetails(final Model model
            ,@RequestParam String transactionCode
            ,@RequestParam String orderCode
            ,@RequestParam PaymentStatus status ) {

        model.addAttribute("actionTitle",status.equals("success") ? "Ticket booked successfully":"Payment failed");
        return status.equals("success") ?"ticket-details":"error";
    }
}
