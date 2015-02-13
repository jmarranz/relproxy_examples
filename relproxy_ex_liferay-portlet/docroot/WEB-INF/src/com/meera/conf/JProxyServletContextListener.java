package com.meera.conf;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaFileObject;

import com.innowhere.relproxy.RelProxyOnReloadListener;
import com.innowhere.relproxy.jproxy.JProxy;
import com.innowhere.relproxy.jproxy.JProxyCompilerListener;
import com.innowhere.relproxy.jproxy.JProxyConfig;
import com.innowhere.relproxy.jproxy.JProxyDiagnosticsListener;
import com.innowhere.relproxy.jproxy.JProxyInputSourceFileExcludedListener;
import com.meera.liferaymvc.EmployeeLiferayMVC;
import com.meera.liferaymvc.EmployeeLiferayMVCDelegate;

public class JProxyServletContextListener implements ServletContextListener 
{

	@Override
	public void contextInitialized(ServletContextEvent evt) 
	{
        System.out.println("JProxyServletContextListener contextInitialized");
        
        ServletContext context = evt.getServletContext();

	    String configPath = context.getRealPath("/") + "/WEB-INF/conf_relproxy.properties";
	    File configFile = new File(configPath);
	    if (!configFile.exists())
	    	throw new RuntimeException("Missing /WEB-INF/conf_relproxy.properties needed to specify input source folder for RelProxy");
	    
	    String inputSourcePath = null;
	    String tomcatLibPath = null;
	    FileReader input = null;
		try 
		{
			input = new FileReader(configFile.getAbsolutePath());
		    Properties prop = new Properties();
		    prop.load(input);
		    inputSourcePath = prop.getProperty("inputSourcePath");
		    tomcatLibPath = prop.getProperty("tomcatLibPath");
		}
		catch (Exception ex) { throw new RuntimeException(ex); }
		finally
		{
		    if (input != null)
				try { input.close(); } catch (IOException ex) {	throw new RuntimeException(ex);	}
		}

	    if (inputSourcePath == null)
	    	throw new RuntimeException("Missing inputSourcePath in conf_relproxy.properties");        
	    if (tomcatLibPath == null)
	    	throw new RuntimeException("Missing tomcatLibPath in conf_relproxy.properties");        
             
	    tomcatLibPath += File.separatorChar + "ext" + File.separatorChar + "portlet.jar";
	    
        JProxyInputSourceFileExcludedListener excludedListener = new JProxyInputSourceFileExcludedListener()
        {
            @Override
            public boolean isExcluded(File file, File rootFolderOfSources)
            {
                String absPath = file.getAbsolutePath();
                if (file.isDirectory())
                {            	
                    return absPath.endsWith(File.separatorChar + "conf");
                }
                else
                {
                    return absPath.endsWith(EmployeeLiferayMVC.class.getSimpleName() + ".java") ||
                    	   absPath.endsWith(EmployeeLiferayMVCDelegate.class.getSimpleName() + ".java");
                }                
            }            
        };
        

        String classFolder = null; // Optional: context.getRealPath("/") + "/WEB-INF/classes";
        Iterable<String> compilationOptions = Arrays.asList(new String[]{"-source","1.6","-target","1.6"});
        long scanPeriod = 300;
        
        RelProxyOnReloadListener proxyListener = new RelProxyOnReloadListener() {
            @Override
            public void onReload(Object objOld, Object objNew, Object proxy, Method method, Object[] args) {
                System.out.println("Reloaded " + objNew + " Calling method: " + method);
            }        
        };
        
        JProxyCompilerListener compilerListener = new JProxyCompilerListener() {
            @Override
            public void beforeCompile(File file) {
                System.out.println("Before compile: " + file);
            }

            @Override
            public void afterCompile(File file) {
                System.out.println("After compile: " + file);
            } 
        };   
        
        JProxyDiagnosticsListener diagnosticsListener = new JProxyDiagnosticsListener()
        {
            @Override
            public void onDiagnostics(DiagnosticCollector<JavaFileObject> diagnostics)
            {
                List<Diagnostic<? extends JavaFileObject>> diagList = diagnostics.getDiagnostics();                
                int i = 1;
                for (Diagnostic<? extends JavaFileObject> diagnostic : diagList)
                {
                   System.err.println("Diagnostic " + i);
                   System.err.println("  code: " + diagnostic.getCode());
                   System.err.println("  kind: " + diagnostic.getKind());
                   System.err.println("  line number: " + diagnostic.getLineNumber());                   
                   System.err.println("  column number: " + diagnostic.getColumnNumber());
                   System.err.println("  start position: " + diagnostic.getStartPosition());
                   System.err.println("  position: " + diagnostic.getPosition());                   
                   System.err.println("  end position: " + diagnostic.getEndPosition());
                   System.err.println("  source: " + diagnostic.getSource());
                   System.err.println("  message: " + diagnostic.getMessage(null));
                   i++;
                }
            }
        };
        
        JProxyConfig jpConfig = JProxy.createJProxyConfig();
        jpConfig.setEnabled(true)
                .setRelProxyOnReloadListener(proxyListener)
                .setInputPath(inputSourcePath)
                .setRequiredExtraJarPaths(new String[]{tomcatLibPath})
                .setJProxyInputSourceFileExcludedListener(excludedListener)
                .setScanPeriod(scanPeriod)
                .setClassFolder(classFolder)
                .setCompilationOptions(compilationOptions)
                .setJProxyCompilerListener(compilerListener)                
                .setJProxyDiagnosticsListener(diagnosticsListener);
        
        JProxy.init(jpConfig);
	}

	@Override
	public void contextDestroyed(ServletContextEvent evt) 
	{
        System.out.println("JProxyServletContextListener contextDestroyed");		
		
		JProxy.stop();		
	}

}
