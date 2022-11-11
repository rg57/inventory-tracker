package com.financial.Reports;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "Reports")
public class Reports {
	
	@Field("Product_Name")	
	private String productName;
	
	@Field("Available_Quantity")
	private Long avaialbleQuantity;
	
	@Field("Avg_Sellingcost")
	private Long avgSellingCost;
	
	@Field("Avg_buyingcost")
	private Long avgBuyingCost;
	
	@Field("Avg_profit")
	private Long profit;
	
	@Field("No_of_sale")
	private int noOfSale;
	
	@Field("LastCustomer")
	private String lastCustomerName;

	@Field("Last_DateOfSell")
	private Date lastDateOfSell;
	
	
	public Reports(String productName, Long avaialbleQuantity, Long avgSellingCost, Long avgBuyingCost, Long profit,
			int noOfSale, String lastCustomerName, Date lastDateOfSell) {
		super();
		this.productName = productName;
		this.avaialbleQuantity = avaialbleQuantity;
		this.avgSellingCost = avgSellingCost;
		this.avgBuyingCost = avgBuyingCost;
		this.profit = profit;
		this.noOfSale = noOfSale;
		this.lastCustomerName = lastCustomerName;
		this.lastDateOfSell = lastDateOfSell;
	}


	public Reports() {
		// TODO Auto-generated constructor stub
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public Long getAvaialbleQuantity() {
		return avaialbleQuantity;
	}


	public void setAvaialbleQuantity(Long avaialbleQuantity) {
		this.avaialbleQuantity = avaialbleQuantity;
	}


	public Long getAvgSellingCost() {
		return avgSellingCost;
	}


	public void setAvgSellingCost(Long avgSellingCost) {
		this.avgSellingCost = avgSellingCost;
	}


	public Long getAvgBuyingCost() {
		return avgBuyingCost;
	}


	public void setAvgBuyingCost(Long avgBuyingCost) {
		this.avgBuyingCost = avgBuyingCost;
	}


	public Long getProfit() {
		return profit;
	}


	public void setProfit(Long profit) {
		this.profit = profit;
	}

	public int getNoOfSale() {
		return noOfSale;
	}


	public void setNoOfSale(int i) {
		this.noOfSale = i;
	}

	public String getLastCustomerName() {
		return lastCustomerName;
	}


	public void setLastCustomerName(String lastCustomerName) {
		this.lastCustomerName = lastCustomerName;
	}


	public Date getLastDateOfSell() {
		return lastDateOfSell;
	}


	public void setLastDateOfSell(Date lastDateOfSell) {
		this.lastDateOfSell = lastDateOfSell;
	}
	
	
	
}
