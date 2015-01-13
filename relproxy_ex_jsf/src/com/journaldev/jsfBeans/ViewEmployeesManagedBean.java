package com.journaldev.jsfBeans;
 
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
 
import com.innowhere.relproxy.jproxy.JProxy;
import com.journaldev.data.Employee;
 
@ManagedBean
@SessionScoped
public class ViewEmployeesManagedBean 
{
    private List<Employee> employees = new ArrayList<Employee>();
    private ViewEmployeesManagedBeanDelegate delegate;
    
    public ViewEmployeesManagedBean(){
 
    }
 
    @PostConstruct
    public void populateEmployeeList()
    { 
    	this.delegate = JProxy.create(new ViewEmployeesManagedBeanDelegateImpl(), ViewEmployeesManagedBeanDelegate.class);   	
    	delegate.populateEmployeeList(employees);
    }
 
    public List<Employee> getEmployees() 
    {
        return delegate.getEmployees(employees);
    }
 
    public void setEmployees(List<Employee> employees) 
    {
        this.employees = employees;
    }
}