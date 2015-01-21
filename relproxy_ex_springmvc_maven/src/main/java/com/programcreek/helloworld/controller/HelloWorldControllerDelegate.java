package com.programcreek.helloworld.controller;

import org.springframework.web.servlet.ModelAndView;

public interface HelloWorldControllerDelegate {

	public ModelAndView showMessage(String name); 
	
    public String getTime();
}	

