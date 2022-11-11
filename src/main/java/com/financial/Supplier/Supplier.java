package com.financial.Supplier;

import java.util.Date;

import javax.persistence.Transient;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "Supplier_Details")
public class Supplier {
	
	@Transient
	public static final String SUPPLIER_SEQUENCE_NAME = "supplier_sequence"; 
	
	@Id
	private int id;
	
	@Field("Supplier_Name")
	private String supplierName;
	@Field("Product_Name")
	private String supplierProductName;					//works in dropdown section
	@Field("Product_Quantity")
	private Long supplierProductQuantity;
	@Field("Buying_Cost")//update the product quantity as well
	private Long buyingCost ;	//update the product cost as well
	@Field("Supplier_Email")
	private String supplierEmail;
	@Field("Supplier_Contact")
	private Long supplierContactNo;
	@Field("Supplier_Address")
	private String supplierAddress;
	@Field("Supplier_count")
	private int scount;
	@Field("Date_of_purchase")
	private Date dateOfPurchase;
	@Field("Product_TaxRate")
	private String taxRate;
	@Field("UserId")
	private String userid;
	
	

	public Supplier() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

	@Override
	public String toString() {
		return "Supplier [id=" + id + ", supplierName=" + supplierName + ", supplierProductName=" + supplierProductName
				+ ", supplierProductQuantity=" + supplierProductQuantity + ", buyingCost=" + buyingCost
				+ ", supplierEmail=" + supplierEmail + ", supplierContactNo=" + supplierContactNo + ", supplierAddress="
				+ supplierAddress + ", scount=" + scount + ", dateOfPurchase=" + dateOfPurchase + ", taxRate=" + taxRate
				+ ", userid=" + userid + "]";
	}


	//Constructor field calls
	public Supplier(int id, String supplierName, String supplierProductName, Long supplierProductQuantity,
			Long buyingCost, String supplierEmail, Long supplierContactNo, String supplierAddress, int scount,
			Date dateOfPurchase, String taxRate, String userid) {
		super();
		this.id = id;
		this.supplierName = supplierName;
		this.supplierProductName = supplierProductName;
		this.supplierProductQuantity = supplierProductQuantity;
		this.buyingCost = buyingCost;
		this.supplierEmail = supplierEmail;
		this.supplierContactNo = supplierContactNo;
		this.supplierAddress = supplierAddress;
		this.scount = scount;
		this.dateOfPurchase = dateOfPurchase;
		this.taxRate = taxRate;
		this.userid = userid;
	}

	//Getters and Setters
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getSupplierProductName() {
		return supplierProductName;
	}
	public void setSupplierProductName(String supplierProductName) {
		this.supplierProductName = supplierProductName;
	}
	public Long getSupplierProductQuantity() {
		return supplierProductQuantity;
	}
	public void setSupplierProductQuantity(Long supplierProductQuantity) {
		this.supplierProductQuantity = supplierProductQuantity;
	}
	public Long getBuyingCost() {
		return buyingCost;
	}
	public void setBuyingCost(Long buyingCost) {
		this.buyingCost = buyingCost;
	}
	public String getSupplierEmail() {
		return supplierEmail;
	}
	public void setSupplierEmail(String supplierEmail) {
		this.supplierEmail = supplierEmail;
	}
	public Long getSupplierContactNo() {
		return supplierContactNo;
	}
	public void setSupplierContactNo(Long supplierContactNo) {
		this.supplierContactNo = supplierContactNo;
	}
	public String getSupplierAddress() {
		return supplierAddress;
	}
	public void setSupplierAddress(String supplierAddress) {
		this.supplierAddress = supplierAddress;
	}
	public int getScount() {
		return scount;
	}
	public void setScount(int scount) {
		this.scount = scount;
	}
	public Date getDateOfPurchase() {
		return dateOfPurchase;
	}
	public void setDateOfPurchase(Date dateOfPurchase) {
		this.dateOfPurchase = dateOfPurchase;
	}
	public String getTaxRate() {
		return taxRate;
	}
	public void setTaxRate(String taxRate) {
		this.taxRate = taxRate;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	
	
}
