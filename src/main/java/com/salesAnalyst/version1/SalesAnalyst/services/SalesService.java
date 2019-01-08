package com.salesAnalyst.version1.SalesAnalyst.services;

import com.salesAnalyst.version1.SalesAnalyst.repositories.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
@Component
public class SalesService {
    @Autowired
    SalesRepository salesRepository;
    public Long[] getSaleValues(int year){
        List<Long> saleValues=salesRepository.findByYear(year);
        Long[] integerArray=new Long[saleValues.size()];
        int i=0;
     for (Long saleValue : saleValues){
         integerArray[i]=saleValue;
         i++;
     }

        return  integerArray;
    }


    public Long[] getSalesByProduct(int year,String prodId){
        //findByYearProductSales
        List<Long> saleValues=salesRepository.findByYearProductSalesForId(year,prodId);
        Long[] integerArray=new Long[saleValues.size()];
        int i=0;
        for (Long saleValue : saleValues){
            integerArray[i]=saleValue;
            i++;
        }

        return  integerArray;
    }

    public Long[] getSalesByCoustomer(int year){
        //findByYearCoustomerSales
        List<Long> saleValues=salesRepository.findByYearCoustomerSales(year);
        Long[] integerArray=new Long[saleValues.size()];
        int i=0;
        for (Long saleValue : saleValues){
            integerArray[i]=saleValue;
            i++;
        }

        return  integerArray;
    }

    public Long[] getSalesValuesByCoustomer(int year,int id){
        List<Long> saleValues=salesRepository.findByYearByCousAndCustomer(year,id);
        Long[] integerArray=new Long[saleValues.size()];
        int i=0;
        for (Long saleValue : saleValues){
            integerArray[i]=saleValue;
            i++;
        }

        return  integerArray;
    }

    public Long[] getProductSalesForCOustomer(int year,int id){
        List<Long> saleValues=salesRepository.findByYearProductSalesAndCustomer(year,id);
        Long[] integerArray=new Long[saleValues.size()];
        int i=0;
        for (Long saleValue : saleValues){
            integerArray[i]=saleValue;
            i++;
        }

        return  integerArray;
    }


    public Long[]getCOstByMonth(int year){
        List<Long> cost=salesRepository.findCostByYear(year);
        Long[] integerArray=new Long[cost.size()];
        int i=0;
        for (Long saleValue : cost){
            integerArray[i]=saleValue;
            i++;
        }

        return  integerArray;
    }
}
