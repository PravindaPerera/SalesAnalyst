package com.salesAnalyst.version1.SalesAnalyst.repositories;

import org.springframework.data.repository.CrudRepository;

import com.salesAnalyst.version1.SalesAnalyst.entities.Users;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UsersRepository extends CrudRepository<Users, Long>{
    Users findByUsernameAndPassword(String username, String password);
}
