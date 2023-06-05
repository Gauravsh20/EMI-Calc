package com.emicalc;



import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.CriteriaQuery;

@ManagedBean
@SessionScoped
public class BankDAO {
	SessionFactory sessionFactory;
	public String AddBankDetail(Bank bank) {
		sessionFactory=SessionHelper.getconnection();
		Session session=sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();
		Criteria criteria=session.createCriteria(Bank.class);
		session.save(bank);
		transaction.commit();
		session.close();
		return "add";
		
	}
	
	public List<BankUser> showAllUsersWithBanks() {
	    SessionFactory sessionFactory = SessionHelper.getconnection();
	    Session session = sessionFactory.openSession();
	    Query query = session.createQuery("from BankUser");
		List<BankUser> userList = query.list();
		return userList;
	    
	}
    public BankUser findByUserId(String userId) {
    	SessionFactory sessionFactory = SessionHelper.getconnection();
  	    Session session = sessionFactory.openSession();
        BankUser bankUser = null;
        try {
            Query query = session.createQuery("FROM BankUser b WHERE b.User_ID = :userId");
            query.setParameter("userId", userId);
            bankUser = (BankUser) query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return bankUser;
    }




}
