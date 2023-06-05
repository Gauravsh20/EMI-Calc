package com.emicalc;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import jakarta.inject.Named;

@Named
@ViewScoped
@ManagedBean
public class BankController implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private String ifscCode;
    private Bank bank;
    
    public void searchBank() throws IOException {
        bank = RazorpayAPIService.getBankData(ifscCode);
    }
    
    // getters and setters

}
