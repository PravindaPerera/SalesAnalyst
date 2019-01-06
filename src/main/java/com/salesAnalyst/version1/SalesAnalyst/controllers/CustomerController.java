package com.salesAnalyst.version1.SalesAnalyst.controllers;

import com.salesAnalyst.version1.SalesAnalyst.serviceFacades.SaleserviceFacade;
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
    SaleserviceFacade saleserviceFacade;

    @GetMapping(path="/user/customer") // Map ONLY GET Requests with the path /user/customer
    public String handleCustomerViewDisplay(ModelMap model, @RequestParam(value = "custId", required = false) String custId) {

        String[] monthValues = months.split(",");

        if (!model.containsAttribute("username")) {
            return "redirect:/";
        }
        // @todo new - check on what should be moved to facade

        // @todo get the customer list from db and set the 1st customer of the list to selectedCustomer by default
        ArrayList<String> customerNames = new ArrayList<String>();
        customerNames.add("Customer 1");
        customerNames.add("Customer 2");
        customerNames.add("Customer 3");
        customerNames.add("Customer 4");
        ArrayList<String> customerIds = new ArrayList<String>();
        customerIds.add("111");
        customerIds.add("222");
        customerIds.add("333");
        customerIds.add("444");

        String selectedCustomer = customerNames.get(0);

        if (custId != null) {
            selectedCustomer = customerNames.get(customerIds.indexOf(custId));
        }

        // current year for customers timezone needs to be obtained
        java.util.TimeZone tz = java.util.TimeZone.getTimeZone(timezone);
        java.util.Calendar c = java.util.Calendar.getInstance(tz);

        model.addAttribute("currentYear", c.get(Calendar.YEAR));

        // @todo: Take this from db
        // customer details
        model.addAttribute("customerName", "TGIF Colombo");
        model.addAttribute("customerBusiness", "Restaurant Management");
        model.addAttribute("customerAddress", "555A, Canal Row, Colombo 01");
        model.addAttribute("customerNames", customerNames);
        model.addAttribute("customerIds", customerIds);
        model.addAttribute("selectedCustomer", selectedCustomer);
        model.addAttribute("monthValues", monthValues);

        model = saleserviceFacade.getSalesService(model);
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

        // @todo: Add new customer to customer table
        // @todo: Verify how the custAddress is added (with or without new lines)

        return "redirect:/user/dashboard";
    }
}
