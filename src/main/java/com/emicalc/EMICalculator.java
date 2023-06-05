package com.emicalc;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.primefaces.event.SlideEndEvent;

@ManagedBean(name ="emiCalc")
@SessionScoped

@Entity
@Table
public class EMICalculator {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int emi_id;
    private double p_amt;
    private double roi;
    private int l_tenure_year;
    private int l_tenure_month;
    private double monthly_emi=0.0;
    private double interest_amt=0.0;
    private int no_months=0;
    private String user_id;

    public int getEmi_id() {
		return emi_id;
	}

	public void setEmi_id(int emi_id) {
		this.emi_id = emi_id;
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

    public double getMonthly_emi() {
        return monthly_emi;
    }

    public void setMonthly_emi(double monthly_emi) {
        this.monthly_emi = monthly_emi;
    }

    public double getInterest_amt() {
        return interest_amt;
    }

    public void setInterest_amt(double interest_amt) {
        this.interest_amt = interest_amt;
    }

    public int getNo_months() {
        return no_months;
    }

    public void setNo_months(int no_months) {
        this.no_months = no_months;
    }

    public void calculateEMI() {
        double rate = roi / (12 * 100);
        int total_months = l_tenure_year * 12 + l_tenure_month;
        double emi = (p_amt * rate * Math.pow(1 + rate, total_months)) / (Math.pow(1 + rate, total_months) - 1);
        double total_interests = emi * total_months - p_amt;
        if (Double.isNaN(emi)) {
            monthly_emi=p_amt/total_months;
        }
        else {	
        	monthly_emi=emi;
        }
        if(Double.isNaN(total_interests)) {
        	interest_amt=0.0;
        }else {
        	interest_amt=total_interests;
        }
        setNo_months(total_months);
    }
    public void onInputChanged(ValueChangeEvent event) {
    	
		FacesMessage message = new FacesMessage("Input Changed", "Amount: " + event.getNewValue());
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	
	public void onSlideEnd(SlideEndEvent event) {
		FacesMessage message = new FacesMessage("Slide Ended", "Value: " + event.getValue());
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public EMICalculator(int emi_id, double p_amt, double roi, int l_tenure_year, int l_tenure_month,
			double monthly_emi, double interest_amt, int no_months, String user_id) {
		super();
		this.emi_id = emi_id;
		this.p_amt = p_amt;
		this.roi = roi;
		this.l_tenure_year = l_tenure_year;
		this.l_tenure_month = l_tenure_month;
		this.monthly_emi = monthly_emi;
		this.interest_amt = interest_amt;
		this.no_months = no_months;
		this.user_id = user_id;
	}

	public EMICalculator() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
