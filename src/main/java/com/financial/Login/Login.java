package com.financial.Login;

import javax.persistence.Transient;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "Users")
public class Login {
	
	
	@Transient
	public static final String LOGIN_SEQUENCE_NAME = "login_sequence"; 
	
	@Id
	private int id;
	
	@Field("UserName")
	@Indexed(unique = true)
	private String userName;
	@Field("UserPass")
	private String userPass;
	@Field("UserEmail")
	private String userEmail;
	@Field("CreatedUserId")
	private String userId;
	@Field("IsActive")
	private boolean isactive;


	// will add active/ non-active toggle later
	

	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public Login(int id, String userName, String userPass, String userEmail, String userId, boolean isactive) {
		super();
		this.id = id;
		this.userName = userName;
		this.userPass = userPass;
		this.userEmail = userEmail;
		this.userId = userId;
		this.isactive = isactive;
	}


	public boolean isIsactive() {
		return isactive;
	}


	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getUserPass() {
		return userPass;
	}


	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserEmail() {
		return userEmail;
	}


	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	

}
