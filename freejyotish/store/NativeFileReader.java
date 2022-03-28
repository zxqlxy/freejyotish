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

import freejyotish.main.BirthData;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/*
*	Created 15 April, 2003 by Michael W. Taft
*/

public class NativeFileReader
{
	private HashMap natives;
	private BufferedReader in;
	private BirthData nativeInfo;
	
	public void readNativeFile(String path, String filename)
		{	
			File file = new File(path + File.separator + filename);
			System.out.println
       (file + (file.exists() ? " exists." : " does not exist."));
       
			try {
				
					in = new BufferedReader(new FileReader(path + File.separator + filename));
					natives = new HashMap();
					readData(in);
				
			
			} catch (java.io.IOException e)
				{
					System.out.println(e);
				}
		}
	
	public void readData(BufferedReader in)throws java.io.IOException
		{
			natives = new HashMap();
			
			
			while (in.read()!= -1)
			{
                try {
                    String s = in.readLine();
                    StringTokenizer st = new StringTokenizer(s, "|");
                    nativeInfo = new BirthData();
                    nativeInfo.setFirstName(st.nextToken()); 
                    nativeInfo.setLastName(st.nextToken());
                    nativeInfo.setBirthCity(st.nextToken());
                    nativeInfo.setBirthState(st.nextToken());
                    nativeInfo.setBirthCountry(st.nextToken());
                    nativeInfo.setLongitude_Deg(Integer.valueOf(st.nextToken()).intValue());
                    nativeInfo.setLongitude_Min(Integer.valueOf(st.nextToken()).intValue());
                    nativeInfo.setLongitude_Sec(Integer.valueOf(st.nextToken()).intValue());
                    nativeInfo.setLatitude_Deg(Integer.valueOf(st.nextToken()).intValue());
                    nativeInfo.setLatitude_Min(Integer.valueOf(st.nextToken()).intValue());
                    nativeInfo.setLatitude_Sec(Integer.valueOf(st.nextToken()).intValue());
                    nativeInfo.setBirthYear(Integer.valueOf(st.nextToken()).intValue());
                    nativeInfo.setBirthMonth(Integer.valueOf(st.nextToken()).intValue());
                    nativeInfo.setBirthDate(Integer.valueOf(st.nextToken()).intValue());
                    nativeInfo.setBirthHour(Integer.valueOf(st.nextToken()).intValue());
                    nativeInfo.setBirthMinute(Integer.valueOf(st.nextToken()).intValue());
                    nativeInfo.setBirthSecond(Integer.valueOf(st.nextToken()).intValue());
                    nativeInfo.setTimeZoneOffset(Double.valueOf(st.nextToken()).doubleValue());
                    nativeInfo.setDSTOffset(Integer.valueOf(st.nextToken()).intValue());
                    nativeInfo.setSex(Integer.valueOf(st.nextToken()).intValue());
                    natives.put(nativeInfo.toString(), nativeInfo);
                } catch( NoSuchElementException nsee ) {
                    //One of the above required element not found.
                    //skip.
                }
		 catch( java.lang.NumberFormatException nfe ) 
		 {
			 System.out.println("NumberFormatException for " + nativeInfo.toString());
			  System.out.println(nfe);
                    continue;
                }
			}
		}
	
	public HashMap getNatives()
	{
		return natives;
	}

	////TESTING ONLY//////
	public static void main (String[] test)
	{
		NativeFileReader rnf = new NativeFileReader();
		String path = "/home/mwt/projects/freejyotish/";
		String filename = "natives.txt";
		rnf.readNativeFile(path, filename);
		NativeFileWriter wnf = new NativeFileWriter();
		wnf.writeAllNatives(rnf.getNatives(), path, "nativesCopy.txt");
		rnf.readNativeFile(path, "nativesCopy.txt");
		wnf.writeAllNatives(rnf.getNatives(), path, "nativesCopyAgain.txt");
	}
	
}
@
