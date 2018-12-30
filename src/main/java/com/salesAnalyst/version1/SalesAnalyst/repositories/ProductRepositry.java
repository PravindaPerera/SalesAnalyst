package com.salesAnalyst.version1.SalesAnalyst.repositories;

import com.salesAnalyst.version1.SalesAnalyst.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepositry extends CrudRepository<Product,Long> {
}
