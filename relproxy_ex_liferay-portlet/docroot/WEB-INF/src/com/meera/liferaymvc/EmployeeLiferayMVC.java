package com.meera.liferaymvc;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

import com.innowhere.relproxy.jproxy.JProxy;
import com.liferay.util.bridges.mvc.MVCPortlet;

/**
 * Portlet implementation class EmployeeLiferayMVC
 */
public class EmployeeLiferayMVC extends MVCPortlet {
	
	protected EmployeeLiferayMVCDelegate delegate;
	
	public EmployeeLiferayMVC()
	{
		this.delegate = JProxy.create(new EmployeeLiferayMVCDelegateImpl(this), EmployeeLiferayMVCDelegate.class);	
	}
	
    public void addEmployee(ActionRequest actionRequest,
            ActionResponse actionResponse) throws IOException, PortletException {
    	
    	delegate.addEmployee(actionRequest, actionResponse);
    }
}
