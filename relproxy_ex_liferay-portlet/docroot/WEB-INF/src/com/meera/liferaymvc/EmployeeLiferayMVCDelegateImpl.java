package com.meera.liferaymvc;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

import com.liferay.portal.kernel.util.ParamUtil;

/**
 * Portlet implementation class EmployeeLiferayMVC
 */
public class EmployeeLiferayMVCDelegateImpl implements EmployeeLiferayMVCDelegate {
 
	protected EmployeeLiferayMVC parent;
	
	public EmployeeLiferayMVCDelegateImpl() // required by RelProxy
	{
	}	
	
	public EmployeeLiferayMVCDelegateImpl(EmployeeLiferayMVC parent)
	{
		this.parent = parent;
	}
	
    public void addEmployee(ActionRequest actionRequest,
            ActionResponse actionResponse) throws IOException, PortletException {

    	String employeeName = ParamUtil.getString(actionRequest, "employeeName");
    	String employeeAddress = ParamUtil.getString(actionRequest,
                  "employeeAddress");
    	Map<String, String> employeeMap = new HashMap<String, String>();
    	employeeMap.put("employeeName", employeeName);
    	employeeMap.put("employeeAddress", employeeAddress);
    	actionRequest.setAttribute("employeeMap", employeeMap);
    	actionResponse.setRenderParameter("mvcPath","/html/jsps/displayEmployee.jsp");
    }
}
