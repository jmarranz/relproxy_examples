package com.programcreek.helloworld.controller;

import java.util.Date;
import java.util.Random;

import org.springframework.web.servlet.ModelAndView;

public class HelloWorldControllerDelegateImpl implements HelloWorldControllerDelegate {

	HelloWorldController parent;
	String message = "Welcome to Spring MVC!";
    Random rand = new Random();	
	
    public HelloWorldControllerDelegateImpl() // Required by RelProxy
    {
    }    
    
    public HelloWorldControllerDelegateImpl(HelloWorldController parent)
    {
    	this.parent = parent;
    }
    
    @Override
	public ModelAndView showMessage(String name) 
	{
		ModelAndView mv = new ModelAndView("helloworld");
		mv.addObject("message", message);
		mv.addObject("name", name);
		
		return mv;
	}
	
    @Override    
    public String getTime() 
    {
        float r = rand.nextFloat() * 100;
        String result = "<br>Next Random # is <b>" + r + "</b>. Generated on <b>" + new Date().toString() + "</b>";
        return result;
    }	
}	

