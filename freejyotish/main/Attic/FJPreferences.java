head	1.2;
access;
symbols;
locks; strict;
comment	@# @;


1.2
date	2003.05.22.07.38.14;	author michaeltaft;	state dead;
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
@No longer needed.
@
text
@package freejyotish.main;

//import freejyotish.store.XMLCenter;
//import freejyotish.store.CalcPrefsHandler;
//import freejyotish.store.CalcPrefsXMLWriter;

public class FJPreferences
{
	protected ChartCalcPrefs calcPrefs;
	//private XMLCenter xmlCenter;
	//private CalcPrefsXMLWriter calcPrefsWriter;
	
	public FJPreferences()
	{
		//xmlCenter = xmlc;
		calcPrefs = initCalcPrefs();
		
	}
	
	public ChartCalcPrefs initCalcPrefs()
	{
		//CalcPrefsHandler getPrefs = new CalcPrefsHandler();
		//xmlCenter.setFileName("calc_prefs.xml");
		//xmlCenter.setHandler(getPrefs);
		//xmlCenter.readXML();
		System.out.println("Calculation preferences initiated.");
		return new ChartCalcPrefs();	
	}

	public void saveCalcPrefs(ChartCalcPrefs ccp)
	{
		//calcPrefsWriter.writeCalcPrefs(ccp, "..");
		
	}






}
@


1.1
log
@Changed the package name from 'FreeJyotish' to 'freejyotish'
@
text
@@

