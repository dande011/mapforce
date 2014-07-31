////////////////////////////////////////////////////////////////////////
//
// DataCompletion.java
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

package com.altova.text.edi;

import com.altova.text.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class DataCompletion {
	protected TextDocument m_Document = null;
	protected String m_StructureName = "";

	protected static ITextNode makeSureExists(ITextNode parent, String name) {
		ITextNodeList children = parent.getChildren();
		
		ITextNode result = children.getFirstNodeByName( name );
		if( result == null )
		{
			result = new TextNode(parent, name);
			result.setName(name);
			if (name.equals("GS") || name.equals("GE") || name.equals("ST") || name.equals("SE"))
				result.setNodeClass(ITextNode.Segment);
			else if (name.startsWith("F"))
				result.setNodeClass(ITextNode.DataElement);
			else if (name.startsWith("S"))
				result.setNodeClass(ITextNode.Composite);
			else if ((name.startsWith("U")) && (3 == name.length()))
				result.setNodeClass(ITextNode.Segment);
			else if ((name.startsWith("I")) && (3 == name.length()))
				result.setNodeClass(ITextNode.Segment);
			else
				result.setNodeClass(ITextNode.Group);
		}
		
		return result;
	}
	
	protected boolean hasKid(ITextNode node, String name) {
		return (0 < node.getChildren().filterByName(name).size());
	}

	protected ITextNode getKid(ITextNode node, String name) {
		return node.getChildren().filterByName(name).getAt(0);
	}

	protected void conservativeSetValue(ITextNode node, String value) {
		if (0 == node.getValue().length())
			node.setValue(value);
	}

	protected void conservativeSetValue(ITextNode node, char value) {
		if (0 == node.getValue().length()) {
			StringBuffer tmp = new StringBuffer();
			tmp.append(value);
			node.setValue(tmp.toString());
		}
	}

	protected void conservativeSetValue(ITextNode node, int value) {
		if (0 == node.getValue().length()) {
			StringBuffer tmp = new StringBuffer();
			tmp.append(value);
			node.setValue(tmp.toString());
		}
	}

	protected void conservativeSetValue(ITextNode node, long value) {
		if (0 == node.getValue().length()) {
			StringBuffer tmp = new StringBuffer();
			tmp.append(value);
			node.setValue(tmp.toString());
		}
	}

	protected String getStructureName() {
		return m_StructureName;
	}

	protected DataCompletion(TextDocument textDocument, String structurename) {
		m_Document = textDocument;
		m_StructureName = structurename;
	}

	public abstract void completeData(ITextNode dataroot, Particle rootParticle);

	protected String getCurrentDateAsEDIString(long syntaxLevel) {
		Date now = new Date();
		SimpleDateFormat formatter;
		if (syntaxLevel < 4)
			formatter = new SimpleDateFormat("yyMMdd");
		else
			formatter = new SimpleDateFormat("yyyyMMdd");
		return formatter.format(now);
	}

	protected String getCurrentTimeAsEDIString() {
		Date now = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("HHmm");
		return formatter.format(now);
	}

	protected long getSegmentChildrenCount(ITextNode node) {
		long result = (ITextNode.Segment == node.getNodeClass()) ? 1 : 0;
		ITextNodeList children = node.getChildren();
		for (int i = 0; i < children.size(); ++i)
			result += getSegmentChildrenCount(children.getAt(i));
		return result;
	}

	protected boolean completeMandatory(ITextNode dataNode, Particle particle) {
		ITextNodeList dataChildren = dataNode.getChildren();
		StructureItem currentItem = particle.getNode();
		dataNode.setNodeClass(currentItem.getNodeClass());
		
		if (currentItem.getNodeClass() == ITextNode.DataElement)
			return dataNode.getValue().length() != 0;
		else
		{
			int childCount = dataChildren.size();
			boolean anyExisted = false;
			boolean bContainsSelect = false;

			for (int i= 0; i< currentItem.getChildCount(); i++)
			{
				Particle childParticle = currentItem.child(i);
				bContainsSelect = !bContainsSelect ? childParticle.getNode().getNodeClass() == ITextNode.Select : true;
				int occurs = 0;
				String name = childParticle.getName();
				ITextNodeList filtered = dataChildren.filterByName(name);
				for (int j=0; j<filtered.size(); j++)
				{
					childCount--;
					dataChildren.moveNode(filtered.getAt(j), dataChildren.size());
					boolean childExists = completeMandatory(filtered.getAt(j), childParticle);
					anyExisted |= childExists;
					if (!childExists)
						dataChildren.removeAt(dataChildren.size()-1);
					else
						occurs++;
				}
				
				if (currentItem.getNodeClass() == ITextNode.Group && childParticle.getNode().getNodeClass() != ITextNode.Select && !name.equals("Message"))
				{
					for(; occurs < childParticle.getMinOccurs(); ++occurs)
					{
						ITextNode node = new TextNode(dataNode, name, childParticle.getNode().getNodeClass());
						completeMandatory(node, childParticle);
					}
				}
			}
			
			if( !bContainsSelect )
			{
				while(childCount != 0)
					dataChildren.removeAt(--childCount);
			}
			else
				return true;
			
			return anyExisted;
		}
	}
}
