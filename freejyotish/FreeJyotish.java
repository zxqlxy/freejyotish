head	1.2;
access;
symbols;
locks; strict;
comment	@# @;


1.2
date	2003.05.18.11.55.29;	author santoshs;	state Exp;
branches;
next	1.1;

1.1
date	2003.05.17.08.18.58;	author santoshs;	state Exp;
branches;
next	;


desc
@@


1.2
log
@Support for the jgoodies plastic look and feel
@
text
@package freejyotish;

import javax.swing.UIManager;

import freejyotish.gui.FJSplash;
import freejyotish.gui.FJSplit;
import freejyotish.main.ChartCenter;
import freejyotish.main.IChartCenter;
import freejyotish.store.FileCenter;
import freejyotish.store.IFJStorage;

/**
 *  Description of the Class
 *
 *@@author     Michael W. Taft
 *@@created    May 15, 2002
 */
public class FreeJyotish
{

	private IChartCenter center;
	private FJSplit main_frame;
	private IFJStorage storage;

	/**
	 *  Constructor for the FreeJyotish object
	 */
	public FreeJyotish()
	{
		FJSplash sp = new FJSplash();
		sp.show();
		System.out.println("FREEJYOTISH is loading...");
		storage = new FileCenter();
		center = new ChartCenter(storage);
		main_frame = new FJSplit(center, sp, storage);
		main_frame.show();
	}

	/**
	 *  The main program for the FreeJyotish class
	 *
	 *@@param  ganapati  The command line arguments
	 */
	public static void main(String[] ganapati)
	{
		try
		{
			UIManager.setLookAndFeel("com.jgoodies.plaf.plastic.PlasticXPLookAndFeel");
		} catch (Exception e)
		{
		}

		FreeJyotish fj = new FreeJyotish();
	}

}
@


1.1
log
@Changed the package name from 'FreeJyotish' to 'freejyotish'
@
text
@d3 8
a10 3
import freejyotish.main.*;
import freejyotish.gui.*;
import freejyotish.store.*;
d20 1
a20 1
	
a23 2
	

d30 2
a31 2
        FJSplash sp = new FJSplash();
        sp.show();
d35 1
a35 1
		main_frame = new FJSplit( center, sp, storage );
a37 3
	
	

d46 7
a55 2


a56 1

@

