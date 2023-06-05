package com.emicalc;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class UserSession {
	private static UserSession instance = null;
	private User user;
	private String U_Name;
	private boolean authenticated;

	public String getU_Name() {
		return U_Name;
	}

	public void setU_Name(String u_Name) {
		U_Name = u_Name;
	}

	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}

	private UserSession() {
		// Private constructor to prevent instantiation from outside the class
	}

	public static synchronized UserSession getInstance() {
		if (instance == null) {
			instance = new UserSession();
		}
		return instance;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void login(String gmail, String password, Logintype logintype) throws NoSuchAlgorithmException {
		// Perform authentication and set authenticated flag to true if successful
		User user = null;
		user = authenticateUser(gmail, password, logintype);
		if (user != null) {
			this.user = user;
			this.authenticated = true;
			U_Name = user.getFirst_Name();
		} else {
			this.authenticated = false;
		}
	}

	private User authenticateUser(String gmail, String password, Logintype logintype) throws NoSuchAlgorithmException {
		// Perform authentication logic here, such as querying a database or calling an
		// API
		// Return the User object if the authentication is successful, null otherwise
		SessionHelper sh = new SessionHelper();
		SessionFactory sf = sh.getconnection();
		Session session = sf.openSession();
		Transaction tans = session.beginTransaction();
		UserDAO dao = new UserDAO();
		String psw = dao.doHasing(password);
		User user;
		try {
			Criteria cr = session.createCriteria(User.class);
			if (logintype.equals(Logintype.email)) {
				cr.add(Restrictions.eq("Email", gmail));
			} else if (logintype.equals(Logintype.Mobileno)) {
				cr.add(Restrictions.eq("Mobile_no", gmail));
			}

			cr.add(Restrictions.eq("Password", psw));

			List<User> list = cr.list();
			user = list.get(0);
			return user;
		} catch (IndexOutOfBoundsException boundsException) {
			// TODO Auto-generated catch block
			return null;
		} finally {
			tans.commit();
			session.close();
		}
	}

	public void logout() {
		// Clear session data and set authenticated flag to false
		this.user = null;
		this.authenticated = false;
		this.U_Name = null;
	}

	public boolean isAuthenticated() {
		return authenticated;
	}
	public boolean isUsernameValid() {
	    return user != null;
	}

	public boolean isPasswordValid() {
	    return authenticated && user != null;
	}


}
