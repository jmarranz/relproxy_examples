package com.example.relproxy_ex_vaadin;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaFileObject;

import java.lang.reflect.Method;

import com.innowhere.relproxy.RelProxyOnReloadListener;
import com.innowhere.relproxy.jproxy.JProxy;
import com.innowhere.relproxy.jproxy.JProxyCompilerListener;
import com.innowhere.relproxy.jproxy.JProxyConfig;
import com.innowhere.relproxy.jproxy.JProxyDiagnosticsListener;
import com.innowhere.relproxy.jproxy.JProxyInputSourceFileExcludedListener;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;



@SuppressWarnings("serial")
@Theme("relproxy_ex_vaadin")
public class Relproxy_ex_vaadinUI extends UI {

	protected volatile VaadinUIDelegate delegate;	
	
	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = Relproxy_ex_vaadinUI.class)
	public static class Servlet extends VaadinServlet 
	{
		public void init(ServletConfig config) throws ServletException {
			super.init(config);
								
			ServletContext context = config.getServletContext();
			
		    String configPath = context.getRealPath("/") + "/WEB-INF/conf_relproxy.properties";
		    File configFile = new File(configPath);
		    if (!configFile.exists())
		    	throw new RuntimeException("Missing /WEB-INF/conf_relproxy.properties needed to specify input source folder for RelProxy");
		    
		    String inputSourcePath = null;
		    FileReader input = null;
			try 
			{
				input = new FileReader(configFile.getAbsolutePath());
			    Properties prop = new Properties();
			    prop.load(input);
			    inputSourcePath = prop.getProperty("inputSourcePath");
			}
			catch (Exception ex) { throw new RuntimeException(ex); }
			finally
			{
			    if (input != null)
					try { input.close(); } catch (IOException ex) {	throw new RuntimeException(ex);	}
			}

		    if (inputSourcePath == null)
		    	throw new RuntimeException("Missing inputSourcePath");
		    
	        if (!new File(inputSourcePath).exists())
	        {
	            System.out.println("RelProxy is disabled, detected production mode ");
	            return;
	        }		    
		    
		    boolean enabled = true; 
		    			    
		    
		    JProxyInputSourceFileExcludedListener excludedListener = new JProxyInputSourceFileExcludedListener()
		    {
				@Override
				public boolean isExcluded(File file, File rootFolder) {
					String absPath = file.getAbsolutePath();				
					return absPath.endsWith(VaadinUIDelegate.class.getSimpleName() + ".java") || 
						   absPath.endsWith(Relproxy_ex_vaadinUI.class.getSimpleName() + ".java"); 						
				}
		    	
		    };
		    
		    String classFolder = null; // Optional: context.getRealPath("/") + "/WEB-INF/classes";
		    Iterable<String> compilationOptions = Arrays.asList(new String[]{"-source","1.6","-target","1.6"});
		    long scanPeriod = 200;
	
		    RelProxyOnReloadListener proxyListener = new RelProxyOnReloadListener() {
		        public void onReload(Object objOld, Object objNew, Object proxy, Method method, Object[] args) {
		            System.out.println("Reloaded " + objNew + " Calling method: " + method);
		        }
		    };
		    
	        JProxyCompilerListener compilerListener = new JProxyCompilerListener(){
	            @Override
	            public void beforeCompile(File file)
	            {
	                System.out.println("Before compile: " + file);
	            }
	
	            @Override
	            public void afterCompile(File file)
	            {
	                System.out.println("After compile: " + file);
	            } 
	        }; 
	        
		    JProxyDiagnosticsListener diagnosticsListener = new JProxyDiagnosticsListener()
		    {
		        public void onDiagnostics(DiagnosticCollector<javax.tools.JavaFileObject> diagnostics)
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
		    jpConfig.setEnabled(enabled)
		            .setRelProxyOnReloadListener(proxyListener)
		            .setInputPath(inputSourcePath)
		            .setJProxyInputSourceFileExcludedListener(excludedListener)
		            .setScanPeriod(scanPeriod)
		            .setClassFolder(classFolder)
		            .setCompilationOptions(compilationOptions)
		            .setJProxyCompilerListener(compilerListener)
		            .setJProxyDiagnosticsListener(diagnosticsListener);
	
		    JProxy.init(jpConfig);
			
		}		
		
	}

	@Override
	protected void init(VaadinRequest request) {
		
		if (delegate == null)
		{
			synchronized(this)
			{
				if (delegate == null)
					this.delegate = JProxy.create(new VaadinUIDelegateImpl(this), VaadinUIDelegate.class);
			}
		}
		
		delegate.init(request);

	}

}