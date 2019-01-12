package com.salesAnalyst.version1.SalesAnalyst.serviceFacades;

import com.salesAnalyst.version1.SalesAnalyst.services.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
@Component
public class ProductServiceFacade {
    @Autowired
    SalesService salesService;

    public ModelMap getProductInfo(ModelMap model,String productId) {


        Long[] totalMonthlySales = salesService.getSaleValues(2017);
        Long[] monthlyProductSales = salesService.getSalesByProduct(2017,"1");
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

        model.addAttribute("notApplicable", "N/A");



        return model;
    }

}
