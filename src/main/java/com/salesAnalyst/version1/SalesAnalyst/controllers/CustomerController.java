package com.salesAnalyst.version1.SalesAnalyst.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Calendar;

@Controller
@SessionAttributes("username")
public class CustomerController {

    @Value("${app.timezone}")
    private String timezone;

    @Value("${app.months}")
    private String months;

    @GetMapping(path="/user/customer") // Map ONLY GET Requests with the path /user/customer
    public String handleCustomerViewDisplay(ModelMap model) {

        String[] monthValues = months.split(",");

        if (!model.containsAttribute("username")) {
            return "redirect:/";
        }

        // current year for customers timezone needs to be obtained
        java.util.TimeZone tz = java.util.TimeZone.getTimeZone(timezone);
        java.util.Calendar c = java.util.Calendar.getInstance(tz);

        model.addAttribute("currentYear", c.get(Calendar.YEAR));

        // customer details
        model.addAttribute("customerName", "TGIF Colombo");
        model.addAttribute("customerBusiness", "Restaurant Management");
        model.addAttribute("customerAddress", "555A, Canal Row, Colombo 01");

        // array of company total sales each month
        int[] totalMonthlySales = new int[] {500, 400, 325, 190, 180, 800, 600, 500, 630, 470, 0, 0};
        // array of monthly sales of the customer
        int[] customerMonthlySales = new int[] {100, 200, 125, 70, 80, 300, 200, 100, 130, 270, 0, 0};
        // array containing monthly customer sales proportionality to total monthly sales
        double[] salesPropotion = new double[12];
        for (int i=0; i<totalMonthlySales.length; i++) {
            if (totalMonthlySales[i] == 0) {
                salesPropotion[i] = 0;
            } else {
                salesPropotion[i] = ((double) customerMonthlySales[i]/totalMonthlySales[i]) * 100;
            }
        }
        // array of product sales for the focused customer
        int[] customerProductSales = new int[] {600, 180, 250, 300, 170, 190};
        // product names of the company
        String[] productNames = new String[] {"Product1", "Product2", "Product3", "Product4", "Product5", "Product6"};
        // colour codes for each product
        String[] colourCodes = new String[] {"#26B99A", "#26B9B9", "#268AB9", "#264CB9", "#5326B9", "#8A26B9"};

        model.addAttribute("totalMonthlySales", totalMonthlySales);
        model.addAttribute("customerMonthlySales", customerMonthlySales);
        model.addAttribute("customerProductSales", customerProductSales);
        model.addAttribute("productNames", productNames);
        model.addAttribute("colourCodes", colourCodes);
        model.addAttribute("monthValues", monthValues);
        model.addAttribute("salesPropotion", salesPropotion);
        model.addAttribute("notApplicable", "N/A");
        return "customer";
    }
}
