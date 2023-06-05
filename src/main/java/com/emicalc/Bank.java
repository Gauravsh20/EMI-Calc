package com.emicalc;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@ManagedBean
@SessionScoped
@Table
@Entity
public class Bank{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int BANK_ID;
	@ManyToOne(cascade = CascadeType.ALL)
	private User user;
    private String BANK;
    private String IFSC;
    private String Accountno;
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
	

	public Bank(int bANK_ID, User user, String bANK, String iFSC, String accountno) {
		super();
		BANK_ID = bANK_ID;
		this.user = user;
		BANK = bANK;
		IFSC = iFSC;
		Accountno = accountno;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getAccountno() {
		return Accountno;
	}
	public void setAccountno(String accountno) {
		Accountno = accountno;
	}
	public int getBANK_ID() {
		return BANK_ID;
	}
	public void setBANK_ID(int bANK_ID) {
		BANK_ID = bANK_ID;
	}

	public Bank() {
		super();
		// TODO Auto-generated constructor stub
	}

    // getters and setters
}
