package com.financial.Inventory;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "Product_Inventory")
public class ProductInventoryDetails {
	
	
	@Field("Product_Name")								// from product
	private String productName;
	
	@Field("Product_Type")								// from product
	private String productType;
	
	@Field("Product_Category")							// from product
	private String productCategory;
	
	@Field("Available_Quantity")						//calculate from supplier and customer
	private Long availableQuantity;
	
	@Field("Buting_cost")								// from supplier
	private Long BuyingcostOfProduct;
	
	@Field("Selling_Cost")								// from customer
	private Long sellingCostOfProduct;
	
	@Field("Date_of_Purchase")							//from supplier
	private Date dateOfPurchase;
	
	

	public ProductInventoryDetails(String productName, String productType, String productCategory,
			Long availableQuantity, Long buyingcostOfProduct, Long sellingCostOfProduct, Date dateOfPurchase) {
		super();
		this.productName = productName;
		this.productType = productType;
		this.productCategory = productCategory;
		this.availableQuantity = availableQuantity;
		BuyingcostOfProduct = buyingcostOfProduct;
		this.sellingCostOfProduct = sellingCostOfProduct;
		this.dateOfPurchase = dateOfPurchase;
	}

	public ProductInventoryDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public Long getAvailableQuantity() {
		return availableQuantity;
	}

	public void setAvailableQuantity(Long availableQuantity) {
		this.availableQuantity = availableQuantity;
	}

	public Long getBuyingcostOfProduct() {
		return BuyingcostOfProduct;
	}

	public void setBuyingcostOfProduct(Long buyingcostOfProduct) {
		BuyingcostOfProduct = buyingcostOfProduct;
	}

	public Long getSellingCostOfProduct() {
		return sellingCostOfProduct;
	}

	public void setSellingCostOfProduct(Long sellingCostOfProduct) {
		this.sellingCostOfProduct = sellingCostOfProduct;
	}

	public Date getDateOfPurchase() {
		return dateOfPurchase;
	}

	public void setDateOfPurchase(Date dateOfPurchase) {
		this.dateOfPurchase = dateOfPurchase;
	}
	
	
}
