head	1.2;
access;
symbols;
locks; strict;
comment	@# @;


1.2
date	2003.05.22.16.33.32;	author michaeltaft;	state Exp;
branches;
next	1.1;

1.1
date	2003.05.17.08.18.57;	author santoshs;	state Exp;
branches;
next	;


desc
@@


1.2
log
@Getting new Preferences system working.
@
text
@package freejyotish.main.calc;



/*
*	First created 5 April, 2003 by Michael W. Taft
*
*/

///The idea here is to separate the ChartCalcPrefs from the SwissEph implementation of the IChartCalculator interface

public class ChartCalcPrefsAdapter
{
	private String houseSystemChoice;
	private String ayanamsaChoice;
	private String zodiacChoice;
	//private ChartCalcPrefs calcPrefs;

	

	public String getHouseSystemChoice()
	{
		return houseSystemChoice;
	}
	
	public String getAyanamsaSystemChoice()
	{
		return ayanamsaChoice;
	}
	
	public String getZodiacChoice()
	{
		return zodiacChoice;
	}

}
@


1.1
log
@Changed the package name from 'FreeJyotish' to 'freejyotish'
@
text
@d3 1
a3 1
import freejyotish.main.ChartCalcPrefs;
d17 1
a17 1
	private ChartCalcPrefs calcPrefs;
d19 1
a19 5
	public ChartCalcPrefsAdapter(ChartCalcPrefs cCP)
	{
		calcPrefs = cCP;
		
	}
@

