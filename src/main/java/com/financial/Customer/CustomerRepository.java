package com.financial.Customer;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.financial.Product.Product;

public interface CustomerRepository extends MongoRepository<Customer, Integer> {
	
	
	// work as find<entityname>By<fieldname>
	public List<Customer> findCustomerByuserid(String userid); 

}
