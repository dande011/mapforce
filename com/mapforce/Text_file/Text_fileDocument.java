/**
 * Text_fileDocument.java
 *
 * This file was generated by MapForce 2012r2sp1.
 *
 * YOU SHOULD NOT MODIFY THIS FILE, BECAUSE IT WILL BE
 * OVERWRITTEN WHEN YOU RE-RUN CODE GENERATION.
 *
 * Refer to the MapForce Documentation for further details.
 * http://www.altova.com/mapforce
 */

package com.mapforce.Text_file;



import com.altova.text.tablelike.ColumnSpecification;
import com.altova.text.tablelike.Header;
import com.altova.text.tablelike.ISerializer;
import com.altova.text.tablelike.csv.Table;
import com.altova.text.tablelike.csv.Serializer;

public class Text_fileDocument extends Table
{
	protected ISerializer createSerializer()
	{
		Serializer result= new Serializer(this);
		result.getFormat().setAssumeFirstRowAsHeaders(false);
		result.getFormat().setFieldDelimiter(',');
		
		result.getFormat().setQuoteCharacter('\"');
		
		result.getFormat().setRemoveEmpty(true);
		return result;
	}
	protected void initHeader(Header header)
	{
		
		header.add(new ColumnSpecification("Field1"));
	}
	public Text_fileDocument(com.altova.typeinfo.TypeInfo tableType) {
        super( tableType);
    }
}

