/*
 * Copyright (c) 2003-2011 MarkLogic Corporation. All rights reserved.
 */
package com.marklogic.mapreduce.functions;

/**
 * Class to encapsulate input and generate query based on 
 * cts:field-word-match() function.
 * 
 * @author jchen
 */
public abstract class FieldWordMatch extends ValueOrWordMatchFunction {

    /**
     * Get an array of field names to be used with the function.
     * 
     * @return an array of field names.
     */
    public abstract String[] getFieldNames();
    
    @Override
    void appendFunctionName(StringBuilder buf) {
        buf.append("cts:field-word-match");
    }
    
    @Override
    void appendNamesParams(StringBuilder buf) {
        String[] fieldNames = getFieldNames();
        buf.append("(");
        for (int i = 0; i < fieldNames.length; i++) {
            if (i > 0) {
                buf.append(",");
            }
            buf.append(fieldNames[i]);
        }
        buf.append("),\n");
    }
}
