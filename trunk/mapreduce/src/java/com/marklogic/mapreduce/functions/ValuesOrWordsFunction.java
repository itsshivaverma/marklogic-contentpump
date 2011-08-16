/*
 * Copyright (c) 2003-2011 MarkLogic Corporation. All rights reserved.
 */
package com.marklogic.mapreduce.functions;

import java.util.Collection;
import java.util.Iterator;

/**
 * Super class for all supported values functions and words function.
 * 
 * @author jchen
 */
public abstract class ValuesOrWordsFunction extends LexiconFunction {

    /**
     * Get the starting value for the function.
     * 
     * @return the starting value for the function.
     */
    public String getStart() {
        return "()";
    }
    
    abstract void appendFunctionName(StringBuilder buf);
    
    abstract void appendNamesParams(StringBuilder buf);
    
    @Override
    public String getInputQuery(Collection<String> nsCol, long start, 
            long end) {
        StringBuilder buf = new StringBuilder();      
        
        buf.append("xquery version \"1.0-ml\"; \n");
        buf.append("xdmp:with-namespaces(("); 
        if (nsCol != null) {
            for (Iterator<String> nsIt = nsCol.iterator(); nsIt.hasNext();) {
                String ns = nsIt.next();
                buf.append('"').append(ns).append('"');
                if (nsIt.hasNext()) {
                    buf.append(',');
                }
            }
        }
        buf.append("),");
        // function name
        appendFunctionName(buf);
        buf.append("(");
        // names
        appendNamesParams(buf);
        // start
        buf.append(getStart());
        buf.append(",");
        // options
        buf.append("(\"skip=").append(start);
        buf.append("\",\"truncate=").append(end).append("\"");
        String[] userOptions = getUserDefinedOptions();
        if (userOptions != null) {
            for (int i = 0; i < userOptions.length; i++) {
                buf.append(",\"").append(userOptions[i]).append("\"");
            }          
        }
        buf.append("),");
        // query
        buf.append(getLexiconQuery()).append("))");
        return buf.toString();
    }

}
