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
 *  Description of the Class
 *
 *@@author     Michael W. Taft
 *@@created    May 15, 2002
 */
public class ChartCenterEvent extends java.util.EventObject
{

	/**
	 *  Constructor for the ChartCenterEvent object
	 *
	 *@@param  center  Description of the Parameter
	 */
	public ChartCenterEvent(IChartCenter center)
	{
		super(center);
		//the source of the event
	}

}

@
