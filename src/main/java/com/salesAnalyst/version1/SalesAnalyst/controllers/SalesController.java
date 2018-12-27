package com.salesAnalyst.version1.SalesAnalyst.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.ArrayList;

@Controller
@SessionAttributes("username")
public class SalesController {

    @Value("${app.timezone}")
    private String timezone;

    @Value("${app.months}")
    private String months;

    @GetMapping(path="/user/sales") // Map ONLY GET Requests with the path /user/customer
    public String handleSalesViewDisplay(ModelMap model) {

        if (!model.containsAttribute("username")) {
            return "redirect:/";
        }

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

        ArrayList<String> productNames = new ArrayList<String>();
        productNames.add("Product 1");
        productNames.add("Product 2");
        productNames.add("Product 3");
        productNames.add("Product 4");
        ArrayList<String> productIds = new ArrayList<String>();
        productIds.add("111");
        productIds.add("222");
        productIds.add("333");
        productIds.add("444");

        model.addAttribute("productNames", productNames);
        model.addAttribute("productIds", productIds);
        model.addAttribute("customerNames", customerNames);
        model.addAttribute("customerIds", customerIds);

        return "sales";
    }

    @PostMapping(path="/user/recordSale") // Map ONLY POST Requests with the path /login
    public String handleUserLogin(@RequestParam(value = "salesReqId", required = false) String salesReqId, @RequestParam(value = "customerId", required = false) String customerId,
                                  @RequestParam(value = "productId", required = false) String productId, @RequestParam String saleValue, @RequestParam String salesDate) {

        String[] date = salesDate.split("/");
        String day = date[1];
        String month = date[0];
        String year = date[2];

        // @todo obtain these elements and add a new entry to the sales table

        return "redirect:/user/dashboard";
    }
}
