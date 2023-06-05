package com.emicalc;

import java.util.List;

public class Test4 {
	public static void main(String[] args) {
		BankDAO bankDAO=new BankDAO();
		UserDAO dao=new UserDAO();
		Bank bank=new Bank();
		bank.setBANK("SBI");
		bank.setAccountno("1234567890");
		bank.setIFSC("asdfghjk");
		User user=new User();
		user.setFirst_Name("Gaurav");
		user.setLast_Name("sharma");
		user.setMobile_no("1234567221");
		user.setEmail("Gaurav122@gmail.com");
		user.setPassword("Gaurav@12");
		dao.Adduser(user);
		bank.setUser(user);
		bankDAO.AddBankDetail(bank);
		System.out.println("gaurav");
	}

	
}



