package com.financial.Supplier;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:3000")
public interface SupplierRepo extends MongoRepository<Supplier, Integer>{
	
	public List<Supplier> findSupplierByuserid(String userid);

}
