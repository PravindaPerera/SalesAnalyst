package com.salesAnalyst.version1.SalesAnalyst.serviceFacades;

import com.salesAnalyst.version1.SalesAnalyst.services.OrganizationService;
import com.salesAnalyst.version1.SalesAnalyst.services.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

@Component
public class SaleserviceFacade {
    @Autowired
    OrganizationService organizationService;
    @Autowired
    SalesService salesService;

    public ModelMap getSalesService(ModelMap model) {
        model = organizationService.getOrganizationDetails(model);

        Double[] totalMonthlySales =salesService.getSaleValues(2017);
//        Long[] coustomerSales=salesService.getSalesByCoustomer(2017);
//        Long[] productSales=salesService.getSalesByProduct(2017);
        // @todo: Take this from db
        // array of company total sales each month
        // array of monthly sales of the customer
        Long[] customerMonthlySales = salesService.getSalesValuesByCoustomer(2017,1);
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
        Long[] customerProductSales = salesService.getProductSalesForCOustomer(2017,1);
        // product names of the company
        String[] productNames = new String[] {"Product1", "Product2"};
        // colour codes for each product
        String[] colourCodes = new String[] {"#26B99A", "#26B9B9", "#268AB9", "#264CB9", "#5326B9", "#8A26B9"};

        model.addAttribute("totalMonthlySales", totalMonthlySales);
        model.addAttribute("customerMonthlySales", customerMonthlySales);
        model.addAttribute("customerProductSales", customerProductSales);
        model.addAttribute("productNames", productNames);
        model.addAttribute("colourCodes", colourCodes);
        model.addAttribute("salesPropotion", salesPropotion);
        model.addAttribute("notApplicable", "N/A");

        return model;
    }


    public ModelMap getDashBoardModel(ModelMap model,int year,int month){

        // array of year sales and costs in each individual month
        // sales values for a given year per month
        Double[] monthlySales = salesService.getSaleValues(year);
        // cost values for a given year per month
        Long[] monthlyCosts=salesService.getCostByMonth(year);
        model.addAttribute("actualSalesValue", monthlySales[month]);
        // @todo need to take form db
        model.addAttribute("budgetedSalesValue", 70);
        model.addAttribute("actualCostValue", monthlyCosts[month]);
        // @todo need to take form db
        model.addAttribute("budgetedCostValue", 50);

        // array of year budgeted sales and budgeted costs in each individual month
        int[] monthlyBudgetedSales = new int[] {10, 20, 12, 50, 60, 30, 20, 10, 13, 27, 0, 0};
        int[] monthlyBudgetedCosts = new int[] {6, 8, 15, 9, 9, 20, 15, 12, 14, 20, 0, 0};

        int totalSales = 0;
        int totalCosts = 0;
        int totalBudgetedSales = 0;
        int totalBudgetedCosts = 0;
        for (Double salesValue : monthlySales)
        {
            totalSales += salesValue;
        }
        for (Long costValue : monthlyCosts)
        {
            totalCosts += costValue;
        }
        for (int budgetedSaleValue : monthlyBudgetedSales)
        {
            totalBudgetedSales += budgetedSaleValue;
        }
        for (int budgetedCostValue : monthlyBudgetedCosts)
        {
            totalBudgetedCosts += budgetedCostValue;
        }
        model.addAttribute("monthlySales", monthlySales);
        model.addAttribute("monthlyCosts", monthlyCosts);


        model.addAttribute("totalSales", totalSales);
        model.addAttribute("totalCosts", totalCosts);
        model.addAttribute("totalBudgetedSales", totalBudgetedSales);
        model.addAttribute("totalBudgetedCosts", totalBudgetedCosts);


        model.addAttribute("monthlySales", monthlySales);
        model.addAttribute("monthlyCosts", monthlyCosts);
       return model;

    }
}
