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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import freejyotish.main.calc.CalculationPreferences;

/*
*	Created 15 April, 2003 by Michael W. Taft
*/

public class CalculationPreferencesReader
{
	//private Hashtable natives;
	private BufferedReader in;
	private CalculationPreferences cp;
	
	public void readCalculationPreferences(String path, String filename)
		{	
			File file = new File(path + File.separator + filename + ".txt");
			System.out.println
       (file + (file.exists() ? " exists." : " does not exist."));
       
			try {
				
					in = new BufferedReader(new FileReader(path + File.separator + filename + ".txt"));
					cp = new CalculationPreferences();
					readData(in);
				
			
			} catch (java.io.IOException e)
				{
					System.out.println(e);
				}
		}
	
	public void readData(BufferedReader in)throws java.io.IOException
		{
	          try {
                    String s = in.readLine();
		    int l = s.length();
		    s = s.substring(1,l-1);
		    s = s.trim();
		    System.out.println("s: " + s);
                    StringTokenizer st = new StringTokenizer(s, ",");
		    while(st.hasMoreTokens())
		    {
			 String pair = st.nextToken().trim();
			 String[] keysAndValues = pair.split("=");
			 System.out.println(keysAndValues[0] + ":" + keysAndValues[1]);
			 cp.setPreference(keysAndValues[0].trim(), (Object) keysAndValues[1].trim());
			    
		    }
                     
                    
                } catch( NoSuchElementException nsee ) {
                    //One of the above required element not found.
                    //skip.
                }
			
		}
	
	public CalculationPreferences getCalculationPreferences()
	{
		return cp;
	}

	////TESTING ONLY//////
	public static void main (String[] test)
	{
		CalculationPreferencesReader rcp = new CalculationPreferencesReader();
		
		String path = ".";
		String filename = "calculation_preferences";
		rcp.readCalculationPreferences(path, filename);
		CalculationPreferencesWriter wcp = new CalculationPreferencesWriter();
		wcp.writeCalculationPreferences(rcp.getCalculationPreferences(), path, "calculation_preferencesCOPY");
		rcp.readCalculationPreferences(path, "calculation_preferencesCOPY");
		wcp.writeCalculationPreferences(rcp.getCalculationPreferences(), path, "calculation_preferencesCOPY_AGAIN");
	}
	
}
@
