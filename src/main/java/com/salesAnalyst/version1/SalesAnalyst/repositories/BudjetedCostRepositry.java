package com.salesAnalyst.version1.SalesAnalyst.repositories;

import com.salesAnalyst.version1.SalesAnalyst.entities.BudgetedCost;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BudjetedCostRepositry extends CrudRepository<BudgetedCost, Long> {

    @Query(value = "SELECT SUM(c.ammount)  FROM com.salesAnalyst.version1.SalesAnalyst.entities.BudgetedCost c WHERE c.year=:year GROUP BY c.month ORDER BY c.month" )
    List<java.lang.Long> findCostByMonth(@Param("year")int year);

    @Query(value = "SELECT SUM(c.ammount)  FROM com.salesAnalyst.version1.SalesAnalyst.entities.BudgetedCost c WHERE c.year=:year" )
    List<java.lang.Long> findCostForYear(@Param("year")int year);
}
