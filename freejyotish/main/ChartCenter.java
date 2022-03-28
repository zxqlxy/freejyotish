head	1.3;
access;
symbols;
locks; strict;
comment	@# @;


1.3
date	2003.05.22.18.02.34;	author michaeltaft;	state Exp;
branches;
next	1.2;

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


1.3
log
@Cleaning up old calculation preferences stuff.
@
text
@package freejyotish.main;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import freejyotish.main.calc.CalculationPreferences;
import freejyotish.main.calc.ChartBuilder;
import freejyotish.store.IFJStorage;

/**
 *  Description of the Class
 *
 *@@author     Michael W. Taft
 *@@created    May 14, 2002
 */
public class ChartCenter implements IChartCenter
{
	private HashMap charts, natives;
	private HashSet chartCenterListeners;
	private IChartCalculator calculator;
	private IFJStorage storage;
	//private String currentChart, currentNative;
	private Chart currentChart;

	private CalculationPreferences calcPrefs;
	
	


	/**
	 *  Constructor for the ChartCenter object
	 */
	public ChartCenter(IFJStorage store)
	{
		storage = store;
		//charts = new HashMap();
		natives = storage.importAllNatives("charts", "natives.txt");
		chartCenterListeners = new HashSet();
		//calcPrefs = storage.readCalculationPreferences("./prefs", "calculation_preferences");
		
		//calculator = new ChartCalculator(new ChartCalcPrefs());
		calculator = new ChartBuilder();
	calculator.setCalculationPreferences();
		//loadAllCharts();
		//setCurrentChart("Michael Taft");
	}

	
	/// >>> IN-MEMORY SECTION <<<

	public void setCurrentChart(Chart chart)
	{
		currentChart = chart;	
		fireChartCenterChanged();
	}
	
	
	
	public Chart getCurrentChart()
	{
		return currentChart;
		
		
	}

	
	/**
	 *  Description of the Method
	 *
	 *@@param  nativeInfo  Description of the Parameter
	 */
	public void createChart(BirthData nativeInfo2)
	{
		
		Chart newChart = calculator.calculateChart(nativeInfo2);
		//addChart(newChart);
		
		addNative(nativeInfo2);
		storage.saveAllNatives(natives, "charts", "natives");
		setCurrentChart(newChart);
		System.out.println("Calculated Chart for " + newChart.getChartName());
	}

	
	/**
	 *  Adds a feature to the Chart attribute of the ChartCenter object
	 *
	 *@@param  chart  The feature to be added to the Chart attribute
	 */
	public void addChart(Chart chart)
	{
		charts.put(chart.getChartName(), chart);
		//fireChartCenterChanged();
	}


	/**
	 *  Description of the Method
	 *
	 *@@param  chartName  Description of the Parameter
	 */
	public void deleteChart(String chartName)
	{
		//charts.remove(chartName);
		natives.remove(chartName);
		storage.deleteChart(chartName);
		fireChartCenterChanged();

	}

	public void testFireChartCenterChanged()
	{
		//fireChartCenterChanged();
	}


	/**
	 *  Gets the chart attribute of the ChartCenter object
	 *
	 *@@param  chartName  Description of the Parameter
	 *@@return            The chart value
	 */
	public Chart getChart(String chartName)
	{
		return (Chart) charts.get(chartName);
	}

	
	/**
	 *  Gets the allCharts attribute of the ChartCenter object
	 *
	 *@@return    The allCharts value
	 */
	public Iterator getAllCharts()
	{
		return charts.values().iterator();
	}


	/**
	 *  Description of the Method
	 *
	 *@@return    Description of the Return Value
	 */
	public boolean hasNoCharts()
	{
		return charts.isEmpty();
	}
	
	/*public void setChartCalcPrefs(ChartCalcPrefs ccp)
	{*/
		//prefs.calcPrefs = ccp;
		
	//}
	
	public void getCalculationPreferences()
	{
		
	}
	
