package com.meera.liferaymvc;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

public interface EmployeeLiferayMVCDelegate {

    public void addEmployee(ActionRequest actionRequest,
            ActionResponse actionResponse) throws IOException, PortletException;	
}
