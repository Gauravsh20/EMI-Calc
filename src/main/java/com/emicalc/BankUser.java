package com.emicalc;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@ManagedBean
@ViewScoped
@Entity
@Table(name="userwithbankdata")
public class BankUser {
    @Override
	public String toString() {
		return "BankUser [BANK_ID=" + BANK_ID + ", User_ID=" + User_ID + ", BANK=" + BANK + ", IFSC=" + IFSC
				+ ", Accountno=" + Accountno + ", First_Name=" + First_Name + ", Last_Name=" + Last_Name + ", Email="
				+ Email + ", Mobile_no=" + Mobile_no + ", Password=" + Password + "]";
	}

	public BankUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getBANK_ID() {
		return BANK_ID;
	}

	public void setBANK_ID(int bANK_ID) {
		BANK_ID = bANK_ID;
	}

	public String getUser_ID() {
		return User_ID;
	}

	public void setUser_ID(String user_ID) {
		User_ID = user_ID;
	}

	public String getBANK() {
		return BANK;
	}

	public void setBANK(String bANK) {
		BANK = bANK;
	}

	public String getIFSC() {
		return IFSC;
	}

	public void setIFSC(String iFSC) {
		IFSC = iFSC;
	}

	public String getAccountno() {
		return Accountno;
	}

	public void setAccountno(String accountno) {
		Accountno = accountno;
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
	@Id
	private int BANK_ID;
	
    private String User_ID;
    
    private String BANK;
    private String IFSC;
    private String Accountno;
    private String First_Name;
    private String Last_Name;
    private String Email;
    private String Mobile_no;
    private String Password;
    
    public BankUser(int bANK_ID, String user_ID, String bANK, String iFSC, String accountno, String first_Name, String last_Name, String email, String mobile_no, String password) {
        super();
        BANK_ID = bANK_ID;
        User_ID = user_ID;
        BANK = bANK;
        IFSC = iFSC;
        Accountno = accountno;
        First_Name = first_Name;
        Last_Name = last_Name;
        Email = email;
        Mobile_no = mobile_no;
        Password = password;
    }
    
    // getters and setters
}
