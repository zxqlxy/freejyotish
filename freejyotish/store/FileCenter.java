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

import java.io.*;
import java.util.HashMap;

import freejyotish.main.Chart;
import freejyotish.main.calc.CalculationPreferences;

public class FileCenter implements IFJStorage
{
	FileInputStream in;
	public Chart getChart(String filename)
	{
		
		Chart chart = new Chart();
		try
		{
		in = new FileInputStream("." + File.separator + "charts" + File.separator + filename);
		ObjectInputStream s = new ObjectInputStream(in);
		chart = (Chart)s.readObject();
		chart.getChartName();
		
		} catch (Exception e)
		{ 
			System.out.println(e);
			
		}
		return chart;
		
	}
	
	
	public void saveChart(Chart chart)
	{
		try
		{
		FileOutputStream out = new FileOutputStream("." + File.separator + "charts" + File.separator + chart.getChartName() + ".chart");
		ObjectOutputStream s = new ObjectOutputStream(out);
		s.writeObject(chart);
		s.flush();
		} catch (java.io.IOException e)
		{ System.out.println(e);}
		
		System.out.println ("[SerialCenter]: saved chart");

	}
	public void deleteChart(String chartName)
	{
		boolean success = (new File ("." + File.separator + "charts" + File.separator + chartName + ".chart")).delete();
		if (success)System.out.println("[SerialCenter]: deleted chart");
		
	
	}

	public HashMap importAllCharts(String path)
	{
		HashMap charts = new HashMap();
		Chart chart = new Chart();
		File dir = new File(path);
		String[] filenames = dir.list();
		System.out.println("Number of chart files =  " + filenames.length);
		try
		{
		for(int i = 0; i < filenames.length; i++)
		{
			chart = getChart(filenames[i]);
			charts.put(filenames[i], chart);
			System.out.println("Importing " + chart.getChartName());
		}
		} catch (Exception e){}
		return charts;
		}	
	//}
	
	
	public HashMap importAllNatives(String path, String filename)
	{
		NativeFileReader rnf = new NativeFileReader();
		rnf.readNativeFile(path, filename);
		return rnf.getNatives();
	}
	public void saveAllNatives(HashMap natives, String path, String filename)
	{
		NativeFileWriter wnf = new NativeFileWriter();
		wnf.writeAllNatives(natives, path, filename);
	}
	
	public CalculationPreferences readCalculationPreferences(String path, String filename)
	{
		CalculationPreferencesReader reader = new CalculationPreferencesReader();
		reader.readCalculationPreferences(path, filename);	
		return reader.getCalculationPreferences();
	}
	
	public void writeCalculationPreferences(CalculationPreferences cp)
	{
		CalculationPreferencesWriter writer = new CalculationPreferencesWriter();
		writer.writeCalculationPreferences(cp, "./prefs", "calculation_preferences");	
	}

}
@
