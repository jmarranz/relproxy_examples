<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<portlet:defineObjects />
<portlet:renderURL  var="addEmployee" windowState="normal">
<portlet:param name="mvcPath" value="/html/jsps/addEmployee.jsp"/>
</portlet:renderURL>
<h1>Welcome to Liferay MVC Employee Portlet</h1>
<a href="<%=addEmployee.toString()%>">Add Employee</a><br/>
