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
@package freejyotish.main.calc;


import java.util.GregorianCalendar;

/*
*	Created 25 March, 2003, by Michael W. Taft
*/

public class PanchangBasics 
{
	private String yoga, tithi, karana;
	private GregorianCalendar sunrise, sunset, gmt;
	private String day;
	private double ayanamsa;
	
	public void setYoga(String yoga)
	{
		this.yoga = yoga;
	}
	
	public String getYoga()
	{
		return yoga;
	}

	public void setTithi(String tithi)
	{
		this.tithi = tithi;
	}
	
	public String getTithi()
	{
		return tithi;
	}
	
	public void setKarana(String karana)
	{
		this.karana = karana;
	}
	
	public String getKarana()
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

	
	public void setAyanamsa(double ayan) 
	{
		ayanamsa = ayan;
	}

	public double getAyanamsa() 
	{
		return ayanamsa;
	}
	
	public void setSunrise(GregorianCalendar gc)
	{
		
	}
	
	public GregorianCalendar getSunrise()
	{
		
		return new GregorianCalendar();//TESTING ONLY
	}
	
	public void setSunset(GregorianCalendar gc)
	{
	}
	
	public GregorianCalendar getSunset()
	{
		
		return new GregorianCalendar();//TESTING ONLY
	}
}

@
