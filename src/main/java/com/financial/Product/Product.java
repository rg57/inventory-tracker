package com.financial.Product;


import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
// MODEL CLASS


@Document(collection = "Product_Details")
public class Product {
	
	@Transient
	public static final String PRODUCT_SEQUENCE_NAME = "product_sequence"; 
	
	@Id
	private int id;
	
	@Field("Product_Name")
	private String Pname;
	@Field("Product_Type")
	private String Ptype;
	@Field("Product_Categories")
	private String Pcategories;
	@Field("UserId")
	private String userid;
	

	public String getPuserid() {
		return userid;
	}



	public void setPuserid(String puserid) {
		userid = puserid;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getPname() {
		return Pname;
	}



	public void setPname(String pname) {
		Pname = pname;
	}



	public String getPtype() {
		return Ptype;
	}



	public void setPtype(String ptype) {
		Ptype = ptype;
	}



	public String getPcategories() {
		return Pcategories;
	}



	public void setPcategories(String pcategories) {
		Pcategories = pcategories;
	}



	public Product(int id, String pname, String ptype, String pcategories, String puserid) {
		super();
		this.id = id;
		Pname = pname;
		Ptype = ptype;
		Pcategories = pcategories;
		userid = puserid;
	}



	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	@Override
	public String toString() {
		return "Product [id=" + id + ", Pname=" + Pname + ", Ptype=" + Ptype + ", Pcategories=" + Pcategories
				+ ", Puserid=" + userid + "]";
	}
	
	
	
}
