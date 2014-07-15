////////////////////////////////////////////////////////////////////////
//
// ParserException.java
//
// This file was generated by MapForce 2012r2sp1.
//
// YOU SHOULD NOT MODIFY THIS FILE, BECAUSE IT WILL BE
// OVERWRITTEN WHEN YOU RE-RUN CODE GENERATION.
//
// Refer to the MapForce Documentation for further details.
// http://www.altova.com/mapforce
//
////////////////////////////////////////////////////////////////////////

package com.altova.text.tablelike.csv;

import com.altova.text.tablelike.MappingException;

public class ParserException extends MappingException {
    int m_LineNumber = 0;

    public int getLineNumber() {
        return m_LineNumber;
    }

    ParserException(BadFormatException x, int linenumber) {
        super(x.getMessage() + " at line #" + linenumber);
        m_LineNumber = linenumber;
    }
}