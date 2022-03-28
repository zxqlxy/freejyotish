head	1.1;
access;
symbols;
locks; strict;
comment	@# @;


1.1
date	2003.05.17.08.18.57;	author santoshs;	state Exp;
branches;
next	;


desc
@@


1.1
log
@Changed the package name from 'FreeJyotish' to 'freejyotish'
@
text
@package freejyotish.main.calc;

import java.util.Hashtable;

/*
*	Created 28 April, 2003 by Michael Taft
*	A new, more flexible type of preferences object
*	From an idea suggested by Ken Lee
*/

public class CalculationPreferences
{
	private Hashtable prefs;
	
	public void setPreference(String name, Object o)
	{
		if (prefs == null) prefs = new Hashtable();
		prefs.put(name, o);
	}
	
	public Object getPreference(String name)
	{
		if (prefs == null) return null;
		return prefs.get(name);
	}
	
	public Hashtable getAllPreferences()
	{
		if (prefs == null) return null;
		return prefs;
	}


}
@
