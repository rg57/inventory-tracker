package com.financial.Product;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.financial.SequenceGeneratorService;
import com.financial.Login.Login;
import com.financial.Login.LoginRepo;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductRepository product_repo;

	@Autowired
	private SequenceGeneratorService service;
	
	@Autowired
	private LoginRepo loginrepo;

	// Add New Products
	@PostMapping("/add")
	public Product saveProduct(@RequestBody Product product) {
				
		if (!(product.getPname()==null) && !product.getPname().isBlank() &&
				!(product.getPtype()==null) && !product.getPtype().isBlank() &&
					!(product.getPcategories()==null) && !product.getPcategories().isBlank())
		{
			Login login = loginrepo.findLoginByisactive(true);
			if(login!=null && login.isIsactive())
			{
				product.setPuserid(login.getUserId());
			}
			product.setId((int) service.getNextSequence("PRODUCT_SEQUENCE_NAME"));
			return product_repo.save(product);
		}

		return null;
	}

	// View Full List of Products
	@GetMapping("/get")
	public List<Product> viewProduct() {
		
		List<Product> productbyuserid = new ArrayList<>();
		Login login = loginrepo.findLoginByisactive(true);
		if(login!=null && login.isIsactive())
		{
			productbyuserid = product_repo.findProductByuserid(login.getUserId());
			return productbyuserid;
//			return product_repo.findAll();
		}
		return null;
	}

	// Retrieve Product By Product Name
	@GetMapping("/get/{name}")
	public Product productbyname(@PathVariable("name") String pname) {
		int index = 0;
		List<Product> pro = viewProduct();
		if (!pname.isEmpty()) {
			for (Product pros : pro) {
				String name = pros.getPname();
				if (name != null) {
					if (name.equals(pname)) {
						return pro.get(index);
					}
				}
				index++;
			}
		}
		return null;
	}

	// Update Product details by Product Name

	@PutMapping("/update/{name}")
	public ResponseEntity<Product> updateProductDetails(@PathVariable("name") String pname,
			@RequestBody Product product) {

		Product productupdate = productbyname(pname);
		if (!(productupdate == null)) {
			if (product.getPname() != null && !product.getPname().isEmpty())
				productupdate.setPname(product.getPname());
			if (product.getPtype() !=null && !product.getPtype().isEmpty())
				productupdate.setPtype(product.getPtype());
			if (product.getPcategories() !=null && !product.getPcategories().isEmpty())
				productupdate.setPcategories(product.getPcategories());
				
			return new ResponseEntity<>(product_repo.save(productupdate), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// //Update Product details by ID
	// @PutMapping("/update/{id}")
	// public ResponseEntity<Product> updateProductDetails(@PathVariable("id") int
	// pid , @RequestBody Product product)
	// {
	// Optional<Product> toBeModifyProduct = product_repo.findById(pid);
	// if(toBeModifyProduct.isPresent())
	// {
	// Product productEntity = toBeModifyProduct.get();
	// if(product.getPname() !=null && !product.getPname().isEmpty())
	// productEntity.setPname(product.getPname());
	// return new ResponseEntity<>(product_repo.save(productEntity),HttpStatus.OK);
	// }
	// else
	// {
	// return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	// }
	// }

	// Delete product by Product Name
	@DeleteMapping("/delete/{name}")
	public String deleteproductdetails(@PathVariable("name") String pname) {
		Product productdelete = productbyname(pname);

		if (!(productdelete == null)) {
			int id = productdelete.getId();
			product_repo.deleteById(id);
			return ("success");
		}
		return ("product not found");

	}

}
