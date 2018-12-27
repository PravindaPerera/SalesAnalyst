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
public class CostsController {

    @Value("${app.timezone}")
    private String timezone;

    @Value("${app.months}")
    private String months;

    @GetMapping(path="/user/costs") // Map ONLY GET Requests with the path /user/customer
    public String handleSalesViewDisplay(ModelMap model) {

        if (!model.containsAttribute("username")) {
            return "redirect:/";
        }

        return "costs";
    }

    @PostMapping(path="/user/recordCost")
    public String handleUserLogin(@RequestParam(value = "costReqId", required = false) String costReqId, @RequestParam String costValue, @RequestParam String costDate) {

        String[] date = costDate.split("-");
        String day = date[0];
        String month = date[1];
        String year = date[2];

        // @todo obtain these elements and add a new entry to the cost table

        return "redirect:/user/dashboard";
    }
}
