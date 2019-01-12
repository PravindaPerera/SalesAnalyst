package com.salesAnalyst.version1.SalesAnalyst.controllers;

import com.salesAnalyst.version1.SalesAnalyst.serviceFacades.ProductServiceFacade;
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
public class ProductController {
    @Autowired
    ProductServiceFacade productServiceFacade;
    @Value("${app.timezone}")
    private String timezone;

    @Value("${app.months}")
    private String months;

    @GetMapping(path="/user/product") // Map ONLY GET Requests with the path /user/product
    public String handleProductViewDisplay(ModelMap model, @RequestParam(value = "prodId", required = false) String prodId) {

        String[] monthValues = months.split(",");

        if (!model.containsAttribute("username")) {
            return "redirect:/";
        }

        // @todo get the product list from db and set the 1st product of the list to selectedProduct by default
        model=productServiceFacade.getProductsMetaData(model,prodId);

        // current year for customers timezone needs to be obtained
        java.util.TimeZone tz = java.util.TimeZone.getTimeZone(timezone);
        java.util.Calendar c = java.util.Calendar.getInstance(tz);
        model=productServiceFacade.getProductInfo(model,prodId);
        model.addAttribute("currentYear", c.get(Calendar.YEAR));
        model.addAttribute("monthValues", monthValues);
        model.addAttribute("productName", "Product A");


        return "product";
    }

    @GetMapping(path="/user/addProduct")
    public String handleAddProductDisplay(ModelMap model) {

        if (!model.containsAttribute("username")) {
            return "redirect:/";
        }

        return "addProduct";
    }

    @PostMapping(path="/user/registerProduct")
    public String handleRegisterProduct(ModelMap model, @RequestParam String prodName) {

        if (!model.containsAttribute("username")) {
            return "redirect:/";
        }
        productServiceFacade.addProduct(prodName);
        return "redirect:/user/dashboard";
    }
}
