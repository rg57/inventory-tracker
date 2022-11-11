package com.financial.Product;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ProductServicesImp implements ProductServices{

	@Autowired
	private ProductRepository productrepository;
	
	List<Product> productDetails;
	
//	@Override
//	public List<Product> saveProduct(@RequestBody Product product) {
//		// TODO Auto-generated method stub
//		
//		Product save = this.productrepository.save(product);
//		productDetails.add(save);
//		return productDetails;
//	}

//	@Override
//	public ResponseEntity<Product> getProductList() {
//		// TODO Auto-generated method stub
//		return ResponseEntity.ok(this.productrepository.findAll());
//	}
	
//	@Override
//	public List<Product> saveProduct() {
//		// TODO Auto-generated method stub
//		
//		
//		return null;
//	}
	
	
//	@Override
//	public ResponseEntity<List<Product>> getProductList() {
//		// TODO Auto-generated method stub
//		productDetails = new ArrayList<>();
////		Product pr = new Product(2, "orange", 5L, 1, 50L, 25L);
////		List.of( new Product(1, "Apple", 12L, 1, 30L, 20L));
////		productDetails.add(pr);
//		
//		return ResponseEntity<List<Product>>(productDetails, HttpStatus.OK);
//					
//	}




	
	
	

}
