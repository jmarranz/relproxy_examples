Vaadin Example in Eclipse using the Vaadin plugin
========

Project creation from scratch
------

+ Be sure an app server is already installed and associated to your Eclipse installation 

+ Install the Vaadin Plugin from Eclipse Marketplace

+ Create a new project: New / New Project / New Vaadin 7 Project
  - Name: relproxy_ex_vaadin
  - Base Package Name: com.example.relproxy_ex_vaadin
  - Mark Generate web.xml 

+ Wait until wizard dialog dismisses, it seems hanged but it is just downloading dependencies, be patient 

+ Disable context reloading of your project in the associated Tomcat (see manual if necessary)!!!

+ Add to /WebContent/WEB-INF/lib relproxy-x.y.z.jar

+ Create in WEB-INF/ an archive named conf_relproxy.properties  with something like:

  inputSourcePath=<your path>/relproxy_ex_vaadin/src
  
+ Replace content Relproxy_ex_vaadinUI.java with the source code of this example

+ Add VaadinUIDelegate.java and VaadinUIDelegateImpl.java to the 



