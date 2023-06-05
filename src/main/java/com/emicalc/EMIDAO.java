package com.emicalc;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

@ManagedBean(name = "EMIDAO")
@RequestScoped
public class EMIDAO {

	/**
	 * Add the EMI Data of the User
	 * 
	 * @param entities
	 * @return
	 */
	public String addHistory(EMICalculator entities) {
		SessionHelper sh = new SessionHelper();
		SessionFactory sf = sh.getconnection();
		Session session = sf.openSession();
		Criteria criteria = session.createCriteria(EMICalculator.class);
		session.beginTransaction();
		try {
			entities.setUser_id(UserSession.getInstance().getUser().getUser_ID());
			entities.setMonthly_emi(calculateEMI(entities.getP_amt(), entities.getRoi(), entities.getL_tenure_year(),
					entities.getL_tenure_month()));
			entities.setInterest_amt(calculateTotalInterest(entities.getP_amt(), entities.getRoi(),
					entities.getL_tenure_year(), entities.getL_tenure_month()));
			System.out.println(entities.getMonthly_emi());
			session.save(entities);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			session.getTransaction().commit();
			session.close();

		}
		return null;
	}

// Calculate The Monthy EMI 
	/**
	 * @param loanAmount
	 * @param annualInterestRate
	 * @param d(year)
	 * @param e(months)
	 * @return
	 */
	public static double calculateEMI(double loanAmount, double annualInterestRate, int d, int e) {
		int totalMonths = calculateTotalMonths(d, e);
		double monthlyInterestRate = (annualInterestRate / 12) / 100;
		double emi = (loanAmount * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, totalMonths))
				/ (Math.pow(1 + monthlyInterestRate, totalMonths) - 1);
		return emi;
	}

	// Calculate The Monthy's to pay the EMI

	/**
	 * @param years
	 * @param months
	 * @return
	 */
	public static int calculateTotalMonths(int years, int months) {
		return years * 12 + months;
	}

	// Calculate the Total Interest to Pay on the Principal Amount

	/**
	 * @param principle
	 * @param rate
	 * @param d(year)
	 * @param e(months)
	 * @return
	 */
	public static double calculateTotalInterest(double principle, double rate, int d, int e) {
		int totalMonths = calculateTotalMonths(d, e);
		double monthlyRate = rate / 1200; // divide by 1200 to convert from annual percentage to monthly decimal rate
		double totalInterest = 0;
		for (int i = 0; i < totalMonths; i++) {
			double interest = principle * monthlyRate;
			totalInterest += interest;
			principle += interest;
		}
		return totalInterest;
	}

}
