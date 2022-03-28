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
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import freejyotish.main.calc.CalculationPreferences;

/*
*	Created 13 April, 2003 by Michael W. Taft
*/


public class CalculationPreferencesWriter
{

	private PrintWriter os;
	
	public void writeCalculationPreferences(CalculationPreferences cp, String path, String filename) 
	{
		
			try {
					os = new PrintWriter(new FileWriter(path + File.separator + filename + ".txt"));
					
			//Iterator iter = cp.getAllPreferences().values().iterator();
			//while (iter.hasNext())
			//{
			//String output = (String) iter.next();
			String output = cp.getAllPreferences().toString();
			//System.out.println(output);
			os.println(output);
			//}
			os.close();
		} catch (IOException ex)
						{
							System.out.print(ex);
						}
		
	}

	public static void main (String[] args)//testing only
	{
		CalculationPreferencesWriter wcp = new CalculationPreferencesWriter();
		CalculationPreferences cp = new CalculationPreferences();
		cp.setPreference("mode", (Object)"sidereal");
		cp.setPreference("ayanamsa", (Object)"lahiri");
		cp.setPreference("node", (Object)"true");
		cp.setPreference("outer_planets", (Object)"on");
		wcp.writeCalculationPreferences(cp, ".", "calculation_preferences");
	
	}

}



@
