<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<portlet:defineObjects />
<portlet:actionURL var="addEmployeeActionURL" windowState="normal" name="addEmployee">
</portlet:actionURL>
<h1> Add Employee</h1>
<form action="<%=addEmployeeActionURL%>" name="emplyeeForm"  method="POST">
Employee Name<br/>
<input  type="text" name="<portlet:namespace/>employeeName" id="<portlet:namespace/>employeeName"/><br/>
Employee Address<br/>
<input type="text" name="<portlet:namespace/>employeeAddress" id="<portlet:namespace/>employeeName"/><br/>
<input type="submit" name="addEmployee" id="addEmployee" value="Add Employee"/>
</form>
