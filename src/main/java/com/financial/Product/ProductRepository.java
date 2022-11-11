package com.financial.Product;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, Integer>{
	
	
	public List<Product> findProductByuserid(String userid); 

}
