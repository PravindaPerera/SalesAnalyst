package com.salesAnalyst.version1.SalesAnalyst.controllers;

import com.salesAnalyst.version1.SalesAnalyst.serviceFacades.CoustomerAnerlysisFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.ArrayList;
import java.util.Calendar;

@Controller
@SessionAttributes("username")
public class CustomerController {

    @Value("${app.timezone}")
    private String timezone;

    @Value("${app.months}")
    private String months;
    @Autowired
    CoustomerAnerlysisFacade coustomerAnerlysisFacade;


    @GetMapping(path="/user/customer") // Map ONLY GET Requests with the path /user/customer
    public String handleCustomerViewDisplay(ModelMap model, @RequestParam(value = "custId", required = false) String custId) {

        String[] monthValues = months.split(",");

        if (!model.containsAttribute("username")) {
            return "redirect:/";
        }
        // current year for customers timezone needs to be obtained
        java.util.TimeZone tz = java.util.TimeZone.getTimeZone(timezone);
        java.util.Calendar c = java.util.Calendar.getInstance(tz);

        model.addAttribute("currentYear", c.get(Calendar.YEAR));

        model=coustomerAnerlysisFacade.getCoustomerList(model,custId);
        model= coustomerAnerlysisFacade.getSalesService(model);
        model.addAttribute("monthValues", monthValues);


        return "customer";
    }

    @GetMapping(path="/user/addCustomer")
    public String handleAddCustomerDisplay(ModelMap model) {

        if (!model.containsAttribute("username")) {
            return "redirect:/";
        }

        return "addCustomer";
    }

    @PostMapping(path="/user/registerCustomer")
    public String handleRegisterCustomer(ModelMap model, @RequestParam String custName, @RequestParam(value = "custBusiness", required = false) String custBusiness, @RequestParam(value = "custAddress", required = false) String custAddress) {

        if (!model.containsAttribute("username")) {
            return "redirect:/";
        }
        coustomerAnerlysisFacade.addCoustomer(custName,custBusiness,custAddress);
        return "redirect:/user/dashboard";
    }
}
