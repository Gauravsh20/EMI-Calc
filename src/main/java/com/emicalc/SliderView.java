package com.emicalc;


import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;


import org.primefaces.event.SlideEndEvent;
import javax.annotation.PostConstruct;  
 
import javax.faces.bean.ManagedBean;  
import org.primefaces.model.chart.PieChartModel;





	@SuppressWarnings("deprecation")
	@ManagedBean
	@SessionScoped
	public class SliderView {
	    private PieChartModel model;
	    Entities entities;
	
	 
	    private int number1;
		private float number2 ;
	    private int number3;

	    public int getNumber1() {
	        return number1;
	    }

	    public void setNumber1(int number1) {
	        this.number1 = number1;
	    }

	    public float getNumber2() {
	        return number2;
	    }

	    public void setNumber2(float number2) {
	        this.number2 = number2;
	    }

	    public int getNumber3() {
	        return number3;
	    }

	    public void setNumber3(int number3) {
	        this.number3 = number3;
	    }

	
	    public void onInputChanged(ValueChangeEvent event) {
	    	this.number3 =(int) event.getNewValue();
	        FacesMessage message = new FacesMessage("Input Changed", "Value: " + event.getNewValue());
	        FacesContext.getCurrentInstance().addMessage(null, message);
	    }

	    public void onSlideEnd(SlideEndEvent event) {
	        FacesMessage message = new FacesMessage("Slide Ended", "Value: " + event.getValue());
	        FacesContext.getCurrentInstance().addMessage(null, message);
	    }
	      
	    public SliderView() {
	    	super();
	    	// TODO Auto-generated constructor stub
	    }
	    
	    public SliderView(int number1, float number2, int number3) {
	    	super();
	    	this.number1 = number1;
	    	this.number2 = number2;
	    	this.number3 = number3;
	    }
	    
	    
	}


