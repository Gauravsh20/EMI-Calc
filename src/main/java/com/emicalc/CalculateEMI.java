package com.emicalc;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class CalculateEMI {
	 public void displaySpinnerValue(Entities entities) {
		    System.out.println("Spinner value: " + entities.getP_amt()+" "+entities.getRoi()+" "+entities.getL_tenure_year()+""+entities.getL_tenure_month());
		    entities.setMonthly_emi(calculateEMI(entities.getP_amt(), entities.getRoi(), entities.getL_tenure_year(),entities.getL_tenure_month()));
		    entities.setInterest_amt(calculateTotalInterest(entities.getP_amt(), entities.getRoi(), entities.getL_tenure_year(),entities.getL_tenure_month()));
	 }
	        public static double calculateEMI(double loanAmount, double annualInterestRate, int d, int e) {
	            int totalMonths = calculateTotalMonths(d, e);
	            double monthlyInterestRate = (annualInterestRate / 12) / 100;
	            double emi = (loanAmount * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, totalMonths)) / (Math.pow(1 + monthlyInterestRate, totalMonths) - 1);
	            return emi;
	        }

	        public static int calculateTotalMonths(int years, int months) {
	            return years * 12 + months;
	        }
	        
	        public static double calculateTotalInterest(double principle, double rate, int d,int e) {
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
