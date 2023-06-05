package com.emicalc;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

import org.primefaces.event.SlideEndEvent;




@ManagedBean
@ViewScoped

@Entity
@Table
public class Entities{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int emi_id;
	
	private double p_amt;
	
	private double roi;
	
	private int l_tenure_year;
	
	private int l_tenure_month;
	
	private int no_months;
	
	private double interest_amt;
	
	private double monthly_emi;
	 
	private String user_id;

	
	public Entities(int emi_id, double p_amt, double roi, int l_tenure_year, int l_tenure_month, int no_months,
			double interest_amt, double monthly_emi, String user_id) {
		super();
		this.emi_id = emi_id;
		this.p_amt = p_amt;
		this.roi = roi;
		this.l_tenure_year = l_tenure_year;
		this.l_tenure_month = l_tenure_month;
		this.no_months = no_months;
		this.interest_amt = interest_amt;
		this.monthly_emi = monthly_emi;
		this.user_id = user_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public double getP_amt() {
		return p_amt;
	}

	public void setP_amt(double p_amt) {
		this.p_amt = p_amt;
	}

	public double getRoi() {
		return roi;
	}

	public void setRoi(double roi) {
		this.roi = roi;
	}

	

	public int getNo_months() {
		return no_months;
	}

	public void setNo_months(int no_months) {
		this.no_months = no_months;
	}

	public double getInterest_amt() {
		return interest_amt;
	}

	public void setInterest_amt(double interest_amt) {
		this.interest_amt = interest_amt;
	}

	public double getMonthly_emi() {
		return monthly_emi;
	}

	public void setMonthly_emi(double monthly_emi) {
		this.monthly_emi = monthly_emi;
	}
	
	
	public int getL_tenure_year() {
		return l_tenure_year;
	}

	public void setL_tenure_year(int l_tenure_year) {
		this.l_tenure_year = l_tenure_year;
	}

	public int getL_tenure_month() {
		return l_tenure_month;
	}

	public void setL_tenure_month(int l_tenure_month) {
		this.l_tenure_month = l_tenure_month;
	}
	
	
	public int getEmi_id() {
		return emi_id;
	}

	public void setEmi_id(int emi_id) {
		this.emi_id = emi_id;
	}
	
	
	     
	public void onInputChanged(ValueChangeEvent event) {
	
		FacesMessage message = new FacesMessage("Input Changed", "Amount: " + event.getNewValue());
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	
	public void onSlideEnd(SlideEndEvent event) {
		FacesMessage message = new FacesMessage("Slide Ended", "Value: " + event.getValue());
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	

	public Entities() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	private boolean showResult;
	public void calculateEMI() {
        double monthly_roi = roi / (12 * 100); // Monthly interest rate
        int num_payments = l_tenure_month * 12; // Total number of payments

        monthly_emi = p_amt * monthly_roi * Math.pow(1 + monthly_roi, num_payments) / (Math.pow(1 + monthly_roi, num_payments) - 1);
        interest_amt = monthly_emi * num_payments - p_amt; // Total interest paid

        showResult = true;
    }
	public String getEmiFormatted() {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        return df.format(monthly_emi);
    }

	public boolean isShowResult() {
		return showResult;
	}

	public void setShowResult(boolean showResult) {
		this.showResult = showResult;
	}

   


}
