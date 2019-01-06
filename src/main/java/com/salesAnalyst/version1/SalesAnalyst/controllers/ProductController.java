package com.salesAnalyst.version1.SalesAnalyst.controllers;

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

        // @todo new - check on what should be moved to facade
        // @todo get the product list from db and set the 1st product of the list to selectedProduct by default
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

        String selectedProduct = productNames.get(0);

        if (prodId != null) {
            selectedProduct = productNames.get(productIds.indexOf(prodId));
        }

        // current year for customers timezone needs to be obtained
        java.util.TimeZone tz = java.util.TimeZone.getTimeZone(timezone);
        java.util.Calendar c = java.util.Calendar.getInstance(tz);

        model.addAttribute("currentYear", c.get(Calendar.YEAR));

        // product details
        model.addAttribute("productName", "Product A");

        // @todo: Take this from db
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
        model.addAttribute("productNames", productNames);
        model.addAttribute("productIds", productIds);
        model.addAttribute("selectedProduct", selectedProduct);
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

        // @todo: Add new product to product table

        return "redirect:/user/dashboard";
    }
}
