
JSF Example (PrimeFaces) in Eclipse
========

Description
------
This example is interesting because it uses an interface with several methods implemented by the reloadable "session singleton"
(usually we register application scope singletons).

To reset the session just remove the session cookie in your browser (sure you know how to do it).

URL (using Tomcat on port 8080) 
------

[http://localhost:8080/relproxy_ex_jsf/faces/index.xhtml](http://localhost:8080/relproxy_ex_jsf/faces/index.xhtml "URL")


Project creation from scratch
------

+ Follow the steps of:

http://www.journaldev.com/2990/primefaces-5-jsf-2-beginners-example-tutorial

+ Java Build Path => add Tomcat v7 Library

+ Disable context reloading of your project in the associated Tomcat (see the manual if necessary)!!!

+ Add WebContent/WEB-INF/lib the RelProxy jar (relproxy-x.y.z.jar)

+ How to fix: java.lang.ClassNotFoundException: javax.servlet.jsp.jstl.core.Config

  Add to WebContent/WEB-INF/lib the files jsp-api-2.0.jar and jstl-1.2.jar
  
  http://stackoverflow.com/questions/15113628/java-lang-classnotfoundexception-javax-servlet-jsp-jstl-core-config

+ Add conf/JProxyServletContextListener

+ Register JProxyServletContextListener in web.xml adding to the end:

   <listener>
	<listener-class>com.journaldev.conf.JProxyServletContextListener</listener-class>
   </listener>

+ ViewEmployeesManagedBean => @ApplicationScoped instead of @SessionScoped

  Modify this class with the new code of this example.

+ Add to jsfBeans: ViewEmployeesManagedBeanDelegate and ViewEmployeesManagedBeanDelegateImpl

+ Change something in 
