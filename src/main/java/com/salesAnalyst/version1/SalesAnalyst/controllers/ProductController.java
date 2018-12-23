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

    @Value("${app.months}")
    private String months;

    @GetMapping(path="/user/product") // Map ONLY GET Requests with the path /user/product
    public String handleCustomerViewDisplay(ModelMap model) {

        String[] monthValues = months.split(",");

        if (!model.containsAttribute("username")) {
            return "redirect:/";
        }

        // current year for customers timezone needs to be obtained
        java.util.TimeZone tz = java.util.TimeZone.getTimeZone(timezone);
        java.util.Calendar c = java.util.Calendar.getInstance(tz);

        model.addAttribute("currentYear", c.get(Calendar.YEAR));

        // product details
        model.addAttribute("productName", "Product A");

        // array of company total sales each month
        int[] totalMonthlySales = new int[] {500, 400, 325, 190, 180, 800, 600, 500, 630, 470, 0, 0};
        // array of monthly sales of the focused product
        int[] monthlyProductSales = new int[] {100, 200, 125, 70, 80, 300, 200, 100, 130, 270, 0, 0};
        // array containing monthly product sales proportionality to total monthly sales
        double[] salesPropotion = new double[12];
        for (int i=0; i<totalMonthlySales.length; i++) {
            if (totalMonthlySales[i] == 0) {
                salesPropotion[i] = 0;
            } else {
                salesPropotion[i] = ((double) monthlyProductSales[i]/totalMonthlySales[i]) * 100;
            }
        }
        model.addAttribute("totalMonthlySales", totalMonthlySales);
        model.addAttribute("monthlyProductSales", monthlyProductSales);
        model.addAttribute("salesPropotion", salesPropotion);
        model.addAttribute("monthValues", monthValues);
        model.addAttribute("notApplicable", "N/A");
        return "product";
    }
}
