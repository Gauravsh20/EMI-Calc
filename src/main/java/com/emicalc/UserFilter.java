package com.emicalc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class UserFilter implements Serializable {
	private List<User> userList;
	private String firstname;
	private String lastname;
	private String email;
	private String mobileNo;
	private UserDAO userDAO;
	private String option;
	private String option1;

	public String getOption1() {
		return option1;
	}

	public void setOption1(String option1) {
		this.option1 = option1;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	@PostConstruct
	public void init() {
		userDAO = new UserDAO();
		userList = new ArrayList<>();
	}

	public void searchByName() {
	    if (firstname != null && !firstname.isEmpty() && option != null && lastname != null && !lastname.isEmpty() && option1 != null) {
	        userList = userDAO.findUsersByNameAndLastName(firstname, option, lastname, option1);
	    } else if (firstname != null && !firstname.isEmpty() && option != null) {
	        userList = userDAO.findUsersByName(firstname, option);
	    } else if (lastname != null && !lastname.isEmpty() && option1 != null) {
	        userList = userDAO.findUsersByLastName(lastname, option1);
	    }
	    
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public void searchByMail() {
		userList = userDAO.findUsersByEmail(email);
		if (userList.isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "No record found.", ""));
		}
	}

	public void searchByMobile() {

		userList = userDAO.findUsersByMobile(mobileNo);
		if (userList.isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "No record found.", ""));
		}
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	// getters and setters
	public String clearSearch() {
		firstname = null;
		lastname = null;
		option = null;
		option1 = null;
		userList = null;
		return null;
	}

}
