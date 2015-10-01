package com.innowhere.relproxy_builtin_ex;

import com.innowhere.relproxy.jproxy.JProxyScriptEngine;
import java.io.InputStream;
import java.io.PrintStream;

/**
 *
 * @author jmarranz
 */
public interface RelProxyBuiltin
{
    public JProxyScriptEngine getJProxyScriptEngine();
    
    public void addOutputListener(OutputListener listener);    
    public void removeOutputListener(OutputListener listener);    
    public int getOutputListenerCount();    
    
    public void addCommandListener(CommandListener listener);
    public void removeCommandListener(CommandListener listener);    
    public int getCommandListenerCount();     
    
    public void runLoop(InputStream in,PrintStream out);
}
