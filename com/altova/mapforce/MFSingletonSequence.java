/**
 * MFSingletonSequence.java
 *
 * This file was generated by MapForce 2012r2sp1.
 *
 * YOU SHOULD NOT MODIFY THIS FILE, BECAUSE IT WILL BE
 * OVERWRITTEN WHEN YOU RE-RUN CODE GENERATION.
 *
 * Refer to the MapForce Documentation for further details.
 * http://www.altova.com/mapforce
 */

package com.altova.mapforce;

public class MFSingletonSequence implements IEnumerable 
{
	public static class Enumerator implements IEnumerator
	{
		Object item;
		boolean b = true;
		
		public Enumerator(Object item) 
		{ 
			this.item = item; 
		}
		
		public Object current() 
		{ 
			return item; 
		}
		
		public int position() {return 1;}
		
		public boolean moveNext()
		{
			if (b)
			{ 
				b = false; 
				return true; 
			}
			return false;
		}
		
		public void close() {}
	}
	
	Object item;
	
	public MFSingletonSequence(Object item)
	{
		if (item == null)
			throw new Error("NULL is not allowed in MFSingletonSequence.");
			
		this.item = item;
	}
	
	public IEnumerator enumerator() 
	{
		return new Enumerator(item);
	}

}
