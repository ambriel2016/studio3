package com.aptana.editor.ruby.parsing.ast;

import java.util.ArrayList;
import java.util.List;

import com.aptana.editor.ruby.core.IRubyElement;
import com.aptana.editor.ruby.parsing.IRubyParserConstants;
import com.aptana.parsing.ast.IParseNode;
import com.aptana.parsing.ast.ParseBaseNode;

public class RubyElement extends ParseBaseNode implements IRubyElement
{

	private static final String EMPTY = ""; //$NON-NLS-1$
	private int occurrenceCount = 1;

	protected RubyElement()
	{
		super(IRubyParserConstants.LANGUAGE);
	}

	public RubyElement(int start, int end)
	{
		super(IRubyParserConstants.LANGUAGE);
		this.start = start;
		this.end = end;
	}

	@Override
	public String getName()
	{
		return EMPTY;
	}

	public IRubyElement[] getChildrenOfType(int type)
	{
		List<IRubyElement> list = new ArrayList<IRubyElement>();
		IParseNode[] children = getChildren();
		for (IParseNode child : children)
		{
			if (child.getType() == type)
			{
				list.add((IRubyElement) child);
			}
		}
		return list.toArray(new IRubyElement[list.size()]);
	}

	public int getOccurrenceCount()
	{
		return occurrenceCount;
	}

	public void incrementOccurrence()
	{
		occurrenceCount++;
	}

	@Override
	public String toString()
	{
		Object parent = getParent();
		if (parent != null)
		{
			return parent.toString() + "/" + getName(); //$NON-NLS-1$
		}
		return getName();
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof RubyElement))
			return false;

		return getName().equals(((RubyElement) obj).getName());
	}

	@Override
	public int hashCode()
	{
		return 31 * super.hashCode() + getName().hashCode();
	}
}
