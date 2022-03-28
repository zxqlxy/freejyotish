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

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import freejyotish.main.FJConstants;
import freejyotish.main.FJUtility;
import freejyotish.main.IChartCenter;
import freejyotish.main.calc.VargaData;

/**
 *  Description of the Class
 *
 *@@author     Harsh Singh, Michael W. Taft
 *@@created    May 19, 2002
 */
public class NorthHoroscope extends Horoscope
{
	private Rectangle2D rect;
	/*
	 *  outer rectangle of the horoscope
	 */
	private Line2D diagonal1;
	/*
	 *  diagonal from upper left to lower right
	 */
	private Line2D diagonal2;
	/*
	 *  diagonal from lower left to upper right
	 */
	private Shape inner;
	/*
	 *  the inner square rotated 45 degs counter clockwise
	 */
	private Point2D[] interSec;
	/*
	 *  all the intersections in the chart
	 */
	private House[] houses;
	
	//private int chartType;


	/**
	 *  Constructor for the NorthHoroscope object
	 *
	 *@@param  x  Description of the Parameter
	 *@@param  y  Description of the Parameter
	 *@@param  l  Description of the Parameter
	 */
	public NorthHoroscope(float x, float y, float l, VargaData vargaData, int vargaNumber, IChartCenter center)
	{
		super(x, y, l, vargaData, vargaNumber, center);
		interSec = new Point2D[12];
		houses = new House[12];
		//chartType = 1;//chart is not a blank chart

		for (int i = 0; i < 4; i++)
		{
			interSec[i * 3] = new Point2D.Float(orig_x + length / 2, orig_y + length / 2);
		}
		interSec[1] = interSec[2] = new Point2D.Float(orig_x + length / 4, orig_y + length / 4);
		interSec[4] = interSec[5] = new Point2D.Float(orig_x + length / 4, orig_y + 3 * length / 4);
		interSec[7] = interSec[8] = new Point2D.Float(orig_x + 3 * length / 4, orig_y + 3 * length / 4);
		interSec[10] = interSec[11] = new Point2D.Float(orig_x + 3 * length / 4, orig_y + length / 4);

		for (int i = 0; i < 12; i++)
		{

			houses[i] = new House(i + 1, interSec[i], orig_x, orig_y, length);
		}
	}
//This is the constructor for blank horoscopes

	public NorthHoroscope( float x, float y, float l, IChartCenter center )
	{
		super( x, y, l, center );
		interSec = new Point2D[12];
		houses = new House[12];
		//chartType = 0;//chart is a blank chart
		
		for (int i = 0; i < 4; i++)
		{
			interSec[i * 3] = new Point2D.Float(orig_x + length / 2, orig_y + length / 2);
		}
		interSec[1] = interSec[2] = new Point2D.Float(orig_x + length / 4, orig_y + length / 4);
		interSec[4] = interSec[5] = new Point2D.Float(orig_x + length / 4, orig_y + 3 * length / 4);
		interSec[7] = interSec[8] = new Point2D.Float(orig_x + 3 * length / 4, orig_y + 3 * length / 4);
		interSec[10] = interSec[11] = new Point2D.Float(orig_x + 3 * length / 4, orig_y + length / 4);

		for (int i = 0; i < 12; i++)
		{

			houses[i] = new House(i + 1, interSec[i], orig_x, orig_y, length);
		}
	} 
	
	
	/**
	 *  Description of the Method
	 *
	 *@@param  g  Description of the Parameter
	 */
	public void drawHoroscope(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
        
    	float rh1 = (float) (length / (Math.sqrt(2.0)));
		float rw1 = rh1;

		rect = new Rectangle2D.Float(orig_x, orig_y, length, length);
		diagonal1 = new Line2D.Float(orig_x, orig_y, orig_x + length, orig_y + length);
		diagonal2 = new Line2D.Float(orig_x, orig_y + length, orig_x + length, orig_y);

		Rectangle2D rect2 = new Rectangle2D.Float(orig_x, orig_y + length / 2, rh1, rw1);
		AffineTransform at = AffineTransform.getRotateInstance(FJUtility.toRadians(-45.0), orig_x, orig_y + length / 2);

		inner = at.createTransformedShape(rect2);
        //g2.setColor( Color.WHITE );
        Paint p = g2.getPaint();
        GradientPaint gp = new GradientPaint( orig_x, orig_y, Color.WHITE
        , length*2, length*2, Color.yellow );
        g2.setPaint( gp );
        //g2.fill3DRect( (int) orig_x, (int) orig_y, (int)length, (int)length, true );
        g2.fill( rect );
        g2.setPaint( p );
        //g2.setColor( Color.BLACK );
		//g2.draw(rect);
		g2.draw(diagonal1);
		g2.draw(diagonal2);
		g2.draw(inner);

		setHouses(g2);
		for (int i = 0; i < 12; i++)
		{
			houses[i].clearAll();
		}
			
			
			  
		if (chartType==1)
			
		{	
			
                
				for (int i=0;i<12; i++) 
				{
					
					//String name = FJConstants.planetLongNames[i];
                    String name;
                    if( planetFont.getName().trim().equalsIgnoreCase(
                        "AstroGadget" ) ) {
                        name = FJConstants.planetGlyphs[ i ];
                    } else {
                        name = FJConstants.planetShortNames[i];
                    }
					//g2.setFont(new Font("AstroGadget",3,50));
					//String name = FJConstants.planetGlyphs[i];
					//int house = planets.getPlanet(FJConstants.planetLongNames[i]).getLongitudeAsInt();
					int house = vargaData.getVargaData()[vargaNumber][i];
					int index = house - ascendant;
					if (index < 0)
					index += 12;
					houses[index].setText(name, g2);
                                        
					
				}
		}
        //repaint();
	}


