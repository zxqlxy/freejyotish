head	1.2;
access;
symbols;
locks; strict;
comment	@# @;


1.2
date	2003.05.22.07.34.07;	author michaeltaft;	state dead;
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
@Files no longer required.
@
text
@package freejyotish.main.calc;

import swisseph.*;
import freejyotish.main.BirthData;
import java.util.Calendar;
import java.util.GregorianCalendar;

/*
*	First created 5 April, 2003 by Michael W. Taft
*
*/

/// Converts local time to GMT and then to Ephemeris Time


public class GetEphemerisTime
{
	private BirthData nativeInfo2;
	private SwissEph sw;
	private double te, tjd;
	private int sec;
	private int min;
	private int hour;
	private int mon;
	private int day;
	private int year;
	private double ut;
	private double offset;
	
	public GetEphemerisTime(SwissEph swiss, BirthData ni)
	{
		sw = swiss;
		nativeInfo2 = ni;
	
	}
	public double getEphemerisTime()
	{		
		year = nativeInfo2.getBirthYear();
		mon = nativeInfo2.getBirthMonth();
		day = nativeInfo2.getBirthDate();;
		hour = nativeInfo2.getBirthHour();;
		min = nativeInfo2.getBirthMinute();
		sec = nativeInfo2.getBirthSecond();
		offset = nativeInfo2.getTimeZoneOffset();
		int dst = nativeInfo2.getDSTOffset();
		Calendar birth = new GregorianCalendar(year, mon-1, day, hour, min, sec);
		birth.add(Calendar.SECOND, (int) (-3600 * (offset + dst)));//Changes time to UTC
				
		double UTHour = (double)birth.get(Calendar.HOUR_OF_DAY);
		double UTMin = (double)birth.get(Calendar.MINUTE);
		double UTSec = (double)birth.get(Calendar.SECOND);
		ut = UTHour + UTMin/60 + UTSec/3600;
		System.out.println("UT = " + ut);
		///
		///
			
		SweDate sd = new SweDate(birth.get(Calendar.YEAR), birth.get(Calendar.MONTH)+1, birth.get(Calendar.DATE), ut);
		tjd = sd.getJulDay();
			
		te = tjd + sd.getDeltaT(tjd);
		
		return te;
	
	}

}
@


1.1
log
@Changed the package name from 'FreeJyotish' to 'freejyotish'
@
text
@@

