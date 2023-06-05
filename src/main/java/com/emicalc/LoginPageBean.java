package com.emicalc;

import java.security.NoSuchAlgorithmException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class LoginPageBean {

	private UserSession userSession;
	private Logintype logintype;
	private String username;
	private String password;

	/**
	 * 
	 */
	public LoginPageBean() {
		userSession = UserSession.getInstance();
	}

	// Login Check

	/**
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public String login() throws NoSuchAlgorithmException {
	    UserDAO dao = new UserDAO();
	    User user = dao.getUser(username, logintype);
	    if (user == null) {
	        FacesContext.getCurrentInstance().addMessage("form:Email", new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                "Invalid username", "User does not exist"));
	        return null;
	    } else {
	        String hashedPassword = dao.doHasing(password);
	        if (hashedPassword.equals(user.getPassword())) {
	            userSession.login(username, hashedPassword, logintype);
	            FacesContext context = FacesContext.getCurrentInstance();
	            ExternalContext externalContext = context.getExternalContext();
	            System.out.println(userSession.getU_Name());
	            externalContext.getSessionMap().put("username", user.getFirst_Name());
	            externalContext.getSessionMap().put("email", user.getEmail());
	            return "User_Page.xhtml?faces-redirect=true"; // Redirect to the home page
	        } else {
	            FacesContext.getCurrentInstance().addMessage("form:Password", new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                    "Invalid password", "Authentication failed"));
	            return null;
	        }
	    }
	}



	/**
	 * @return Redirect back to the login page
	 */
	public String logout() {
		userSession.logout();
		return "/Loginpage.xhtml?faces-redirect=true"; // Redirect back to the login page
	}

//Getter and Setter 

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Logintype getLogintype() {
		return logintype;
	}

	public void setLogintype(Logintype logintype) {
		this.logintype = logintype;
	}
}
