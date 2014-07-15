////////////////////////////////////////////////////////////////////////
//
// CommandSplitMultiple.java
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

package com.altova.text.flex;

import com.altova.text.*;
import java.util.ArrayList;

public class CommandSplitMultiple extends CommandSplitSingle {
		
	public CommandSplitMultiple(String name, Splitter splitter, int orientation, int offset) {
		super(name, splitter, orientation, offset);
	}
	
	public boolean readText(DocumentReader doc) {
		if (!hasNext())
			return true;
		
		Range range = new Range(doc.getRange());
		
		if (orientation == 1 && containsMultipleLines(range))
			return readTextMultilineVertical(doc);

		while (range.isValid()) {
			Range partRange = splitter.split(range);
			DocumentReader part = new DocumentReader(doc, partRange);
			doc.getOutputTree().enterElement(getName(), ITextNode.Group);
			next.readText(part);
			doc.getOutputTree().leaveElement(getName());
		}
		return true;
	}
	
	private boolean containsNonEmptyLine(String s) {
		for (int i = 0; i < s.length(); ++i) {
			if (s.charAt(i) != CR && s.charAt(i) != LF)
				return true;
		}
		return false;
	}

	private boolean readTextMultilineVertical(DocumentReader doc) {
		String text = doc.getRange().toString();

		while (containsNonEmptyLine(text)) {
			StringBuffer leftCol = new StringBuffer();
			StringBuffer rest = new StringBuffer();

			Range range = new Range(text);
			splitMultilineVertical(range, leftCol, rest);

			doc.getOutputTree().enterElement(name, ITextNode.Group);
			DocumentReader part = new DocumentReader(leftCol.toString(), doc.getOutputTree());
			next.readText(part);
			doc.getOutputTree().leaveElement(name);

			text = rest.toString();
		}
		return true;
	}
	
	public boolean writeText(DocumentWriter doc) {
		if (orientation == 1 && next != null) {
			return writeTextMultilineVertical(doc);
		}
		TextNodeList children = doc.getCurrentNode().getChildren().filterByName(getName());
		for (int i = 0; i < children.size(); ++i) {
			if (i != 0)
				splitter.appendDelimiter(doc);
			
			StringBuffer partString = new StringBuffer();
			DocumentWriter part = new DocumentWriter(children.getAt(i), partString);
			if (next != null)
				next.writeText(part);
			splitter.prepareUpper(partString);
			doc.appendText(partString);
		}
		return true;
	}
	
	private boolean writeTextMultilineVertical(DocumentWriter doc) {
		ArrayList<StringBuffer> lines = new ArrayList<StringBuffer>();
		TextNodeList children = doc.getCurrentNode().getChildren().filterByName(getName());
		for (int i = 0; i < children.size(); ++i) {
			StringBuffer col = new StringBuffer();
			DocumentWriter colWriter = new DocumentWriter(children.getAt(i), col);
			next.writeText(colWriter);

			boolean isLast = (i == children.size() - 1);

			int lineno = 0;
			Range range = new Range(col.toString());
			while (range.isValid())	{
				Range lineRange = lineSplitter.split(range);
				if (lineRange.endsWith(LF))
					lineRange.end--;
				if (lineRange.endsWith(CR))
					lineRange.end--;
				StringBuffer line = new StringBuffer(lineRange.toString());
				if (!isLast && line.length() < offset) {
					for (int i2 = offset - line.length(); i2 > 0; --i2)
						line.append(' ');
				}
				
				++lineno;
				if (lines.size() < lineno)
					lines.add(new StringBuffer());
				((StringBuffer)lines.get(lineno-1)).append(line);
			}
		}

		for (int i3 = 0; i3 < lines.size(); ++i3) {
			doc.appendText(lines.get(i3).toString() + CR + LF);
		}

		return true;
	}
}
