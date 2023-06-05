
package com.emicalc;

import java.security.NoSuchAlgorithmException;

public class Test2{
	public static void main(String[] args) throws NoSuchAlgorithmException {
		UserDAO dao =new UserDAO();
		User users=new User();
		users.setFirst_Name("Radhe");
		users.setLast_Name("Shayam");
		users.setEmail("Radhe@gmail.com");
		users.setMobile_no("1234567890");
		users.setPassword("Gaurav@12");
		System.out.println(dao.Adduser(users));	
	}

}
