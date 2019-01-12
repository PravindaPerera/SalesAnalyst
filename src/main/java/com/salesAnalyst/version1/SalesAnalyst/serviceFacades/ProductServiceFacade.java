package com.salesAnalyst.version1.SalesAnalyst.serviceFacades;

import com.salesAnalyst.version1.SalesAnalyst.entities.Product;
import com.salesAnalyst.version1.SalesAnalyst.services.ProductService;
import com.salesAnalyst.version1.SalesAnalyst.services.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductServiceFacade {
    @Autowired
    SalesService salesService;
    @Autowired
    ProductService productService;

    public ModelMap getProductInfo(ModelMap model,String productId) {


        Double[] totalMonthlySales = salesService.getSaleValues(2017);
        Long[] monthlyProductSales = salesService.getSalesByProduct(2017,"1");
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


    public ModelMap getProductsMetaData(ModelMap model,String prodId){
        List<Product> products=productService.getProducts();
        List<String> productNames = productService.getProducts().stream().
                map(Product::getName).
                collect(Collectors.toList());
        List<String> productIds =  productService.getProducts().stream().
                map(s->Integer.toString(s.getProductId())).
                collect(Collectors.toList());

        Product selectedProduct = products.get(0);

        if (prodId != null) {
            selectedProduct = products.get(productIds.indexOf(prodId));
        }

        model.addAttribute("productNames", productNames);
        model.addAttribute("productIds", productIds);
        model.addAttribute("selectedProduct", selectedProduct);
        return model;
    }

    public void addProduct(String prodName){
        productService.addProduct(prodName);
    }

}