	public void saveCalculationPreferences()
	{
		//if (cp != null)
		//{
			calculator.setCalculationPreferences();
			//storage.writeCalculationPreferences(cp);
		//}
	}

	/*public void saveChartCalcPrefs(ChartCalcPrefs ccp)
	{
		prefs.saveCalcPrefs(ccp);
		
	}*/

	
	
	/// >>> PERSISTENCE SECTION <<<

	/**
	 *  Description of the Method
	 */
	public void saveAllCharts()
	{
		//saves all charts to whatever
	}


	/**
	 *  Description of the Method
	 */
	public void loadAllCharts()
	{
		HashMap temp = storage.importAllCharts("." + File.separator + "charts" + File.separator);
		Iterator iter = temp.values().iterator();
		while (iter.hasNext())
		{
			Chart chart = (Chart) iter.next();
			charts.put(chart.getChartName(), chart);
			System.out.println("Chart center contains chart: " + chart.getChartName());
			
		}
	}


	/**
	 *  Description of the Method
	 *
	 *@@param  name  Description of the Parameter
	 */
	public void saveChart(Chart chart)
	{
		
		storage.saveChart(chart);
	}

	/// >>> NATIVEINFO SECTION <<<
	
	public void addNative(BirthData nativeInfo2)
	{
		natives.put(nativeInfo2.toString(), nativeInfo2);
	}
	public void deleteNative(String nativeName)
	{
		natives.remove(nativeName);
		//storage.deleteChart(chartName);
		fireChartCenterChanged();
	}
	public BirthData getNative(String nativeName)
	{
		return (BirthData)natives.get(nativeName);
	}
	
	public Iterator getAllNatives()
	{
		return natives.values().iterator();
	}

	/// >>> EVENT SECTION <<<
	/**
	 *  Adds a feature to the ChartCenterEventListener attribute of the ChartCenter
	 *  object
	 *
	 *@@param  c  The feature to be added to the ChartCenterEventListener attribute
	 */
	public synchronized void addChartCenterEventListener(ChartCenterEventListener c)
	{
		chartCenterListeners.add(c);
	}


	/**
	 *  Description of the Method
	 *
	 *@@param  c  Description of the Parameter
	 */
	public synchronized void removeChartCenterEventListener(ChartCenterEventListener c)
	{
		chartCenterListeners.remove(c);
	}


	/**
	 *  Description of the Method
	 */
	private void fireChartCenterChanged()
	{
		ChartCenterEvent event = new ChartCenterEvent(this);
		ChartCenterEventListener listener;
		Iterator iter = chartCenterListeners.iterator();

		while (iter.hasNext())
		{
			listener = (ChartCenterEventListener) iter.next();
			listener.chartCenterChanged(event);
		}

	}

}

@


1.2
log
@Getting new Preferences system working.
@
text
@d45 1
a45 1
	calculator.setCalculationPreferences(calcPrefs);
d158 1
a158 1
	public CalculationPreferences getCalculationPreferences()
d160 1
a160 1
		return calcPrefs;
d163 1
a163 1
	public void saveCalculationPreferences(CalculationPreferences cp)
d167 1
a167 1
			calculator.setCalculationPreferences(cp);
@


1.1
log
@Changed the package name from 'FreeJyotish' to 'freejyotish'
@
text
@d3 1
a3 9
import freejyotish.main.calc.ChartBuilder;
import freejyotish.main.calc.CalculationPreferences;
import freejyotish.store.*;

///TESTING ONLY
import java.io.*;
//import org.exolab.castor.xml.*;
///TESTING ONLY

d8 4
d26 1
a26 1
	private FJPreferences prefs;
d41 1
a41 1
		calcPrefs = storage.readCalculationPreferences("./prefs", "calculation_preferences");
d45 1
a45 1
		calculator.setCalculationPreferences(calcPrefs);
d67 1
a67 1
	////TESTING ONLY
d165 2
a166 2
		if (cp != null)
		{
d168 2
a169 2
			storage.writeCalculationPreferences(cp);
		}
@

