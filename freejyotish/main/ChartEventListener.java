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
@package freejyotish.main;

/**
 *  Description of the Interface
 *
 *@@author     Michael W. Taft
 *@@created    May 14, 2002
 */
public interface ChartEventListener extends java.util.EventListener
{

	/**
	 *  Description of the Method
	 *
	 *@@param  e  Description of the Parameter
	 */
	public void chartChanged(ChartEvent e);

}

@
