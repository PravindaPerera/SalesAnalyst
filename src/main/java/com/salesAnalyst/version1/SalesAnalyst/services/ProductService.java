package com.salesAnalyst.version1.SalesAnalyst.services;

import com.salesAnalyst.version1.SalesAnalyst.entities.Customer;
import com.salesAnalyst.version1.SalesAnalyst.entities.Product;
import com.salesAnalyst.version1.SalesAnalyst.repositories.CoustomerRepositry;
import com.salesAnalyst.version1.SalesAnalyst.repositories.ProductRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class ProductService {


    @Autowired
    ProductRepositry productRepositry;
    public List<Product> getProducts(){
        return StreamSupport.stream(productRepositry.
                findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public void addProduct(String prodName){
        Product product=new Product();
        product.setName(prodName);
        productRepositry.save(product);

    }
}
