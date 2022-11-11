package com.financial.Reports;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.financial.Customer.Customer;
import com.financial.Customer.CustomerRepository;
import com.financial.Inventory.InventoryRepo;
import com.financial.Product.Product;
import com.financial.Product.ProductRepository;
import com.financial.Supplier.Supplier;
import com.financial.Supplier.SupplierRepo;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/reports")
public class ReportsController {

	Long purchaseQuantity = 0L, sellQuantity = 0L, availableQuantity = 0L ,totalPurchaseQuantity=0L ,totalSellQuantity=0L;
	Long purchaseCost = 0L, sellCost = 0L ,PurchasePerProductCost=0L , SellPerProductCost=0L;
	int cuscount = 0, supcount = 0;
	Long avgPurchaseCost = 0L, avgSellCost = 0L;

	@Autowired
	InventoryRepo inventoryrepo;

	@Autowired
	ReportsRepo reportsrepo;

	@Autowired
	SupplierRepo sr;

	@Autowired
	CustomerRepository cr;

	@Autowired
	ProductRepository pr;

	@GetMapping("/view")
	public List<Reports> viewReports() {
		List<Product> productlist = pr.findAll();
		List<Supplier> supplierlist = sr.findAll();
		List<Customer> customerlist = cr.findAll();

		reportsrepo.deleteAll();
		if (productlist != null) {
			for (Product productname : productlist) {

				Reports rep = new Reports();
				String pn = productname.getPname();
				if (!pn.isBlank() && pn != null) {
					rep.setProductName(pn);

					if (!customerlist.isEmpty()) {
						String customerName = null;
						Date dateofsell = null;
						for (int i = 0; i < customerlist.size(); i++) {

							Customer cus = customerlist.get(i);
							if (pn.equals(cus.getCustomerProductName())) {

								sellCost = cus.getSellingCost();
								sellQuantity = cus.getCustomerProductQuantity();
								totalSellQuantity+=sellQuantity;
								if(sellCost!=0)
								{
									SellPerProductCost+= sellCost/sellQuantity;
								}
								customerName = cus.getCustomerName();
								dateofsell = cus.getDateOfSell();
								cuscount++;
							}
						}

						if (SellPerProductCost != 0) {
							avgSellCost = (SellPerProductCost / cuscount);
						}
						rep.setLastDateOfSell(dateofsell);
						rep.setLastCustomerName(customerName);
						rep.setAvgSellingCost(avgSellCost);
						rep.setNoOfSale(cuscount);
					}

					if (!supplierlist.isEmpty()) {

						for (int j = 0; j < supplierlist.size(); j++) {

							Supplier sup = supplierlist.get(j);
							if (pn.equals(sup.getSupplierProductName())) {
								purchaseCost = sup.getBuyingCost();
								purchaseQuantity = sup.getSupplierProductQuantity();
								totalPurchaseQuantity+=purchaseQuantity;
								if(purchaseCost!=0)
								{
									PurchasePerProductCost += (purchaseCost/purchaseQuantity);
								}
								supcount++;
							}
						}

						if (PurchasePerProductCost!=0) {
							avgPurchaseCost = (PurchasePerProductCost / supcount);
						}
						rep.setAvgBuyingCost(avgPurchaseCost);

					}
					rep.setAvaialbleQuantity(totalPurchaseQuantity - totalSellQuantity);
					rep.setProfit(avgSellCost - avgPurchaseCost);
				}
				reportsrepo.save(rep); // saving data

				sellCost = 0L;
				sellQuantity = 0L;
				cuscount = 0;
				supcount = 0;
				purchaseCost = 0L;
				purchaseQuantity = 0L;
				avgPurchaseCost = 0L;
				avgSellCost = 0L;
				PurchasePerProductCost =0L;
			}
			return reportsrepo.findAll();
		}
		return null;

	}
}
