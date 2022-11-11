package com.financial.Customer;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
import com.financial.Product.Product;
import com.financial.Product.ProductRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping
("/customer")
public class CustomerController {

	@Autowired
	CustomerRepository customerRepo;
	@Autowired
	ProductRepository productRepo;
	@Autowired
	SequenceGeneratorService service;
	@Autowired
	LoginRepo loginrepo;

	

	//Add New Customer
	@PostMapping("/add")
	public String AddCustomer(@RequestBody Customer customer)
	{	
		int product_count=1;
		if((customer!=null) && customer.getCustomerName()!=null && !customer.getCustomerName().isEmpty() &&
				customer.getCustomerProductName()!=null && !customer.getCustomerProductName().isEmpty())
		{
			
			Login login = loginrepo.findLoginByisactive(true);
			if(login!=null && login.isIsactive())
			{
				customer.setUserid(login.getUserId());
			}
			customer.setId((int) service.getNextSequence("CUSTOMER_SEQUENCE_NAME"));
			customerRepo.save(customer);
			return "success";
		}
		return null;
	}
	
	
	//View Customer Details
	@GetMapping("/view")
	public List<Customer> ViewCustomer(){
		
		List<Customer> customersbyuserid = new ArrayList<>();
		Login login = loginrepo.findLoginByisactive(true);
		if(login!=null && login.isIsactive())
		{
			if(customerRepo!=null)
			{
				customersbyuserid = customerRepo.findCustomerByuserid(login.getUserId());
				return customersbyuserid;
			}
		}
		
		return null;
		
	}
	
	//find Customer Details by Name
	public Customer CustomerByName(@PathVariable("name") String customername)
	{
		int index=0;
		List<Customer> customerlist = ViewCustomer();
		if(!customerlist.isEmpty() && !customername.isEmpty())
		{
			for(Customer cus : customerlist )
			{
				if(customername.equals(cus.getCustomerName()))
				{
					return customerlist.get(index);
				}
				index++;
			}
		}
		return null;
		
	}
	
	//Update Customer Details
	@PutMapping("/update/{name}")
	public ResponseEntity<Customer> UpdateCustomer(@PathVariable("name") String customername , @RequestBody Customer customer )
	{
		Customer customedetailstoupadate = CustomerByName(customername);
		if(customedetailstoupadate!=null)
		{
			if(customer.getCustomerName()!=null && !customer.getCustomerName().isEmpty())
			{
				customedetailstoupadate.setCustomerName(customer.getCustomerName());
			}
			if(customer.getCustomerProductName()!=null && !customer.getCustomerProductName().isEmpty())
			{
				customedetailstoupadate.setCustomerProductName(customer.getCustomerProductName());
			}
			if(customer.getCustomerProductQuantity()!=null && customer.getCustomerProductQuantity()!=0)
			{
				customedetailstoupadate.setCustomerProductQuantity(customer.getCustomerProductQuantity());
			}
//			if(customer.getCustomerPurchasedProduct()!=null && !customer.getCustomerPurchasedProduct().isEmpty())
//			{
//				customedetailstoupadate.setCustomerPurchasedProduct(customer.getCustomerPurchasedProduct());
//			}
			if(customer.getSellingCost()!=null && customer.getSellingCost()!=0)
			{
				customedetailstoupadate.setSellingCost(customer.getSellingCost());
			}
			if(customer.getCustomerContactNo()!=null && customer.getCustomerContactNo()!=0)
			{
				customedetailstoupadate.setCustomerContactNo(customer.getCustomerContactNo());
			}
			if(customer.getCustomerEmail()!=null && !customer.getCustomerEmail().isEmpty())
			{
				customedetailstoupadate.setCustomerEmail(customer.getCustomerEmail());
			}
			if(customer.getCustomerAddress()!=null && !customer.getCustomerAddress().isEmpty())
			{
				customedetailstoupadate.setCustomerAddress(customer.getCustomerAddress());
			}
			if(customer.getDateOfSell()!=null)
			{
				customedetailstoupadate.setDateOfSell(customer.getDateOfSell());
			}
			
			return new ResponseEntity<Customer> (customerRepo.save(customedetailstoupadate) , HttpStatus.OK);
		}
		return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
		
	}
	
	@DeleteMapping("delete/{name}")
	public String DeleteCustomerDetails(@PathVariable("name") String customername)
	{
		Customer cus = CustomerByName(customername);
		if(cus!=null)
		{
			customerRepo.deleteById(cus.getId());
			return "success delete " + cus.getCustomerName();
		}
		return "error to delete";
		
	}

}
