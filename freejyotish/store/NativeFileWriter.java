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
import java.io.*;
import java.util.HashMap;
import java.util.Iterator;

/*
*	Created 13 April, 2003 by Michael W. Taft
*/


public class NativeFileWriter
{

	private PrintWriter os;
	
	public void writeNative(BirthData ni) throws IOException
	{
		
			try {
					os = new PrintWriter(new FileWriter("natives.txt"));
					} catch (Exception ex)
						{
							System.out.print(ex);
						}
			String output = createNativeInfoString(ni);	
			os.print(output);
			os.close();
		
		
	}
	
	public void writeAllNatives(HashMap natives, String path, String filename) 
	{
		
			try {
					os = new PrintWriter(new FileWriter(path + File.separator + filename + ".txt"));
					
			Iterator iter = natives.values().iterator();
			while (iter.hasNext())
			{
			BirthData nativeInfo = (BirthData) iter.next();
			String output = createNativeInfoString(nativeInfo);	
			os.println(output);
			}
			os.close();
		} catch (IOException ex)
						{
							System.out.print(ex);
						}
		
	}

	public String createNativeInfoString(BirthData ni)
	{
		StringBuffer construct = new StringBuffer("|" + ni.getFirstName() +  "|");
		construct.append(ni.getLastName() + "|");
		construct.append(ni.getBirthCity() + "|");
		construct.append(ni.getBirthState() + "|");
		construct.append(ni.getBirthCountry() + "|");
		construct.append(ni.getLongitude_Deg() + "|");
		construct.append(ni.getLongitude_Min() + "|");
		construct.append(ni.getLongitude_Sec() + "|");
		//construct.append(ni.getIsEast() + "|");
		construct.append(ni.getLatitude_Deg() + "|");
		construct.append(ni.getLatitude_Min() + "|");
		construct.append(ni.getLatitude_Sec() + "|");
		//construct.append(ni.getIsNorth() + "|");
		construct.append(ni.getBirthYear() + "|");
		construct.append(ni.getBirthMonth() + "|");
		construct.append(ni.getBirthDate() + "|");
		construct.append(ni.getBirthHour() + "|");
		construct.append(ni.getBirthMinute() + "|");
		construct.append(ni.getBirthSecond() + "|");
		construct.append(ni.getTimeZoneOffset() + "|");
		construct.append(ni.getDSTOffset() + "|");
		construct.append(ni.getSex() + "|");
		System.out.println(construct);
		return construct.toString();
	}


}



@
