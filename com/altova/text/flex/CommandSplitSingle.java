////////////////////////////////////////////////////////////////////////
//
// CommandSplitSingle.java
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

public class CommandSplitSingle extends Command {
	protected Splitter splitter;
	protected Command first;
	protected int orientation;
	protected int offset;
		
	public CommandSplitSingle(String name, Splitter splitter, int orientation, int offset) {
		super(name);
		this.splitter = splitter;
		this.orientation = orientation;
		this.offset = offset;
	}
	
	public void setFirst(Command first) {
		this.first = first;
	}
	
	public boolean readText(DocumentReader doc) {
		Range range = new Range(doc.getRange());
		if (orientation == 1 && containsMultipleLines(range))
			return readTextMultilineVertical(doc);
		
		Range firstRange = splitter.split(range);
		
		DocumentReader firstDoc = new DocumentReader(doc, firstRange);
		DocumentReader restDoc = new DocumentReader(doc, range); 
		
		return writeNodeAndCallChildren(firstDoc, restDoc);
	}
	
	protected boolean containsMultipleLines(Range range) {
		Range range2 = new Range(range);
		lineSplitter.split(range2);
		return range2.isValid();
	}

	protected boolean containsMultipleLines(StringBuffer str) {
		Range range = new Range(str.toString());
		return containsMultipleLines(range);
	}

	private boolean writeNodeAndCallChildren(DocumentReader firstDoc, DocumentReader restDoc) {
		firstDoc.getOutputTree().enterElement(getName(), ITextNode.Group);
		if (first != null)
			first.readText(firstDoc);
		super.readText(restDoc);
		firstDoc.getOutputTree().leaveElement(getName());
		return true;
	}
	
	static protected SplitLines lineSplitter = new SplitLines(1);
	
	private boolean readTextMultilineVertical(DocumentReader doc) {
		StringBuffer leftCol = new StringBuffer();
		StringBuffer rightCol = new StringBuffer();

		Range range = new Range(doc.getRange());
		splitMultilineVertical(range, leftCol, rightCol);

		DocumentReader firstDoc = new DocumentReader(leftCol.toString(), doc.getOutputTree());
		DocumentReader restDoc = new DocumentReader(rightCol.toString(), doc.getOutputTree());

		return writeNodeAndCallChildren(firstDoc, restDoc);
	}

	protected void splitMultilineVertical(Range range, StringBuffer left, StringBuffer right)
	{
		while (range.isValid()) {
			Range line = lineSplitter.split(range);
			Range leftRange = splitter.split(line);

			if (leftRange.endsWith(CR) && line.startsWith(LF)) {
				leftRange.end--;
				line.start--;
			}

			if (left != null) {
				leftRange.appendTo(left);
				if (!leftRange.endsWith(CR) && !leftRange.endsWith(LF))
					left.append("\r\n");
			}

			if (right != null) {
				if (line.isValid())
					line.appendTo(right);
				else
					right.append("\r\n");
			}
		}
	}
	
	public boolean writeText(DocumentWriter doc) {
		TextNodeList children = doc.getCurrentNode().getChildren().filterByName(getName());
		for (int i = 0; i < children.size(); ++i) {
			StringBuffer firstString = new StringBuffer();
			StringBuffer restString = new StringBuffer();
			
			if (first != null) {
				DocumentWriter firstDoc = new DocumentWriter(children.getAt(i), firstString);
				first.writeText(firstDoc);
			}
			if (next != null) {
				DocumentWriter restDoc = new DocumentWriter(children.getAt(i), restString);
				next.writeText(restDoc);
			}
			
			if (orientation == 1 && (containsMultipleLines(firstString) || containsMultipleLines(restString))) {
				doc.appendText(mergeMultilineVertical(firstString.toString(), restString.toString()));
			} else {
				splitter.prepareUpper(firstString);
				doc.appendText(firstString);
				splitter.appendDelimiter(doc);
				splitter.prepareLower(restString);
				doc.appendText(restString);
			}
		}
		return true;
	}
	
	private String mergeMultilineVertical(String left, String right) {
		StringBuffer result = new StringBuffer();
		Range leftRange = new Range(left);
		Range rightRange = new Range(right);

		while (leftRange.isValid() || rightRange.isValid()) {
			Range leftLine = lineSplitter.split(leftRange);
			Range rightLine = lineSplitter.split(rightRange);
			if (leftLine.endsWith(LF))
				leftLine.end--;
			if (leftLine.endsWith(CR))
				leftLine.end--;
			if (offset >= 0) {
				result.append(leftLine.toString());
				if (leftLine.length() < offset) {
					for (int i = offset - leftLine.length(); i > 0; --i)
						result.append(' ');
				}
				result.append(rightLine.toString());
			}
			else {
				result.append(leftLine.toString());
				if (rightLine.length() < - offset) {
					for (int i = - offset - rightLine.length(); i > 0; --i)
						result.append(' ');
				}
				result.append(rightLine.toString().substring(Math.max(0, rightLine.length() + offset)));
			}
		}
		return result.toString();
	}
}
