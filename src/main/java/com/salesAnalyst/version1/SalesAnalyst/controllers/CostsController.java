package com.salesAnalyst.version1.SalesAnalyst.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("username")
public class CostsController {

    @Value("${app.timezone}")
    private String timezone;

    @Value("${app.months}")
    private String months;

    @GetMapping(path="/user/costs")
    public String handleCostsViewDisplay(ModelMap model) {

        if (!model.containsAttribute("username")) {
            return "redirect:/";
        }

        return "costs";
    }

    @PostMapping(path="/user/recordCost")
    public String handleCostRecord(ModelMap model, @RequestParam(value = "costReqId", required = false) String costReqId, @RequestParam String costValue, @RequestParam String costDate) {

        if (!model.containsAttribute("username")) {
            return "redirect:/";
        }

        String[] date = costDate.split("-");
        String day = date[0];
        String month = date[1];
        String year = date[2];

        // @todo obtain these elements and add a new entry to the cost table

        return "redirect:/user/dashboard";
    }

    @GetMapping(path="/user/addCostBudget")
    public String handleBudgetedCostViewDisplay(ModelMap model) {

        if (!model.containsAttribute("username")) {
            return "redirect:/";
        }

        return "addCostBudget";
    }

    @PostMapping(path="/user/saveCostBudget")
    public String handleSaveCostBudget(ModelMap model, @RequestParam(value = "all", required = false) String all, @RequestParam(value = "jan", required = false) String jan,
                                        @RequestParam(value = "feb", required = false) String feb, @RequestParam(value = "mar", required = false) String mar,
                                        @RequestParam(value = "apr", required = false) String apr, @RequestParam(value = "may", required = false) String may,
                                        @RequestParam(value = "jun", required = false) String jun, @RequestParam(value = "jul", required = false) String jul,
                                        @RequestParam(value = "aug", required = false) String aug, @RequestParam(value = "sep", required = false) String sep,
                                        @RequestParam(value = "oct", required = false) String oct, @RequestParam(value = "nov", required = false) String nov,
                                        @RequestParam(value = "dec", required = false) String dec, @RequestParam String CostBudget) {

        if (!model.containsAttribute("username")) {
            return "redirect:/";
        }

        // if user has ticked the checkbox the value will be "on"
        // else value will be null
        // always values will be set to the current years months
        // inserts and updates both should be possible

        // @todo obtain these elements and add a new entry to the budgeted costs table

        return "redirect:/user/dashboard";
    }
}
