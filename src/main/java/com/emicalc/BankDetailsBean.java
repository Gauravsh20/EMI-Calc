package com.emicalc;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "bankDetailsBean")
@ViewScoped
public class BankDetailsBean {
	private String user;
	private List<BankUser> bankUsers1;
	private BankUser bankUser; // assume you have a UserService that retrieves user data

	public List<BankUser> getBankUsers1() {
		return bankUsers1;
	}

	public void setBankUsers1(List<BankUser> bankUsers1) {
		this.bankUsers1 = bankUsers1;
	}

	@PostConstruct

	public void init() {
		String userId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("userId");
		bankUsers1 = new ArrayList<>();
		BankDAO dao = new BankDAO();
		BankUser user = dao.findByUserId(userId);
		System.out.println(user);
		if (user != null) {
			bankUsers1.add(user);
		}
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public BankUser getBankUser() {
		return bankUser;
	}

	public void setBankUser(BankUser bankUser) {
		this.bankUser = bankUser;
	}

	private List<BankUser> bankUsers;

}
