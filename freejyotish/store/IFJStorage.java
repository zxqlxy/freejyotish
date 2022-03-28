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
@package freejyotish.store;

import freejyotish.main.Chart;
import freejyotish.main.calc.CalculationPreferences ;
import java.util.HashMap;

public interface IFJStorage
{
	public Chart getChart(String filename);
	public void saveChart(Chart chart);
	public void deleteChart(String filename); 
	public HashMap importAllCharts(String path);
	public CalculationPreferences readCalculationPreferences(String path, String filename);
	public void writeCalculationPreferences(CalculationPreferences cp);
	
	//public ChartCalcPrefs getChartCalcPrefs();
	//public void setChartCalcPrefs(ChartCalcPrefs ccp);
	//public void saveChartCalcPrefs(ChartCalcPrefs ccp);
	
	public HashMap importAllNatives(String path, String filename);
	public void saveAllNatives(HashMap natives, String path, String filename);



}
@
