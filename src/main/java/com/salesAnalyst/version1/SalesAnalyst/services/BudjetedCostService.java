package com.salesAnalyst.version1.SalesAnalyst.services;

import com.salesAnalyst.version1.SalesAnalyst.repositories.BudjetedCostRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BudjetedCostService {
    @Autowired
    BudjetedCostRepositry budjetedCostRepositry;



}
