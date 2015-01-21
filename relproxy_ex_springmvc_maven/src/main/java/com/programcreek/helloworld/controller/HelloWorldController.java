package com.programcreek.helloworld.controller;
 
import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.innowhere.relproxy.jproxy.JProxy;
 
@Controller
public class HelloWorldController {

	protected HelloWorldControllerDelegate delegate;
	   
    public HelloWorldController()
    {

    }
    
    @PostConstruct
    public void init() 
    {   	
		System.out.println("in controller init");    	
    	this.delegate = JProxy.create(new HelloWorldControllerDelegateImpl(this),HelloWorldControllerDelegate.class);
    }    
    
	@RequestMapping("/hello")
	public ModelAndView showMessage(
			@RequestParam(value = "name", required = false, defaultValue = "World") String name) 
	{
		System.out.println("in controller");
		return delegate.showMessage(name);
	}
	
    @RequestMapping(value = "/ajaxtest", method = RequestMethod.POST)
    public @ResponseBody String getTime() 
    {
        //System.out.println("Debug Message from Controller.." + new Date().toString());    	
    	return delegate.getTime();
    }	
}