	/**
	 *  Sets the houses attribute of the NorthHoroscope object
	 *
	 *@@param  g2  The new houses value
	 */
	public void setHouses(Graphics2D g2)
	{
		int asc = this.ascendant;

		for (int i = 0; i < 12; i++)
		{
			if (asc > 12)
			{
				asc = asc - 12;
			}
			houses[i].setSign(asc++, g2);
		}
	}


	/*
	 *  This is the house class which keeps all the positions necessary for
	 *  for the planets to be place in the correct places
	 */
	/**
	 *  Description of the Class
	 *
	 *@@author     Harsh Singh
	 *@@created    May 19, 2002
	 */
	class House
	{
		private int noPlanets = 0;
		// number of planets currently in this house
		private boolean isAsc = false;
		// is this house the ascendant
		private int house;
		// the house number
		private int sign;
		// what sign is this house
		private Point2D point;
		// this is point about which all the text is centered
		private float origx;
		private float origy;
		private float len;


		/**
		 *  Constructor for the House object
		 *
		 *@@param  number  Description of the Parameter
		 *@@param  pt      Description of the Parameter
		 *@@param  x       Description of the Parameter
		 *@@param  y       Description of the Parameter
		 *@@param  l       Description of the Parameter
		 */
		public House(int number, Point2D pt, float x, float y, float l)
		{
			if (number == 1)
			{
				isAsc = true;
			}
			house = number;
			point = pt;
			origx = x;
			origy = y;
			len = l;
		}


		/*
		 *  sets the number of the house
		 */
		/**
		 *  Sets the sign attribute of the House object
		 *
		 *@@param  number  The new sign value
		 *@@param  g2      The new sign value
		 */
		public void setSign(int number, Graphics2D g2)
		{

			float x = (float) point.getX();
			float y = (float) point.getY();

            g2.setFont( signFont );
			sign = number;
            String numberStr;
            if( signFont.getName().trim().equalsIgnoreCase( "AstroGadget" ) ) {
                if( sign == 0 ) sign = 1;
               // System.out.println( "sign: " + sign );
                numberStr = FJConstants.signGlyphs[ sign-1 ];
            } else {
                numberStr = String.valueOf(sign);
            }
            //System.out.println( "numberStr: " + numberStr );
			FontMetrics fm = g2.getFontMetrics();
			int w = fm.stringWidth(numberStr);
			int h = fm.getAscent();

			switch (house)
			{
							case 1:
							case 2:
							case 12:
							{
								g2.drawString(numberStr, x - w / 2, y - 10 + h / 4);
								break;
							}
							case 3:
							case 4:
							case 5:
							{
								g2.drawString(numberStr, x - 10 - w / 2, y + h / 4);
								break;
							}
							case 6:
							case 7:
							case 8:
							{
								g2.drawString(numberStr, x - w / 2, y + 10 + h / 4);
								break;
							}
							case 9:
							case 10:
							case 11:
							{
								g2.drawString(numberStr, x + 10 - w / 2, y + h / 4);
								break;
							}

			}
		}


		/**
		 *  Sets the text attribute of the House object
		 *
		 *@@param  text  The new text value
		 *@@param  g2    The new text value
		 */
		public void setText(String text, Graphics2D g2)
		{
			float place_y;
			float place_x;
            
            g2.setFont( planetFont );
			FontMetrics fm = g2.getFontMetrics();
			g2.setPaint(Color.red);

			int w = fm.stringWidth(text);
			int h = fm.getAscent();

			float x = (float) point.getX();
			float y = (float) point.getY();

			switch (house)
			{
							case 1:
							case 2:
							case 12:
							{
								place_y = origy + (y - origy) / 4 + 10;
								g2.drawString(text, x - w / 2, place_y + noPlanets * 13 - h / 4);
								break;
							}
							case 3:
							case 5:
							{
								place_x = origx + (x - origx) / 4;
								place_y = y - 20 + noPlanets * 10;
								g2.drawString(text, place_x - w / 2, place_y - h / 4);
								break;
							}
							case 4:
							{
								place_x = origx + (x - origx) / 2;
								place_y = y - 20 + noPlanets * 13;
								g2.drawString(text, place_x - w / 2, place_y - h / 4);
								break;
							}
							case 6:
							case 7:
							case 8:
							{
								float tempy = origy + len;
								place_y = y + (tempy - y) / 2;
								g2.drawString(text, x - w / 2, place_y + noPlanets * 13 - h / 4);
								break;
							}
							case 9:
							case 11:
							{
								float tempx = origx + len;
								place_x = x + (tempx - x) / 2 + 10;
								place_y = y - 20 + noPlanets * 13;
								g2.drawString(text, place_x - w / 2, place_y - h / 4);
								break;
							}
							case 10:
							{
								float tempx = origx + len;
								place_x = x + (tempx - x) / 2;
								place_y = y - 20 + noPlanets * 13;
								g2.drawString(text, place_x - w / 2, place_y - h / 4);
								break;
							}
			}
			noPlanets++;
		}


		/**
		 *  Description of the Method
		 */
		public void clearAll()
		{
			noPlanets = 0;
		}
	}
}

@
