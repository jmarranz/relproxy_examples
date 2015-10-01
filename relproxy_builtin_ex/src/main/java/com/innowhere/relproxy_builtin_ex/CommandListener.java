package com.innowhere.relproxy_builtin_ex;

import java.io.PrintStream;

/**
 *
 * @author jmarranz
 */
public interface CommandListener
{
    public void execute(String command,String input,PrintStream out);    
}
