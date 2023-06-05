package com.emicalc;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.faces.bean.ViewScoped;

import jakarta.inject.Named;

@Named("dtFilterView")
@ViewScoped
public class Filter {
	private List<User> products;
	
	 public List<User> getProducts() {
	        return new ArrayList<>(products);
	    }

	    public List<User> getProducts(int size) {

	        if (size > products.size()) {
	            Random rand = new Random();

	            List<User> randomList = new ArrayList<>();
	            for (int i = 0; i < size; i++) {
	                int randomIndex = rand.nextInt(products.size());
	                randomList.add(products.get(randomIndex));
	            }

	            return randomList;
	        }

	        else {
	            return new ArrayList<>(products.subList(0, size));
	        }

	    }

	    public List<User> getClonedProducts(int size) {
	        List<User> results = new ArrayList<>();
	        List<User> originals = getProducts(size);
	        for (User original : originals) {
	            results.add(original);
	        }

	        // make sure to have unique codes
	        for (User product : results) {
	            product.setMobile_no(UUID.randomUUID().toString().replace("-", "").substring(0, 8));
	        }

	        return results;
	    }


}
