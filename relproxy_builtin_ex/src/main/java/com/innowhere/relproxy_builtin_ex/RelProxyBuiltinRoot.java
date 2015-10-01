package com.innowhere.relproxy_builtin_ex;

import com.innowhere.relproxy_builtin_ex.impl.RelProxyBuiltinImpl;

/**
 *
 * @author jmarranz
 */
public class RelProxyBuiltinRoot
{
    private final static RelProxyBuiltinImpl SINGLETON = new RelProxyBuiltinImpl();
    
    public static RelProxyBuiltin get()
    {
        return SINGLETON;
    }
}
