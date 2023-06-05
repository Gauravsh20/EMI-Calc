package com.emicalc;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

@ManagedBean
@ViewScoped
public class Bean {
	private User user = null;
	private String contactMethod;
	private String phoneNumber;
	private String email;
	private Logintype logintype;
	private boolean showbox = false;
	private String newPassword;
	private String confirmPassword;
	private String Oldpss;

	// getters and setters

	public String getOldpss() {
		return Oldpss;
	}

	public void setOldpss(String oldpss) {
		Oldpss = oldpss;
	}

	public String getContactMethod() {
		return contactMethod;
	}

	public void setContactMethod(String contactMethod) {
		this.contactMethod = contactMethod;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isShowbox() {
		return showbox;
	}

	public void setShowbox(boolean showbox) {
		this.showbox = showbox;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	// Check the Login Type (User-phone Or User-Email)

	/**
	 * 
	 */
	public void contactMethodChanged() {
		if (contactMethod.equals("Mobileno")) {
			logintype = Logintype.Mobileno;
			user = null;
			this.showbox = false;
			this.newPassword = null;
			this.confirmPassword = null;
		} else if (contactMethod.equals("email")) {
			this.showbox = false;
			logintype = Logintype.email;
			user = null;
			this.newPassword = null;
			this.confirmPassword = null;
		}
		newPassword = "";
		confirmPassword = "";
	}

// Check user is Authorized Or Not !

	/**
	 * @return
	 */
	public String submitForm() {
		SessionHelper sh = new SessionHelper();
		SessionFactory sf = sh.getconnection();
		Session session = sf.openSession();
		Transaction tans = session.beginTransaction();
		try {
			Criteria cr = session.createCriteria(User.class);
			if (logintype.equals(Logintype.email)) {
				cr.add(Restrictions.eq("Email", email));
			} else if (logintype.equals(Logintype.Mobileno)) {
				cr.add(Restrictions.eq("Mobile_no", phoneNumber));
			}
			List<User> list = cr.list();
			user = list.get(0);
			showbox = true;
			System.out.println(user.toString());
			FacesContext context = FacesContext.getCurrentInstance();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "valid username", "Authentication Pass"));
		} catch (IndexOutOfBoundsException boundsException) {
			showbox = false;
			FacesContext context = FacesContext.getCurrentInstance();
			FacesContext.getCurrentInstance().addMessage("form:msg",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid username", "Authentication failed"));
			return null;
		} catch (NullPointerException boundsException) {
			showbox = false;
			FacesContext context = FacesContext.getCurrentInstance();
			FacesContext.getCurrentInstance().addMessage("form:msg",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid username", "Authentication failed"));
			return null;
		} finally {
			tans.commit();
			session.close();
		}
		return "result-page";
	}

//Check User empty Or not!

	/**
	 * @return
	 */
	public boolean valid() {
		return user != null;
	}

//Change The Password With the Help of Email Or phone no.

	/**
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws NullPointerException
	 */
	public String changePassword() throws NoSuchAlgorithmException, NullPointerException {
		if (newPassword != null && !newPassword.isEmpty()) {
			// update user's password with new password
			String pass = user.getPassword();
			String pass_F = user.getPassword_F();
			String pass_S = user.getPassword_S();
			System.out.println("radheshayma");
			String pass_N = new UserDAO().doHasing(newPassword);
			if (!pass_F.equals(pass_N) && !pass_S.equals(pass_N) && !pass.equals(pass_N)) {
				System.out.println("not match");
				user.setPassword_S(user.getPassword_F());
				user.setPassword_F(user.getPassword());
				user.setPassword(new UserDAO().doHasing(newPassword));
				// save user object to database
				SessionHelper sh = new SessionHelper();
				SessionFactory sf = sh.getconnection();
				Session session = sf.openSession();
				Transaction tans = session.beginTransaction();
				try {
					session.update(user);
					tans.commit();
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_INFO, "Password updated successfully!", null));
				} catch (Exception ex) {
					tans.rollback();
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Failed to update password. Please try again later.", null));
					ex.printStackTrace();
				} finally {
					session.close();
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Password Changed Successfully !", "New password!"));
				}
			} else {
				System.out.println("Matched");
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Password is being used Perviously!", "Enter new Password."));
				FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Password is being used previously!", "Enter new Password."));
				return "result-page";
			}

		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"New password must be numeric and cannot be empty!", null));
		}
		return "Loginpage.xhtml?faces-redirect=true";
	}

//Change the password using user Email 	

	/**
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public boolean Passwordchange() throws NoSuchAlgorithmException {
		SessionHelper sh = new SessionHelper();
		SessionFactory sf = sh.getconnection();
		Session session = sf.openSession();
		Transaction tans = session.beginTransaction();
		Criteria cr = session.createCriteria(User.class);
		try {
			String email = UserSession.getInstance().getUser().getEmail();
			cr.add(Restrictions.eq("Email", email));
			List<User> list = cr.list();
			user = list.get(0);
		} catch (NullPointerException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"New password must be numeric and cannot be empty!", null));
		} finally {
			tans.commit();
		}
		System.out.println(Oldpss);
		String old = new UserDAO().doHasing(Oldpss);
		System.out.println(old);
		System.out.println(user.getPassword());
		if (old.equals(user.getPassword())) {
			System.out.println("Hello");
			showbox = true;
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Old Password doesn't match!", "Please Try Again"));

		}
		
		 
		return true;

	}

}
