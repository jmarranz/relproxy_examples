package com.innowhere.relproxy_builtin_ex_main;

import com.innowhere.relproxy_builtin_ex.CommandListener;
import com.innowhere.relproxy_builtin_ex.OutputListener;
import java.io.PrintStream;

/**
 *
 * @author jmarranz
 */
public class TestListener implements OutputListener
{  
    @Override
    public void write(PrintStream out)
    {
        out.println("uppercase");
        out.println("lowercase");                   
    }

    public CommandListener getCommandListener()
    {
        return new CommandListener()
        {
            @Override
            public void execute(String command,String text,PrintStream out)
            {
                if ("uppercase".equals(command))
                    out.println(text.toUpperCase());
                else if ("lowercase".equals(command))
                    out.println(text.toLowerCase());                               
                else 
                    out.println("Unknown command:" + command);        
            }
        };
    }  
}
