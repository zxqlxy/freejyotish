head	1.2;
access;
symbols;
locks; strict;
comment	@# @;


1.2
date	2003.05.22.18.02.34;	author michaeltaft;	state Exp;
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
@Cleaning up old calculation preferences stuff.
@
text
@package freejyotish.main;


import java.util.Iterator;

public interface IChartCenter
{
	
	public void createChart(BirthData nativeInfo2);
	public void addChart(Chart chart);
	public void deleteChart(String chartName);
	public Chart getChart(String chartName);
	public Chart getCurrentChart();
	public void setCurrentChart(Chart chart);
	
	public void addNative(BirthData nativeInfo2);
	public void deleteNative(String nativeName);
	public BirthData getNative(String nativeName);
	
	public Iterator getAllNatives();
	//public void saveChart(String name);
	
	//public void getCalculationPreferences();
	public void saveCalculationPreferences();
	
	public Iterator getAllCharts();
	public boolean hasNoCharts();
	public void addChartCenterEventListener(ChartCenterEventListener c);
	public void removeChartCenterEventListener(ChartCenterEventListener c);
	public void testFireChartCenterChanged();
	
	///TESTING ONLY

	



}
@


1.1
log
@Changed the package name from 'FreeJyotish' to 'freejyotish'
@
text
@a4 1
import freejyotish.main.calc.CalculationPreferences;
d23 2
a24 2
	public CalculationPreferences getCalculationPreferences();
	public void saveCalculationPreferences(CalculationPreferences cp);
@

