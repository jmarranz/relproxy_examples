Liferay Example (v6.2) in Eclipse
========

Description
------
This example creates a new portlet and shows how to avoid redeploys and context reloading (and of course servlet container restarts) using RelProxy in development time.

Eclipse 4.4 (Luna), liferay-portal-6.2-ce-ga3 Tomcat and liferay-plugins-sdk-6.2 are used. 


URL (using Tomcat on port 8080) 
------

[http://localhost:8080](http://localhost:8080 "URL")

How to import this project AS IS in Eclipse and Liferay installation 
------

+ Download the project and copy the folder relproxy_ex_liferay-portlet to the folder (liferay-plugins-sdk-6.2 folder)/portlets/

+ In Eclipse Liferay Perspective import the project using File / New / Liferay Project from Existing Source  

Project creation from scratch
------

+ Install the Liferay IDE plugin in Eclipse via Eclipse Marketplace. Now you should use Liferay Perspective.

+ Download and decompress the Liferay-Tomcat 7 bundle (liferay-portal-6.2-ce-ga3 Tomcat in this example)

+ Associate the Tomcat 7 of the Liferay bundle in Eclipse: view Servers / Right button / New / Server

	Alternative through: Window / Preferences / Liferay / Create a new Liferay runtime environment

+ Download and decompress the Liferay Plugins SDK (liferay-plugins-sdk-6.2 in this example)

+ Configure in Eclipse the Liferay Plugins SDK: Window / Preferences / Liferay / Configure installed Liferay Plugin SDKs / Add (select the liferay-plugins-sdk-6.2 folder)

+ In Servers view, start the "Liferay v6.2 CE Server (Tomcat 7) at localhost"

+ Go to http://localhost:8080 and click on "Finish Configuration" and "Go to My Portal", finish configuration

+ Follow the steps of this tutorial 

  [http://www.liferaysavvy.com/2014/05/liferay-mvc-portlet-development-with.html](http://www.liferaysavvy.com/2014/05/liferay-mvc-portlet-development-with.html "URL")

  Until "Create Liferay Portlet Project form existing source with Liferay IDE"

  Modifications:

    * Project Name and Display Name: relproxy_ex_liferay ("-portlet" sufix will be added by Liferay)

    * To avoid the problem of superclass com.liferay.util.bridges.mvc.MVCPortlet not found, cancel the "New Liferay Portlet" dialog and add util-bridges-6.2.2.jar downloaded from [http://repo1.maven.org/maven2/com/liferay/portal/util-bridges/](http://repo1.maven.org/maven2/com/liferay/portal/util-bridges/)  and copy to (liferay-plugins-sdk-6.2 folder)/portlets/relproxy_ex_liferay-portlet/docroot/WEB-INF/lib 
 and press F5 to refresh. Execute again "New Liferay Portlet" and follow instructions of the tutorial. This fix is taken from this [link](http://stackoverflow.com/questions/9964583/the-import-com-liferay-util-bridges-cannot-be-resolved)

    * You will need to add this portlet project to your Liferay Tomcat by clicking in Servers view and right click, click "Add and Remove...", select relproxy_ex_liferay-portlet and click Add.

    * Complete all source changes before executing Ant deploy, Liferay start and adding the portlet to the Welcome page. The pass of the default user "test@liferay.com" (Joe Bloggs) is "test"    

  Notes: 

    * The tutorial has a typo, "displayEmployees.jsp" is wrong, must be "displayEmployee.jsp" without "s".
    
    * docroot/view.jsp and docroot/html/employeeliferaymvc/view.jsp are not executed and can be deleted.

+ To avoid auto class reloading of Tomcat when saving a Java class: double click on "Liferay v6.2 CE Server (Tomcat 7)", Publishing / Never publish automatically.

+ Add relproxy-x.y.z.jar to docroot/WEB-INF/lib (v0.8.4 or upper), select the project and press F5 to refresh. 

+ Add to the end of file docroot/html/jsps/displayEmployee.jsp

```html
<br />
<portlet:renderURL  var="back" windowState="normal">
<portlet:param name="mvcPath" value="/html/jsps/view.jsp"/>
</portlet:renderURL>
<a href="<%=back.toString()%>">Back</a>
```

+ Add a new file docroot/WEB-INF/conf_relproxy.properties with two entries (F5 to refresh):

  inputSourcePath=(liferay-plugins-sdk-6.2 folder)/portlets/relproxy_ex_liferay-portlet/docroot/WEB-INF/src
  
  tomcatLibPath=(liferay-portal-6.2-ce-ga3 folder)/tomcat-7.0.42/lib

+ Add the Java file com/meera/conf/JProxyServletContextListener.java 

+ Register this listener in web.xml adding to the end:

```xml
	<listener>
	    <listener-class>com.meera.conf.JProxyServletContextListener</listener-class>
	</listener>
```

+ Modify EmployeeLiferayMVC accordingly to this example

+ Add com/meera/liferaymvc/EmployeeLiferayMVCDelegate.java and  com/meera/liferaymvc/EmployeeLiferayMVCDelegateImpl.java

+ Execute Ant build.xml - deploy task

+ Right click on "Liferay v6.2 CE Server (Tomcat 7)" and select Publish

+ Try to use the portlet, add a "new employee"  

+ Try to modify EmployeeLiferayMVCDelegateImpl and reload the result page for instance:

```java
  employeeMap.put("employeeName", employeeName + " IS THE BEST!");
```
  Of course this is a very simple modification. Read the manual to know the limits.


