package com.salesAnalyst.version1.SalesAnalyst.controllers;

import com.salesAnalyst.version1.SalesAnalyst.serviceFacades.CoustomerAnerlysisFacade;
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

@Controller
@SessionAttributes("username")
public class SalesController {

    @Value("${app.timezone}")
    private String timezone;

    @Value("${app.months}")
    private String months;
@Autowired
    ProductServiceFacade productServiceFacade;
@Autowired
    CoustomerAnerlysisFacade coustomerAnerlysisFacade;
    @GetMapping(path="/user/sales")
    public String handleSalesViewDisplay(ModelMap model) {

        if (!model.containsAttribute("username")) {
            return "redirect:/";
        }

        // @todo get the customer list from db and set the 1st customer of the list to selectedCustomer by default
       model=productServiceFacade.getProductInfo(model,null);
        model=coustomerAnerlysisFacade.getCoustomerList(model,null);





        return "sales";
    }

    @PostMapping(path="/user/recordSale")
    public String handleRecordSale(ModelMap model, @RequestParam(value = "salesReqId", required = false) String salesReqId, @RequestParam(value = "customerId", required = false) String customerId,
                                  @RequestParam(value = "productId", required = false) String productId, @RequestParam String saleValue, @RequestParam String salesDate) {

        if (!model.containsAttribute("username")) {
            return "redirect:/";
        }

        String[] date = salesDate.split("-");
        String day = date[0];
        String month = date[1];
        String year = date[2];

        // @todo obtain these elements and add a new entry to the sales table

        return "redirect:/user/dashboard";
    }

    @GetMapping(path="/user/addSalesBudget")
    public String handleBudgetedSalesViewDisplay(ModelMap model) {

        if (!model.containsAttribute("username")) {
            return "redirect:/";
        }

        return "addSalesBudget";
    }

    @PostMapping(path="/user/saveSalesBudget")
    public String handleSaveSalesBudget(ModelMap model, @RequestParam(value = "all", required = false) String all, @RequestParam(value = "jan", required = false) String jan,
                                  @RequestParam(value = "feb", required = false) String feb, @RequestParam(value = "mar", required = false) String mar,
                                        @RequestParam(value = "apr", required = false) String apr, @RequestParam(value = "may", required = false) String may,
                                        @RequestParam(value = "jun", required = false) String jun, @RequestParam(value = "jul", required = false) String jul,
                                        @RequestParam(value = "aug", required = false) String aug, @RequestParam(value = "sep", required = false) String sep,
                                        @RequestParam(value = "oct", required = false) String oct, @RequestParam(value = "nov", required = false) String nov,
                                        @RequestParam(value = "dec", required = false) String dec, @RequestParam String SalesBudget) {

        if (!model.containsAttribute("username")) {
            return "redirect:/";
        }

        // if user has ticked the checkbox the value will be "on"
        // else value will be null
        // always values will be set to the current years months
        // inserts and updates both should be possible

        // @todo obtain these elements and add a new entry to the budgeted sales table

        return "redirect:/user/dashboard";
    }
}
