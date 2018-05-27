package com.salesAnalyst.version1.SalesAnalyst.controllers;

import com.salesAnalyst.version1.SalesAnalyst.entities.Users;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Calendar;

@Controller
@SessionAttributes("username")
public class CustomerController {

    @Value("${app.timezone}")
    private String timezone;

    @GetMapping(path="/user/customer") // Map ONLY GET Requests with the path /user/dashboard
    public String handleUponSuccessUserLogin(ModelMap model) {
        System.out.println("rendering customer......");

        // current year for customers timezone needs to be obtained
        java.util.TimeZone tz = java.util.TimeZone.getTimeZone(timezone);
        java.util.Calendar c = java.util.Calendar.getInstance(tz);

        model.addAttribute("currentYear", c.get(Calendar.YEAR));

        // customer details
        model.addAttribute("customerName", "TGIF Colombo");
        model.addAttribute("customerBusiness", "Restaurant Management");
        model.addAttribute("customerAddress", "555A, Canal Row, Colombo 01");

        // array of monthly sales of the customer
        int[] monthlySales = new int[] {100, 200, 125, 70, 80, 300, 200, 100, 130, 270, 0, 0};
        int[] customerProductSales = new int[] {600, 180, 250, 300, 170, 190};
        model.addAttribute("monthlySales", monthlySales);
        model.addAttribute("customerProductSales", customerProductSales);
        return "customer";
    }
}
