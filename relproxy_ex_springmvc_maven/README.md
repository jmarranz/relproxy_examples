
Spring MVC v4 Example Maven based in Eclipse
========

URL (using Tomcat on port 8080) 
------

[http://localhost:8080/relproxy_ex_springmvc_maven/](http://localhost:8080/relproxy_ex_springmvc_maven/ "URL")


Project creation from scratch
------

+ Follow the steps of:

  http://www.programcreek.com/2014/02/spring-mvc-helloworld-using-maven-in-eclipse/

  Modifications to the tutorial (make them before executing "Run on Server":
  
  This example uses:
  
	Group Id: com.innowhere
	Artifact Id: relproxy_ex_springmvc_maven
	Package: com.innowhere.relproxyexspmvcmaven  (not really used)

  This example uses Tomcat v7 install first and associate to Eclipse first. 

  After change pom.xml, change again the <finalName> to:
  
  	<build>
		<finalName>relproxy_ex_springmvc_maven</finalName>
	</build>

  In Project Properties / Java Build Path / Libraries, ensure a JDK v1.6 or upper is defined (by default is 1.5 not valid for RelProxy and Tomcat v7)  

  In Project Properties / Java Compiler, ensure "Compiler compliance level" is 1.6 or upper (RelProxy is compiled with v1.6 level).

+ More required steps:

+ Disable context reloading of your project in the associated Tomcat (see the RelProxy Manual if necessary)!!!

+ Add relproxy-x.y.z.jar to WEB-INF/lib

+ Add to the pom.xml (used in this example relproxy-0.8.2.jar)

    <dependency>
        <groupId>com.innowhere</groupId>
        <artifactId>relproxy</artifactId>
        <version>0.8.2</version>
        <scope>system</scope>
        <systemPath>${basedir}/src/main/webapp/WEB-INF/lib/relproxy-0.8.2.jar</systemPath>
    </dependency>    


+ Add this relproxy-0.8.2.jar jar to Project Properties / Java Build Path / Add JAR

+ Add com.programcreek.helloworld.conf.JProxyServletContextListener Java file from this example.

+ Register JProxyServletContextListener in web.xml adding this <listener> BEFORE org.springframework.web.context.ContextLoaderListener
  listener declaration:

   <listener>
		<listener-class>com.programcreek.helloworld.conf.JProxyServletContextListener</listener-class>
   </listener>

+ Create an archive with name conf_relproxy.properties in <project root>/src/main/webapp/WEB-INF/conf/ folder, with a property:

  webapp_folder=<project root>

  Example: webapp_folder=/home/iamoneuser/projects/relproxy_ex_springmvc_maven
  In Windows use \\ bars

+ Replace HelloWorldController.java and helloworld.jsp code with code of the same files from this example.

+ Add com.programcreek.helloworld.controller.HelloWorldControllerDelegate and com.programcreek.helloworld.controller.HelloWorldControllerDelegateImpl from this example.
 
+ To test whether everything is working try to change in HelloWorldControllerDelegateImpl something like:

   1) mv.addObject("message", message); => mv.addObject("message", message + " HELLO!!");  and reload the page
   2) "<br>Next Random..." => "<br>THIS IS THE Next Random..." no need of page reload, class reload is triggered by a timed AJAX pull.
   
   