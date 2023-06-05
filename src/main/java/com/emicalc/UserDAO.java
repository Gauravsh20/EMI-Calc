package com.emicalc;
//describe the class and method and work

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

@ManagedBean
@SessionScoped
public class UserDAO {

	/**
	 * Generate User Id
	 * 
	 * @return
	 */
	public String generateDriverId() {
		SessionFactory sf = SessionHelper.getconnection();
		Session session = sf.openSession();
		Criteria cr = session.createCriteria(User.class);
		List<User> user = cr.list();
		if (user.size() == 0) {
			return "U001";
		}
		// session.close();
		String id = user.get(user.size() - 1).getUser_ID();
		int id1 = Integer.parseInt(id.substring(1));
		id1++;
		String id2 = String.format("U%03d", id1);
		return id2;

	}

	/**
	 * Add user method
	 * 
	 * @param user
	 * @return
	 * @throws NoSuchAlgorithmException
	 */

	public String Adduser(User user) {
		SessionHelper sh = new SessionHelper();
		SessionFactory sf = sh.getconnection();
		Session session = sf.openSession();
		Criteria criteria = session.createCriteria(User.class);
		try {
			session.beginTransaction();
			user.setUser_ID(generateDriverId());
			user.setPassword(doHasing(user.getPassword()));
			user.setPassword_F(user.getPassword());
			user.setPassword_S(user.getPassword());
			session.save(user);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// TODO: handle finally clause
			session.getTransaction().commit();
			session.close();
			FacesContext context = FacesContext.getCurrentInstance();
			ExternalContext externalContext = context.getExternalContext();
			externalContext.getSessionMap().put("username", user.getFirst_Name());
			externalContext.getSessionMap().put("email", user.getEmail());
		}
		return "User_Page.xhtml?faces-redirect=true";
	}

	/**
	 * Password Encryption method
	 * 
	 * @param Password
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public String doHasing(String Password) throws NoSuchAlgorithmException {
		MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		messageDigest.update(Password.getBytes());
		byte[] npss = messageDigest.digest();
		StringBuilder sb = new StringBuilder();
		for (byte b : npss) {
			sb.append(String.format("%02x", b));
		}
		return sb.toString();
	}

	/**
	 * Show all users
	 * 
	 * @return
	 */
	public List<User> ShowAllUSer() {
		SessionFactory sessionFactory = SessionHelper.getconnection();
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from User");
		Criteria cr = session.createCriteria(User.class);
		List<User> userList = query.list();
		return userList;

	}

	/**
	 * User Search by First Name
	 * 
	 * @param fname
	 * @param option
	 * @return
	 */
	public List<User> findUsersByName(String fname, String option) {
		System.out.println(fname + "" + "" + option);
		Query query;
		
			SessionFactory sessionFactory = SessionHelper.getconnection();
			Session session = sessionFactory.openSession();
			if (option.equals("f_Option1")) {
				query = session.createQuery("SELECT u FROM User u WHERE u.First_Name LIKE :fname");
				query.setParameter("fname", fname + "%");
			} else {
				query = session.createQuery("SELECT u FROM User u WHERE u.First_Name LIKE :fname");
				query.setParameter("fname", "%" + fname + "%");
			}
			List<User>  userList = query.list();
			return userList;

	}

	/**
	 * User Search By Last Name
	 * 
	 * @param lname
	 * @param option1
	 * @return
	 */
	public List<User> findUsersByLastName(String lname, String option1) {
		SessionFactory sessionFactory = SessionHelper.getconnection();
		Session session = sessionFactory.openSession();
		Query query = null;
		List<User> userList;
			System.out.println("dao" + lname + "  " + option1);
			if (option1.equals("l_Option1")) {
				query = session.createQuery("SELECT u FROM User u WHERE u.Last_Name LIKE :lname");
				query.setParameter("lname", lname + "%");
			} else {
				query = session.createQuery("SELECT u FROM User u WHERE u.Last_Name LIKE :lname");
				query.setParameter("lname", "%" + lname + "%");
			}
		userList = query.list();
		return userList;
	}

	/**
	 * User Search By Email
	 * 
	 * @param email
	 * @return
	 */
	public List<User> findUsersByEmail(String email) {
		SessionFactory sessionFactory = SessionHelper.getconnection();
		Session session = sessionFactory.openSession();
		List<User> userList = null;
		try {
			Query query = session.createQuery("SELECT u FROM User u WHERE u.Email LIKE :email");
			query.setParameter("email", "%" + email + "%");
			userList = query.list();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userList;
	}

	/**
	 * User Search By Mobile no
	 * 
	 * @param mobileNo
	 * @return
	 * 
	 */
	public List<User> findUsersByMobile(String mobileNo) {
		SessionFactory sessionFactory = SessionHelper.getconnection();
		Session session = sessionFactory.openSession();
		List<User> userList;
		Query query = null;
		try {
			query = session.createQuery("SELECT u FROM User u WHERE u.Mobile_no LIKE :mobileNo");
			query.setParameter("mobileNo", "%" + mobileNo + "%");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		userList = query.list();
		return userList;
	}

	/**
	 * User Search By First And Last Name
	 * 
	 * @param firstName
	 * @param option
	 * @param lastName
	 * @param option1
	 * @return
	 */
	public List<User> findUsersByNameAndLastName(String firstName, String option, String lastName, String option1) {
	    SessionFactory sessionFactory = SessionHelper.getconnection();
	    Session session = sessionFactory.openSession();
	    Query query = null;
	    List<User> userList = null;

	    try {
	        if (option.equals("f_Option")) {
	            if (option1.equals("l_Option1")) {
	                query = session.createQuery("SELECT u FROM User u WHERE u.First_Name LIKE :firstName AND u.Last_Name LIKE :lastName");
	                query.setParameter("firstName", firstName + "%");
	                query.setParameter("lastName", lastName + "%");
	            } else {
	                query = session.createQuery("SELECT u FROM User u WHERE u.First_Name LIKE :firstName AND u.Last_Name LIKE :lastName");
	                query.setParameter("firstName", firstName + "%");
	                query.setParameter("lastName", "%" + lastName + "%");
	            }
	        } else {
	            if (option1.equals("l_Option1")) {
	                query = session.createQuery("SELECT u FROM User u WHERE u.First_Name LIKE :firstName AND u.Last_Name LIKE :lastName");
	                query.setParameter("firstName", "%" + firstName + "%");
	                query.setParameter("lastName", lastName + "%");
	            } else {
	                query = session.createQuery("SELECT u FROM User u WHERE u.First_Name LIKE :firstName AND u.Last_Name LIKE :lastName");
	                query.setParameter("firstName", "%" + firstName + "%");
	                query.setParameter("lastName", "%" + lastName + "%");
	            }
	        }

	        userList = query.list();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        session.close();
	    }

	    return userList;
	}

	public User getUser(String username, Logintype logintype) throws NoSuchAlgorithmException {
	    SessionHelper sh = new SessionHelper();
	    SessionFactory sf = sh.getconnection();
	    Session session = sf.openSession();
	    Transaction tans = session.beginTransaction();
	    User user;
	    try {
	        Criteria cr = session.createCriteria(User.class);
	        if (logintype.equals(Logintype.email)) {
	            cr.add(Restrictions.eq("Email", username));
	        } else if (logintype.equals(Logintype.Mobileno)) {
	            cr.add(Restrictions.eq("Mobile_no", username));
	        }
	        List<User> list = cr.list();
	        if (list.isEmpty()) {
	            // No user found with the given username and login type
	            return null;
	        }
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
	
}
