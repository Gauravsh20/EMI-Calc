package com.emicalc;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.inject.Named;

@ViewScoped
@Named
public class RazorpayAPIService {

    private static final String API_URL = "https://ifsc.razorpay.com/";

    public static Bank getBankData(String ifscCode) throws IOException {
        URL url = new URL(API_URL + ifscCode);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        int responseCode = conn.getResponseCode();
        if (responseCode != 200) {
            throw new RuntimeException("Failed to fetch data from API");
        }

        InputStream inputStream = conn.getInputStream();
        ObjectMapper objectMapper = new ObjectMapper();
        Bank bank = objectMapper.readValue(inputStream, Bank.class);
        return bank;
    }

}
