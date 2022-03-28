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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/*
*	Created 6 May, 2003 by Michael W. Taft
*/

/////EXPLANATION//////
/* Each record of a GJ output file has 3 possible states: 15, 14, or 13 tokens (where a tab is the delimiter)
*	15 tabs = a record where daylight savings time is in effect, and there is an American or Canadian state listed
*	13 tabs = a record where dst is 0, and no state is listed (i.e. the person is born outside N. America)
*	14 tab records have two possiblities: dst on, but no state listed, or; dst off, but state listed.
*	The following import code sorts for these possiblities and acts accordingly
*/
public class GoravaniJyotishFileImporter
{
	private HashMap natives;
	private BufferedReader in;
	private BirthData nativeInfo;
	private static long startTime;
	private static int numberOfRecords;
	
	public void readGoravaniJyotishFile(String path, String filename)
		{	startTime = System.currentTimeMillis();
			File file = new File(path + File.separator + filename);
			System.out.println (file + (file.exists() ? " exists." : " does not exist."));
       
			try {
				
					in = new BufferedReader(new FileReader(path + File.separator + filename));
					List newNatives = readData(in);
					natives = new HashMap();
					convertData(newNatives);
					
				
			
			} catch (java.io.IOException e)
				{
					System.out.println(e);
				}
		}
	
	public List readData(BufferedReader in)throws java.io.IOException
		{
			int inputNumber = 0;
			List gjNatives = new ArrayList();			
			while (in.readLine() !=null)
			{
                
                    String s = in.readLine();
		    //System.out.println("Adding File: " + s);
		    gjNatives.add(s);
                  
			}
		numberOfRecords = gjNatives.size();
		System.out.println("Number of GJ Records: " + numberOfRecords);
		return gjNatives;
		}
		
	private void convertData(List gjNatives)
	{
		for (int i = 0; i < gjNatives.size()-1; i++)
			{
				parseInputString((String)gjNatives.get(i));
			}
		
	}
	
