package com.financial.Inventory;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.financial.Customer.Customer;
import com.financial.Customer.CustomerRepository;
import com.financial.Product.Product;
import com.financial.Product.ProductRepository;
import com.financial.Supplier.Supplier;
import com.financial.Supplier.SupplierRepo;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/inventory")
public class InventoryController {

	@Autowired
	CustomerRepository customerrepo;
	@Autowired
	ProductRepository productrepo;
	@Autowired
	SupplierRepo supplierrepo;
	@Autowired
	InventoryRepo inventoryrepo;

	@GetMapping("/view")
	public List<ProductInventoryDetails> viewInventory() {

		List<Product> productlist = productrepo.findAll();
		List<Supplier> supplierlist = supplierrepo.findAll();
		List<Customer> customerlist = customerrepo.findAll();

		Long sellquantity = 0L , buyquantity=0L ,totalquantity =0L;

		inventoryrepo.deleteAll();

		if (!productlist.isEmpty()) {
			for (int i = 0; i < productlist.size(); i++) {
				ProductInventoryDetails productdetails = new ProductInventoryDetails();
				Product product = productlist.get(i);
				if (!product.getPname().isEmpty()) {
					productdetails.setProductName(product.getPname());
					productdetails.setProductType(product.getPtype());
					productdetails.setProductCategory(product.getPcategories());


					if (!customerlist.isEmpty()) {

						for (int j = 0; j < customerlist.size(); j++) {
							Customer customer = customerlist.get(j);
							if (customer.getCustomerProductName() != null)
								if (customer.getCustomerProductName().equals(product.getPname())) {
									productdetails.setSellingCostOfProduct(customer.getSellingCost());
									sellquantity += customer.getCustomerProductQuantity();

									//									totalquantity=sellquantity;
									//									productdetails.setAvailableQuantity(totalquantity);
								}
						}
					}

					if (!supplierlist.isEmpty()) {

						for (int k = 0; k < supplierlist.size(); k++) {
							Supplier supplier = supplierlist.get(k);

							if (supplier.getSupplierProductName() != null)
								if (supplier.getSupplierProductName().equals(product.getPname())) {
									productdetails.setBuyingcostOfProduct(supplier.getBuyingCost());
									productdetails.setDateOfPurchase(supplier.getDateOfPurchase());
									buyquantity+= supplier.getSupplierProductQuantity();

									if(sellquantity!=0) {
										totalquantity = buyquantity - sellquantity;
									}
									else
										totalquantity=buyquantity;

									productdetails.setAvailableQuantity(totalquantity);


								}
						}
					}

					//					}
				}
				inventoryrepo.save(productdetails);
			}

			return inventoryrepo.findAll();
		}
		return null;

	}
}
