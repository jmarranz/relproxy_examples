Example of how to embed RelProxy in your Java framework
========

This is an example of how to embed RelProxy (JProxy) in your Java framework (including a simple example) to add support to hot downloadable user classes.

A simple Java framework is created, the package com.innowhere.relproxy_builtin_ex defines the framework and the classes of the package 
com.innowhere.relproxy_builtin_ex_main are a simple use example. You can try to modify the class TestListener in runtime adding for instance 
a new command "same" that does nothing.

The TestListener class define two listeners of both types, one listener is an anonymous inner class to show that this kind of class can be hot reloadable if the
enclosing class is also hot reloadable.

Built with NetBeans and Maven.

