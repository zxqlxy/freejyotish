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

import java.io.Serializable;
import java.util.GregorianCalendar;

import swisseph.SweDate;

/*
*	Created 25 March, 2003, by Michael W. Taft
*/

public class BasicsI implements Serializable
{
	private String yoga, tithi, karana;
	private GregorianCalendar sunrise, sunset, gmt;
	private String day, sid_time, ayan;
	
	private void setYoga(String yoga)
	{
		this.yoga = yoga;
	}
	
	private String getYoga()
	{
		return yoga;
	}

	private void setTithi(String tithi)
	{
		this.tithi = tithi;
	}
	
	private String getTithi()
	{
		return tithi;
	}
	
	private void setKarana(String karana)
	{
		this.karana = karana;
	}
	
	private String getKarana()
	{
		return karana;
	}
	
	public void setDay(String day) 
	{
		this.day = day;
	}

	public String getDay() 
	{
		return day;
	}

	public void setSid_Time(String sid_time) 
	{
		this.sid_time = sid_time;
	}

	public String getSid_Time() 
	{
		return sid_time;
	}

	public void setAyanamsa(String ayan) 
	{
		this.ayan = ayan;
	}

	public String getAyanamsa() 
	{
		return ayan;
	}
	
	public void setSunrise(SweDate sr)
	{
		int sunHour = (int)sr.getHour();
		double rem1 = sr.getHour() - (double)sunHour;
		double sun2 = rem1 * 60.0;
		int sunMin = (int)sun2;
		double rem2 = sun2 - (double)sunMin;
		double sun3 = rem2 * 60.0;
		int sunSec = (int)sun3;
		sunrise = new GregorianCalendar((int)sr.getYear(), (int)sr.getMonth(), (int)sr.getDay(), sunHour, sunMin, sunSec);
		System.out.println("[BasicsI]: Sunrise = " + (int)sr.getYear() + ":" +  (int)sr.getMonth() + ":" + (int)sr.getDay() + ":" + sunHour+ ":" + sunMin+ ":" + sunSec);
	}
}

@


1.1
log
@Changed the package name from 'FreeJyotish' to 'freejyotish'
@
text
@@

