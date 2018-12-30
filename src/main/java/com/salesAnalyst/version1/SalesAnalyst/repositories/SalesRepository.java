package com.salesAnalyst.version1.SalesAnalyst.repositories;

import com.salesAnalyst.version1.SalesAnalyst.entities.Sales;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesRepository extends CrudRepository<Sales, Long> {

    @Query(value = "SELECT SUM(c.slaeValue)  FROM com.salesAnalyst.version1.SalesAnalyst.entities.Sales c WHERE c.year=:year GROUP BY c.month ORDER BY c.month" )
    List<Long> findByYear(@Param("year")int year);


    @Query(value = "SELECT SUM(c.cost)  FROM com.salesAnalyst.version1.SalesAnalyst.entities.Sales c WHERE c.year=:year GROUP BY c.month ORDER BY c.month" )
    List<Long> findCostByYear(@Param("year")int year);

    @Query(value = "SELECT SUM(c.slaeValue)  FROM com.salesAnalyst.version1.SalesAnalyst.entities.Sales c WHERE c.year=:year AND c.customer=:cust_id GROUP BY c.month ORDER BY c.month" )
    List<Long> findByYearByCousAndCustomer(@Param("year")int year,@Param("cust_id")int customerId);
    @Query(value = "SELECT SUM(c.slaeValue)  FROM com.salesAnalyst.version1.SalesAnalyst.entities.Sales c WHERE c.year=:year GROUP BY c.Product ORDER BY c.Product" )
    List<Long> findByYearProductSales(@Param("year")int year);
    @Query(value = "SELECT SUM(c.slaeValue)  FROM com.salesAnalyst.version1.SalesAnalyst.entities.Sales c WHERE c.year=:year AND c.customer=:cust_id GROUP BY c.Product ORDER BY c.Product" )
    List<Long> findByYearProductSalesAndCustomer(@Param("year")int year,@Param("cust_id")int customerId);
    @Query(value = "SELECT SUM(c.slaeValue)  FROM com.salesAnalyst.version1.SalesAnalyst.entities.Sales c WHERE c.year=:year GROUP BY c.customer ORDER BY c.customer" )
    List<Long> findByYearCoustomerSales(@Param("year")int year);
    @Query(value = "SELECT SUM(c.slaeValue)  FROM com.salesAnalyst.version1.SalesAnalyst.entities.Sales c WHERE c.year=:year AND c.customer=:cust_id GROUP BY c.customer ORDER BY c.customer" )
    List<Long> findByYearCoustomerSalesAndCustomer(@Param("year")int year);
}
