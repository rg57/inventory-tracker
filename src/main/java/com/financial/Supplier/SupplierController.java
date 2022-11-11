package com.financial.Supplier;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.financial.Customer.Customer;
import com.financial.Login.Login;
import com.financial.Login.LoginRepo;
import com.financial.Product.Product;
import com.financial.Product.ProductRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping
("/supplier")
public class SupplierController {

	@Autowired
	SupplierRepo supplierDBA;
	@Autowired
	ProductRepository productRepo;
	@Autowired
	SequenceGeneratorService service;
	@Autowired
	LoginRepo loginrepo;

	//Add new Supplier
	@PostMapping("/add")
	public String AddSupplier(@RequestBody Supplier supplier)
	{
		if(!(supplier==null) && supplier.getSupplierName()!=null && !supplier.getSupplierName().isEmpty() &&
				supplier.getSupplierProductName()!=null && !supplier.getSupplierProductName().isEmpty())
		{

			Login login = loginrepo.findLoginByisactive(true);
			if(login!=null && login.isIsactive())
			{
				supplier.setUserid(login.getUserId());
			}
			supplier.setId((int) service.getNextSequence("SUPPLIER_SEQUENCE_NAME"));
			supplierDBA.save(supplier);

			return "success";
		}
		return null;
	}

	@GetMapping("/view")
	public List<Supplier> ViewSupplierList()
	{
		List<Supplier> suppliersbyuserid = new ArrayList<>();
		Login login = loginrepo.findLoginByisactive(true);
		if(login!=null && login.isIsactive())
		{
			if((supplierDBA!=null))
			{
				suppliersbyuserid = supplierDBA.findSupplierByuserid(login.getUserId());
				return suppliersbyuserid;
			}
		}
		return null;
	}

	@GetMapping("/view/{name}")
	public Supplier ViewSupplierByName(@PathVariable("name") String suppliername)
	{
		int index=0;
		List<Supplier> supplierlist = ViewSupplierList();
		if(!supplierlist.isEmpty() && !suppliername.isEmpty())
		{
			for(Supplier sup : supplierlist)
			{
				if(suppliername.equals(sup.getSupplierName()))
				{
					return supplierlist.get(index);
				}
				index++;
			}
		}
		return null;
	}

	@PutMapping("/update/{name}")
	//	public String UpdateSupplierList(@PathVariable("name") String suppliername, @RequestBody Supplier supplier )
	public ResponseEntity<Supplier> UpdateSupplierList(@PathVariable("name") String suppliername, @RequestBody Supplier supplier )
	{
		List<Product> pros = productRepo.findAll();
		Supplier supplierDetailsByName = ViewSupplierByName(suppliername);
		if(supplierDetailsByName!=null)
		{
			if(supplier.getSupplierAddress()!=null && !supplier.getSupplierAddress().isEmpty())
			{
				supplierDetailsByName.setSupplierAddress(supplier.getSupplierAddress());
			}
			if(supplier.getSupplierContactNo()!=null && supplier.getSupplierContactNo()!=0)
			{
				supplierDetailsByName.setSupplierContactNo(supplier.getSupplierContactNo());
			}
			if(supplier.getSupplierEmail()!=null && !supplier.getSupplierEmail().isEmpty())
			{
				supplierDetailsByName.setSupplierEmail(supplier.getSupplierEmail());
			}
			if(supplier.getSupplierName()!=null && !supplier.getSupplierName().isEmpty())
			{
				supplierDetailsByName.setSupplierName(supplier.getSupplierName());
			}
			if(supplier.getBuyingCost()!=null && supplier.getBuyingCost()!=0)
			{
				//				for(Product pro : pros )
				//				{
				//					if(pro.getPname()==supplier.getSupplierProductName())
				//					{
				//						pro.setPbuyingcost(supplier.getSupplierProductCost());
				//					}
				//				}

				supplierDetailsByName.setBuyingCost(supplier.getBuyingCost());
			}
			if(supplier.getSupplierProductName()!=null && !supplier.getSupplierProductName().isEmpty())
			{
				supplierDetailsByName.setSupplierProductName(supplier.getSupplierProductName());
			}
			if(supplier.getSupplierProductQuantity()!=null && supplier.getSupplierProductQuantity()!=0)
			{				
				//				for(Product pro : pros )
				//				{
				//					if(pro.getPname()==supplier.getSupplierProductName())
				//					{
				//						pro.setPquantity(supplier.getSupplierProductQuantity());
				//					}
				//				}
				supplierDetailsByName.setSupplierProductQuantity(supplier.getSupplierProductQuantity());
			}
			if(supplier.getDateOfPurchase()!=null)
			{
				supplierDetailsByName.setDateOfPurchase(supplier.getDateOfPurchase());
			}
			if(supplier.getTaxRate()!=null && supplier.getTaxRate().isEmpty())
			{
				supplierDetailsByName.setTaxRate(supplier.getTaxRate());
			}
			return new ResponseEntity<>(supplierDBA.save(supplierDetailsByName),HttpStatus.OK);
			//			return "Successfully updated the details of supplier =" + supplier.getSupplierName();
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		//		return "Details not updated due to some error";
	}

	@DeleteMapping("/delete/{name}")
	public String DeleteSupplierDetails(@PathVariable("name") String supplierName)
	{
		Supplier sup = ViewSupplierByName(supplierName);
		if(sup!=null)
		{
			supplierDBA.deleteById(sup.getId());
			return "success delete " + sup.getSupplierName();
		}
		return "data not deleted due to error";
	}

}
