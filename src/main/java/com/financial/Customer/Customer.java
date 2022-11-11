package com.financial.Customer;



import java.util.Date;

import javax.persistence.Transient;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "Customer_Details")
public class Customer {

	@Transient
	public static final String CUSTOMER_SEQUENCE_NAME = "customer_sequence"; 
	
	@Id
	private int id;
	
	@Field("Customer_Name")
	private String customerName;
	@Field("Product_Name")
	private String customerProductName;
	@Field("Product_Quantity")
	private Long customerProductQuantity;
	@Field("Customer_count")
	private int customerProductCount;
	@Field("Selling_Cost")
	private Long sellingCost;
	@Field("Customer_contact")
	private Long customerContactNo;
	@Field("Customer_email")
	private String customerEmail;
	@Field("Customer_Address")
	private String customerAddress;
	@Field("Product_Selling_Date")
	private Date dateOfSell;
	@Field("UserId")
	private String userid;
	
	
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Customer(int id, String customerName, String customerProductName, Long customerProductQuantity,
			int customerProductCount, Long sellingCost, Long customerContactNo, String customerEmail,
			String customerAddress, Date dateOfSell, String userid) {
		super();
		this.id = id;
		this.customerName = customerName;
		this.customerProductName = customerProductName;
		this.customerProductQuantity = customerProductQuantity;
		this.customerProductCount = customerProductCount;
		this.sellingCost = sellingCost;
		this.customerContactNo = customerContactNo;
		this.customerEmail = customerEmail;
		this.customerAddress = customerAddress;
		this.dateOfSell = dateOfSell;
		this.userid = userid;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", customerName=" + customerName + ", customerProductName=" + customerProductName
				+ ", customerProductQuantity=" + customerProductQuantity + ", customerProductCount="
				+ customerProductCount + ", sellingCost=" + sellingCost + ", customerContactNo=" + customerContactNo
				+ ", customerEmail=" + customerEmail + ", customerAddress=" + customerAddress + ", dateOfSell="
				+ dateOfSell + ", userid=" + userid + "]";
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerProductName() {
		return customerProductName;
	}
	public void setCustomerProductName(String customerProductName) {
		this.customerProductName = customerProductName;
	}
	public Long getCustomerProductQuantity() {
		return customerProductQuantity;
	}
	public void setCustomerProductQuantity(Long customerProductQuantity) {
		this.customerProductQuantity = customerProductQuantity;
	}
	public int getCustomerProductCount() {
		return customerProductCount;
	}
	public void setCustomerProductCount(int customerProductCount) {
		this.customerProductCount = customerProductCount;
	}
	public Long getSellingCost() {
		return sellingCost;
	}
	public void setSellingCost(Long sellingCost) {
		this.sellingCost = sellingCost;
	}
	public Long getCustomerContactNo() {
		return customerContactNo;
	}
	public void setCustomerContactNo(Long customerContactNo) {
		this.customerContactNo = customerContactNo;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	public Date getDateOfSell() {
		return dateOfSell;
	}
	public void setDateOfSell(Date string) {
		this.dateOfSell = string;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	
		
}
