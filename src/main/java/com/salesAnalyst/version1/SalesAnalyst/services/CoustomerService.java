package com.salesAnalyst.version1.SalesAnalyst.services;

import com.salesAnalyst.version1.SalesAnalyst.entities.Customer;
import com.salesAnalyst.version1.SalesAnalyst.repositories.CoustomerRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class CoustomerService {
    @Autowired
    CoustomerRepositry coustomerRepositry;
    public List<Customer> getCoustomers(){
        return StreamSupport.stream(coustomerRepositry.
                findAll().spliterator(), false)
                .collect(Collectors.toList());
    }


    public  void addCustomer( String custName,String custBusiness,String custAddress){
        Customer customer=new Customer();
        customer.setName(custName);
        customer.setCategory(custBusiness);
        customer.setAddress(custAddress);
        coustomerRepositry.save(customer);

    }

}
