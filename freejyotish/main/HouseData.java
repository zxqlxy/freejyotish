head	1.2;
access;
symbols;
locks; strict;
comment	@# @;


1.2
date	2003.05.23.07.55.32;	author michaeltaft;	state Exp;
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
@House preferences work.
@
text
@package freejyotish.main;

import java.io.Serializable;

public class HouseData implements Serializable
{
	private double[]ascmc;
	private double[]cusp;
	private String houseSystem;
	
	public HouseData()//because sometimes you need a blank constructor
	{
		ascmc = new double[10];
		cusp = new double[13];
	}

	public HouseData(double[] ascmc, double[]cusp)
	{
		this.ascmc = ascmc;
		this.cusp = cusp;
	}

	public double[] getAscmc()
	{
		return ascmc;
	}
	
	public void setAscmc(double[]asc)
	{
		ascmc = asc;
	}
	
	public double getAscendant()
	{
		return ascmc[0];
	}
	
	public void setAscendant(double asc)
	{
		ascmc[0] = asc;
	}

	public double getMidheaven()
	{
		return ascmc[1];
	}
	
	public void setMidheaven(double mid)
	{
		ascmc[1] = mid;
	}
	
	public double[] getAllCusps()
	{
		return cusp;	
	}
	
	public double getCusp(int index)
	{
		return cusp[index];
	}
	
	public void setCusp(int index, double value)
	{
		cusp[index] = value;
	}
	
	public void setCusps(double[]cp)
	{
		cusp = cp;
	}
	/**
	 * @@return
	 */
	public String getHouseSystem() {
		return houseSystem;
	}

	/**
	 * @@param string
	 */
	public void setHouseSystem(String string) {
		houseSystem = string;
	}

}

@


1.1
log
@Changed the package name from 'FreeJyotish' to 'freejyotish'
@
text
@d9 1
d72 14
@

