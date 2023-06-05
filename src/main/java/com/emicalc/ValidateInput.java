package com.emicalc;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

@ManagedBean
@SessionScoped
public class ValidateInput {

	/**
	 * Email Validation
	 * 
	 * @param context
	 * @param comp
	 * @param value
	 */
	public void validateEmailID(FacesContext context, UIComponent comp, Object value) {
		String Email = (String) value;
		String regex = "^[\\w!#$%&amp;'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&amp;'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
		SessionFactory sf = SessionHelper.getconnection();
		Session session = sf.openSession();
		Transaction tans = session.beginTransaction();
		Criteria cr = session.createCriteria(User.class);
		cr.add(Restrictions.eq("Email", Email));
		List<User> user = cr.list();
		if (!user.isEmpty()) {
			((UIInput) comp).setValid(false);
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Please Enter Another Email",
					"Already Register Email");
			context.addMessage(comp.getClientId(context), message);
		}
		if (!patternMatches(Email, regex)) {
			((UIInput) comp).setValid(false);
			FacesMessage message = new FacesMessage("Please Enter Valid Email", "Invalid Email Format");
			context.addMessage(comp.getClientId(context), message);
		}

	}

	/**
	 * pattern Match with regex
	 * 
	 * @param emailAddress
	 * @param regexPattern
	 * @return
	 */
	public static boolean patternMatches(String emailAddress, String regexPattern) {
		return Pattern.compile(regexPattern).matcher(emailAddress).matches();
	}

	/**
	 * Name Validation
	 * 
	 * @param context
	 * @param comp
	 * @param value
	 */
	public void validationName(FacesContext context, UIComponent comp, Object value) {
		String Name = (String) value;
		String regex = "^[\\p{L}'\\-]+( [\\p{L}'\\-]+)*$";
		boolean flag = false;
		Pattern p = Pattern.compile(regex);

		if (Name == null) {
			flag = false;
		}
		Matcher m = p.matcher(Name);
		flag = m.matches();
		if (!flag) {
			((UIInput) comp).setValid(false);
			FacesMessage message = new FacesMessage("Invalid Name", "Please Enter Valide Name");
			context.addMessage(comp.getClientId(context), message);
		}

	}

	/**
	 * Password Validation
	 * 
	 * @param context
	 * @param comp
	 * @param value
	 */
	public void validationpassword(FacesContext context, UIComponent comp, Object value) {
		String Name = (String) value;
		String regex = "^(?=.*[0-9])" + "(?=.*[a-z])(?=.*[A-Z])" + "(?=.*[@#$%^&+=])" + "(?=\\S+$).{8,20}$";
		boolean flag = false;
		Pattern p = Pattern.compile(regex);
		if (Name == null) {
			flag = false;
		}
		Matcher m = p.matcher(Name);
		flag = m.matches();
		if (!flag) {
			((UIInput) comp).setValid(false);
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Please Enter Strong Password",
					"Invalid Password");
			context.addMessage(comp.getClientId(context), message);
		}

	}

	/**
	 * Phone No validation
	 * 
	 * @param context
	 * @param comp
	 * @param value
	 */
	public void validatePhnNo(FacesContext context, UIComponent comp, Object value) {

		String mno = (String) value;
		boolean flag = false;
		SessionFactory sf = SessionHelper.getconnection();
		Session session = sf.openSession();
		Transaction tans = session.beginTransaction();
		Criteria cr = session.createCriteria(User.class);
		cr.add(Restrictions.eq("Mobile_no", mno));
		List<User> user = cr.list();
		if (mno.matches("\\d{10}") && user.isEmpty()) {
			flag = true;
		}
		if (flag == false) {
			if (!user.isEmpty()) {
				((UIInput) comp).setValid(false);
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Please Enter Another Mobile No.",
						"Already register Contact Number");
				context.addMessage(comp.getClientId(context), message);
			} else {
				((UIInput) comp).setValid(false);
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please Enter Correct Mobile No.",
						"Invalid Contact Number");
				context.addMessage(comp.getClientId(context), message);
			}

		}
	}

}
