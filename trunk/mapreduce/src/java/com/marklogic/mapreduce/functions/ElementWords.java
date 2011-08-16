/*
 * Copyright (c) 2003-2011 MarkLogic Corporation. All rights reserved.
 */
package com.marklogic.mapreduce.functions;

/**
 * Class to encapsulate input and generate query based on cts:element-words() 
 * function.
 * 
 * @author jchen
 */
public abstract class ElementWords extends ValuesOrWordsFunction {

    /**
     * Get an array of element QNames to be used with the function.
     * 
     * @return an array of element QNames.
     */
    public abstract String[] getElementNames();
    
    @Override
    void appendFunctionName(StringBuilder buf) {
        buf.append("cts:element-words");
    }
    
    @Override
    void appendNamesParams(StringBuilder buf) {
        String[] elemNames = getElementNames();
        buf.append("(");
        for (int i = 0; i < elemNames.length; i++) {
            if (i > 0) {
                buf.append(",");
            }
            buf.append(elemNames[i]);
        }
        buf.append("),\n");
    }
}
