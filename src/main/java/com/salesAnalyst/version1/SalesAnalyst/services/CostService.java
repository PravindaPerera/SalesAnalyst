package com.salesAnalyst.version1.SalesAnalyst.services;

import com.salesAnalyst.version1.SalesAnalyst.repositories.CostReposiry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CostService {

    @Autowired
    CostReposiry reposiry;

    public Long[]getCOstByMonth(int year){
        List<Long> cost=reposiry.findCostByMonth(year);
        Long[] integerArray=new Long[cost.size()];
        int i=0;
        for (Long saleValue : cost){
            integerArray[i]=saleValue;
            i++;
        }

        return  integerArray;
    }
}
