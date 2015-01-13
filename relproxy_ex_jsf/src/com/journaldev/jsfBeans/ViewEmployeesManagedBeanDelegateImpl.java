package com.journaldev.jsfBeans;

import java.util.List;

import com.journaldev.data.Employee;

public class ViewEmployeesManagedBeanDelegateImpl implements ViewEmployeesManagedBeanDelegate
{
    public void populateEmployeeList(List<Employee> employees){ 
    	
        for(int i = 1 ; i <= 10 ; i++){
            Employee emp = new Employee();
            emp.setEmployeeId(String.valueOf(i));
            emp.setEmployeeName("Employee#"+i);
            employees.add(emp);
        }
    }	
	
    public List<Employee> getEmployees(List<Employee> employees) {

		int i = employees.size() + 1;
        Employee emp = new Employee();
        emp.setEmployeeId(String.valueOf(i));
        emp.setEmployeeName("Employee#"+i);
        employees.add(emp);
        
        return employees;        
    }
}