	public void parseInputString(String inputString)
	{
		
		StringTokenizer stX = new StringTokenizer(inputString, "\t");
		int number = stX.countTokens();
		System.out.println("Number of tokens: " + number);
		
		BirthData newNative = new BirthData();
		StringTokenizer st = new StringTokenizer(inputString, "\t");
		try {
		if(number == 15)//15-token record
		{
		String nameSequence = st.nextToken();
		String[] name = parseNameSequence(nameSequence);
		System.out.println(nameSequence);
		newNative.setFirstName(name[0]);
		newNative.setLastName(name[1]);
		String sex = st.nextToken();

		String dateSequence = st.nextToken();
		int[] date = parseDateSequence(dateSequence);
		
		newNative.setBirthYear(date[2]);
		newNative.setBirthDate(date[1]);
		newNative.setBirthMonth(date[0]);
		
		String timeSequence = st.nextToken();
		int[] time = parseTimeSequence(timeSequence);
		
		newNative.setBirthHour(time[0]);
		newNative.setBirthMinute(time[1]);
		newNative.setBirthSecond(time[2]);
		
		int dstOffset;
		String dst = st.nextToken().trim();
		dstOffset = parseDST(dst);				
		newNative.setDSTOffset(dstOffset);	
		
		String tZone = st.nextToken().trim();
		
		double timeZoneOffset;
		timeZoneOffset = Double.valueOf(tZone).doubleValue();
		newNative.setTimeZoneOffset(timeZoneOffset);		
		System.out.println("DST: " + dstOffset + "  TZ: " + timeZoneOffset);
		
		String city = st.nextToken();
		String state = st.nextToken();			
		String country = st.nextToken();
		
		newNative.setBirthCity(city);
		newNative.setBirthState(state);	
		newNative.setBirthCountry(country);
		
		System.out.println(city + ", " + state + ", " + country);
		
		int lat_deg = Integer.valueOf(st.nextToken()).intValue();
		String northSouth = st.nextToken();
		if (northSouth.equals("S"))lat_deg = lat_deg * -1;
		int lat_min = Integer.valueOf(st.nextToken()).intValue();
		System.out.println("Latitude is: " + lat_deg + " deg. " + lat_min);
		
		newNative.setLatitude_Deg(lat_deg);
		newNative.setLatitude_Min(lat_min);
		newNative.setLatitude_Sec(0);//Goravani Jyotish doesn't store lon/lat seconds
		
		int lon_deg = Integer.valueOf(st.nextToken()).intValue();
		String eastWest = st.nextToken();
		if (eastWest.equals("W"))
		{
			lon_deg = lon_deg * -1;
			newNative.setTimeZoneOffset(newNative.getTimeZoneOffset() * -1);
		}
		int lon_min = Integer.valueOf(st.nextToken()).intValue();
		System.out.println("Latitude is: " + lon_deg + " deg. " + lon_min);
		
		newNative.setLongitude_Deg(lon_deg);
		newNative.setLongitude_Min(lon_min);
		newNative.setLongitude_Sec(0);//Goravani Jyotish doesn't store lon/lat seconds
		
		natives.put(newNative.toString(), newNative);
		}
		
		else if(number == 14)// 14-token record
		{
		String nameSequence = st.nextToken();
		String[] name = parseNameSequence(nameSequence);
		System.out.println(nameSequence);
		newNative.setFirstName(name[0]);
		newNative.setLastName(name[1]);
		String sex = st.nextToken();

		String dateSequence = st.nextToken();
		int[] date = parseDateSequence(dateSequence);
		
		newNative.setBirthYear(date[2]);
		newNative.setBirthDate(date[1]);
		newNative.setBirthMonth(date[0]);
		
		String timeSequence = st.nextToken();
		int[] time = parseTimeSequence(timeSequence);
		
		newNative.setBirthHour(time[0]);
		newNative.setBirthMinute(time[1]);
		newNative.setBirthSecond(time[2]);
		
		int dstOffset;
		double timeZoneOffset;
		boolean dstIsZero = true;
		String variableOne = st.nextToken().trim();
		if (variableOne.equals("DST 1") || variableOne.equals("DST 2"))
			{
			dstOffset = parseDST(variableOne);
			newNative.setDSTOffset(dstOffset);
			String tZone = st.nextToken().trim();
			timeZoneOffset = Double.valueOf(tZone).doubleValue();
			newNative.setTimeZoneOffset(timeZoneOffset);
			dstIsZero = false;
			}				
		else
			{
			dstOffset = 0;
			newNative.setDSTOffset(dstOffset);
			timeZoneOffset = Double.valueOf(variableOne).doubleValue();
			newNative.setTimeZoneOffset(timeZoneOffset);	
			}
		
				
		System.out.println("DST: " + dstOffset + "  TZ: " + timeZoneOffset);
		
		String city, state, country;
		
		if (dstIsZero)
		{
		city = st.nextToken();
		state = st.nextToken();			
		country = st.nextToken();
		newNative.setBirthCity(city);
		newNative.setBirthState(state);	
		newNative.setBirthCountry(country);
		}
		
		else
		{
		city = st.nextToken();
		state = "";			
		country = st.nextToken();
		newNative.setBirthCity(city);
		newNative.setBirthState(state);	
		newNative.setBirthCountry(country);		
		}
		
		System.out.println(city + ", " + state + ", " + country);
		
		int lat_deg = Integer.valueOf(st.nextToken()).intValue();
		String northSouth = st.nextToken();
		if (northSouth.equals("S"))lat_deg = lat_deg * -1;
		int lat_min = Integer.valueOf(st.nextToken()).intValue();
		System.out.println("Latitude is: " + lat_deg + " deg. " + lat_min);
		
		newNative.setLatitude_Deg(lat_deg);
		newNative.setLatitude_Min(lat_min);
		newNative.setLatitude_Sec(0);//Goravani Jyotish doesn't store lon/lat seconds
		
		int lon_deg = Integer.valueOf(st.nextToken()).intValue();
		String eastWest = st.nextToken();
		if (eastWest.equals("W"))
		{
			lon_deg = lon_deg * -1;
			newNative.setTimeZoneOffset(newNative.getTimeZoneOffset() * -1);
		}
		int lon_min = Integer.valueOf(st.nextToken()).intValue();
		System.out.println("Latitude is: " + lon_deg + " deg. " + lon_min);
		
		newNative.setLongitude_Deg(lon_deg);
		newNative.setLongitude_Min(lon_min);
		newNative.setLongitude_Sec(0);//Goravani Jyotish doesn't store lon/lat seconds
		
		natives.put(newNative.toString(), newNative);
		}
		
		
		else if (number == 13) // 13-token record
		{
				String nameSequence = st.nextToken();
		String[] name = parseNameSequence(nameSequence);
		System.out.println(nameSequence);
		newNative.setFirstName(name[0]);
		newNative.setLastName(name[1]);
		String sex = st.nextToken();

		String dateSequence = st.nextToken();
		int[] date = parseDateSequence(dateSequence);
		
		newNative.setBirthYear(date[2]);
		newNative.setBirthDate(date[1]);
		newNative.setBirthMonth(date[0]);
		
		String timeSequence = st.nextToken();
		int[] time = parseTimeSequence(timeSequence);
		
		newNative.setBirthHour(time[0]);
		newNative.setBirthMinute(time[1]);
		newNative.setBirthSecond(time[2]);
	
		int dstOffset = 0; //This is the first constant in the 13-token type				
		newNative.setDSTOffset(dstOffset);	
		
		String tZone = st.nextToken().trim();
		
		double timeZoneOffset;
		timeZoneOffset = Double.valueOf(tZone).doubleValue();
		newNative.setTimeZoneOffset(timeZoneOffset);		
		System.out.println("DST: " + dstOffset + "  TZ: " + timeZoneOffset);
		String city = st.nextToken();
		newNative.setBirthState("");//This is the second constant in the 13-token type				
		String country = st.nextToken();
		newNative.setBirthCity(city);
		newNative.setBirthCountry(country);
		
		System.out.println(city + ", "  + country);
		
		int lat_deg = Integer.valueOf(st.nextToken()).intValue();
		String northSouth = st.nextToken();
		if (northSouth.equals("S"))lat_deg = lat_deg * -1;
		int lat_min = Integer.valueOf(st.nextToken()).intValue();
		System.out.println("Latitude is: " + lat_deg + " deg. " + lat_min);
		
		newNative.setLatitude_Deg(lat_deg);
		newNative.setLatitude_Min(lat_min);
		newNative.setLatitude_Sec(0);//Goravani Jyotish doesn't store lon/lat seconds
		
		int lon_deg = Integer.valueOf(st.nextToken()).intValue();
		String eastWest = st.nextToken();
		if (eastWest.equals("W"))
		{
			lon_deg = lon_deg * -1;
			newNative.setTimeZoneOffset(newNative.getTimeZoneOffset() * -1);
		}
		int lon_min = Integer.valueOf(st.nextToken()).intValue();
		System.out.println("Latitude is: " + lon_deg + " deg. " + lon_min);
		
		newNative.setLongitude_Deg(lon_deg);
		newNative.setLongitude_Min(lon_min);
		newNative.setLongitude_Sec(0);//Goravani Jyotish doesn't store lon/lat seconds
		
		natives.put(newNative.toString(), newNative);	
			
			
			
		}
		} catch( NoSuchElementException nsee ) {
                    //One of the above required element not found.
                    System.out.println("Missing Element in Record");
               }
		
	}
	private String[] parseNameSequence(String nameSequence)
	{
		String[] name = new String[2];
		try {
		StringTokenizer st = new StringTokenizer(nameSequence);
		int number = st.countTokens();
		String firstName = "";
		for(int i=0;i<number-1;i++)//counts through how ever many names except the last one
		{
		firstName = firstName + " " + st.nextToken().trim();
		
		}
		String lastName = st.nextToken();//picks up the last one
		System.out.println("First Name: " + firstName);
		System.out.println("Last Name: " + lastName);
		name[0] = firstName;
		name[1] = lastName;
		
		 } catch( NoSuchElementException nsee ) {
			System.out.println("Missing Element in Record");
                }
		return name;
	}
	
