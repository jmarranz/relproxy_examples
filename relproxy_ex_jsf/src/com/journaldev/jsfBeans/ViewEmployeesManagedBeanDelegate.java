package com.journaldev.jsfBeans;

import java.util.List;

import com.journaldev.data.Employee;

public interface ViewEmployeesManagedBeanDelegate 
{
    public void populateEmployeeList(List<Employee> employees);	
    public List<Employee> getEmployees(List<Employee> employees);    
}
