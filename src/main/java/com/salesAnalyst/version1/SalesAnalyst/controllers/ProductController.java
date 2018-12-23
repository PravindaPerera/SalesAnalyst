package com.salesAnalyst.version1.SalesAnalyst.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Calendar;

@Controller
@SessionAttributes("username")
public class ProductController {

    @Value("${app.timezone}")
    private String timezone;

    @GetMapping(path="/user/product") // Map ONLY GET Requests with the path /user/product
    public String handleCustomerViewDisplay(ModelMap model) {

        if (!model.containsAttribute("username")) {
            return "redirect:/";
        }

        // current year for customers timezone needs to be obtained
        java.util.TimeZone tz = java.util.TimeZone.getTimeZone(timezone);
        java.util.Calendar c = java.util.Calendar.getInstance(tz);

        model.addAttribute("currentYear", c.get(Calendar.YEAR));

        // customer details
        model.addAttribute("productName", "Product A");

        // array of monthly sales of the customer
        int[] monthlySales = new int[] {100, 200, 125, 70, 80, 300, 200, 100, 130, 270, 0, 0};
        int[] customerProductSales = new int[] {600, 180, 250, 300, 170, 190};
        model.addAttribute("monthlySales", monthlySales);
        model.addAttribute("customerProductSales", customerProductSales);
        return "product";
    }
}