	private int[] parseDateSequence(String dateSequence)
	{
		int[] date = new int[3];
		try {
		StringTokenizer st = new StringTokenizer(dateSequence, "/");
		int month = Integer.valueOf(st.nextToken()).intValue();
		int day = Integer.valueOf(st.nextToken()).intValue();
		int year = Integer.valueOf(st.nextToken()).intValue();
		System.out.println("Year: " + year);
		System.out.println("Month: " + month);
		System.out.println("Date: " + day);
		date[0] = month;
		date[1] = day;
		date[2] = year;
		
		 } catch( NoSuchElementException nsee ) {
			System.out.println("Missing Element in Record");
                }
		return date;
	}
	
	private int[] parseTimeSequence(String timeSequence)
	{
		int[] time = new int[3];
		try {
		StringTokenizer st = new StringTokenizer(timeSequence, ":");
		int hour = Integer.valueOf(st.nextToken()).intValue();
		int minute = Integer.valueOf(st.nextToken()).intValue();
		int second = Integer.valueOf(st.nextToken()).intValue();
		System.out.println("Hour: " + hour);
		System.out.println("Min: " + minute);
		System.out.println("Sec: " + second);
		time[0] = hour;
		time[1] = minute;
		time[2] = second;
		
		 } catch( NoSuchElementException nsee ) {
			System.out.println("Missing Element in Record");
                }
		return time;
	}
	
	private int parseDST(String dst)
	{	
		int dstOffset;
		if (dst.equals("")) dstOffset = 0;
		else if (dst.equals("DST 1")) dstOffset = 1;
		else if (dst.equals("DST 2")) dstOffset = 2;
		else dstOffset = 0;
		return dstOffset;
	}
	
	public HashMap getAllNatives()
	{
		return natives;
	}
	////TESTING ONLY//////
	public static void main (String[] test)
	{
		GoravaniJyotishFileImporter gjfi = new GoravaniJyotishFileImporter();
		String path = "/home/mwt/projects/freejyotish/";
		String filename = "GJOutputFile.txt";
		gjfi.readGoravaniJyotishFile(path, filename);
		NativeFileWriter nfw = new NativeFileWriter();
		nfw.writeAllNatives(gjfi.getAllNatives(),  "/home/mwt/projects/freejyotish/charts", "natives");
		long endTime = System.currentTimeMillis();
		long completionTime = endTime - startTime;
		double duration = completionTime/1000.0;
		int converted = gjfi.getAllNatives().size();
		System.out.println("GJ file records: " + numberOfRecords);
		System.out.println("New records created: " + converted);
		System.out.println("Converted " + numberOfRecords + " Goravani files in " + duration + " seconds.");
		
	}
	
}
@
