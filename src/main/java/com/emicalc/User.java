package com.emicalc;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@ManagedBean
@SessionScoped
@ViewScoped
@Entity
@Table
public class User {
	
	@Id
	private String User_ID;
	
	
	private String First_Name;
	
	private String Last_Name;
	
	
	private String Email;
	
	private String Mobile_no;
	
	
	private String Password;
	
	private String Password_F;
	
	private String Password_S;

	public String getUser_ID() {
		return User_ID;
	}

	public void setUser_ID(String user_ID) {
		User_ID = user_ID;
	}

	public User(String user_ID, String first_Name, String last_Name, String email, String mobile_no, String password,
			String password_F, String password_S) {
		super();
		User_ID = user_ID;
		First_Name = first_Name;
		Last_Name = last_Name;
		Email = email;
		Mobile_no = mobile_no;
		Password = password;
		Password_F = password_F;
		Password_S = password_S;
	}

	public String getFirst_Name() {
		return First_Name;
	}

	public void setFirst_Name(String first_Name) {
		First_Name = first_Name;
	}

	public String getLast_Name() {
		return Last_Name;
	}

	public void setLast_Name(String last_Name) {
		Last_Name = last_Name;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getMobile_no() {
		return Mobile_no;
	}

	public void setMobile_no(String mobile_no) {
		Mobile_no = mobile_no;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getPassword_F() {
		return Password_F;
	}

	public void setPassword_F(String password_F) {
		Password_F = password_F;
	}

	public String getPassword_S() {
		return Password_S;
	}

	public void setPassword_S(String password_S) {
		Password_S = password_S;
	}

	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	

}
