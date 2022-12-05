package com.admaru.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//TODO
public class Tracker extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    
    
       
    public Tracker() {
        super();
    }
    
    public void View() {
    	//view건수 로깅
    	
    }
    
    public void Click() {
    	//
    }
    
    public void q1() {
    	//
    }
    
    public void q2() {
    
    }
    
    public void q3() {
    
    }
    
    public void q4() {
    }
    
    public void skip() {
    
    }
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("==============");
		ConnectionSampleWithPool redis = new ConnectionSampleWithPool();
		redis.setValue("xxxx", "yyyy");
		redis.getValue("xxxx");
		System.out.println("==============end");
	}

}