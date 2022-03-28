head	1.1;
access;
symbols;
locks; strict;
comment	@# @;


1.1
date	2003.05.17.08.18.58;	author santoshs;	state Exp;
branches;
next	;


desc
@@


1.1
log
@Changed the package name from 'FreeJyotish' to 'freejyotish'
@
text
@package freejyotish.gui;

import java.awt.Font;
import java.awt.Graphics;

import freejyotish.main.*;
import freejyotish.main.calc.VargaData;

/**
 *  Description of the Class @@
 *
 *@@author	Harsh Singh, Michael W. Taft
 *@@created    February 13, 2003
 */
public abstract class Horoscope implements ChartCenterEventListener
{
	protected int chartType;
	/**
	 *  Description of the Field
	 */
	protected int vargaNumber;
	/**
	 *  Description of the Field
	 */
	protected VargaData vargaData;

	/**
	 *  Description of the Field
	 */
	protected int ascendant;

	/**
	 *  Description of the Field
	 */
	protected float orig_x;
	/**
	 *  Description of the Field
	 */
	protected float orig_y;
	/**
	 *  Description of the Field
	 */
	protected float length;
    
    /**
     * Font for signs
     */
     protected static Font signFont;
     
    /**
     * Font for planets
     */
     protected static Font planetFont;
     
     

	private IChartCenter c_center;

	/**
	 *  Constructor for the Horoscope object
	 *
	 *@@param  x            Description of the Parameter
	 *@@param  y            Description of the Parameter
	 *@@param  l            Description of the Parameter
	 *@@param  vargaData    Description of the Parameter
	 *@@param  vargaNumber  Description of the Parameter
	 */
	public Horoscope(float x, float y, float l, VargaData vd, int vNumber, IChartCenter center)
	{
		this.orig_x = x;
		this.orig_y = y;
		this.length = l;
		vargaData = vd;
		vargaNumber = vNumber;
		ascendant = vargaData.getVargaData()[vargaNumber][13];
		c_center = center;
		c_center.addChartCenterEventListener(this);
		chartType = 1;//chart is not a blank chart
		
	}


	//this is the constructor for making blank charts (like on startup)
	/**
	 *  Constructor for the Horoscope object
	 *
	 *@@param  x  Description of the Parameter
	 *@@param  y  Description of the Parameter
	 *@@param  l  Description of the Parameter
	 */
	public Horoscope(float x, float y, float l, IChartCenter center)
	{
		this.orig_x = x;
		this.orig_y = y;
		this.length = l;
		vargaNumber = 0;//Rasi is default
		c_center = center;
		c_center.addChartCenterEventListener(this);
		chartType = 0;//chart is a blank chart
	}

	
	/**
	 *  Description of the Method
	 *
	 *@@param  g  Description of the Parameter
	 */
	public abstract void drawHoroscope(Graphics g);


	//this is only here to support subclasses

	/**
	 *  Sets the ascendant attribute of the Horoscope object
	 *
	 *@@param  asc  The new ascendant value
	 */
	public void setAscendant(int asc)
	{
		ascendant = asc;
		
	}


	/**
	 *  Gets the ascendant attribute of the Horoscope object
	 *
	 *@@return    The ascendant value
	 */
	public int getAscendant()
	{
		return ascendant;
	}


	/**
	 *  Sets the vargaNumber attribute of the Horoscope object
	 *
	 *@@param  vNumber  The new vargaNumber value
	 */
	public void setVargaNumber(int vNumber)
	{
		vargaNumber = vNumber;
		if (chartType == 1)
		{
		ascendant = vargaData.getVargaData()[vargaNumber][13];
		}
	}

    
    public void setPlanetFont( Font f ) {
        planetFont = f;
    }
    
    public void setSignFont( Font f ) {
        signFont = f;
    }

	/**
	 *  Gets the vargaNumber attribute of the Horoscope object
	 *
	 *@@return    The vargaNumber value
	 */
	public int getVargaNumber()
	{
		return vargaNumber;
	}

	public void changeChartType()
	{
		chartType = 1;
	}
	
	public VargaData getVargaData()
	{
		return vargaData;
	}
	
	public void chartCenterChanged(ChartCenterEvent e)
	{
		changeChartType();
		Chart newChart = c_center.getCurrentChart();
		if (newChart != null)
		{
		vargaData = newChart.getVargaData();
		ascendant = vargaData.getVargaData()[vargaNumber][13];
		}
		else ascendant = FJConstants.ARIES;
		//System.out.println("Horoscope hears Chart Center change.");
	}
}

@
