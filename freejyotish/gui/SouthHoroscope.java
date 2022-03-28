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
@/* This class has all the Chart Information for the Native */
/* It calculates positions of all the planets and also the ascendant */

package freejyotish.gui;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import freejyotish.main.FJConstants;
import freejyotish.main.IChartCenter;
import freejyotish.main.calc.VargaData;


public class SouthHoroscope extends Horoscope
{
    private Rectangle2D o_rect;     /* outer rectangle of the horoscope */
    private Rectangle2D i_rect;		/* inner rectangle of the horoscope */
    private Line2D	[]lines;
	private House	[]houses;

    public SouthHoroscope(float x, float y, float l, VargaData vargaData, int vargaNumber, IChartCenter center)
    {
		super(x,y,l, vargaData, vargaNumber, center);
		
		lines	= new Line2D[12];
		houses	= new House[12];

        o_rect	= new Rectangle2D.Float(orig_x, orig_y, length, length);
        i_rect	= new Rectangle2D.Float(orig_x + length/4, orig_y + length/4,
        								length/2, length/2);
		lines[0]	= new Line2D.Float(orig_x + length/4, orig_y,
									   orig_x + length/4, orig_y + length/4);
		lines[8]	= new Line2D.Float(orig_x + length/4, orig_y + 3*length/4,
									   orig_x + length/4, orig_y + length);

		lines[1]	= new Line2D.Float(orig_x + length/2, orig_y,
									   orig_x + length/2, orig_y + length/4);
		lines[7]	= new Line2D.Float(orig_x + length/2, orig_y + 3*length/4,
									   orig_x + length/2, orig_y + length);

		lines[2]	= new Line2D.Float(orig_x + 3*length/4, orig_y,
									   orig_x + 3*length/4, orig_y + length/4);
		lines[6]	= new Line2D.Float(orig_x + 3*length/4, orig_y + 3*length/4,
									   orig_x + 3*length/4, orig_y + length);

		lines[3]	= new Line2D.Float(orig_x + 3*length/4, orig_y + length/4,
		                               orig_x + length, orig_y + length/4);
		lines[11]	= new Line2D.Float(orig_x, orig_y + length/4,
		                               orig_x + length/4, orig_y + length/4);

		lines[4]	= new Line2D.Float(orig_x + 3*length/4, orig_y + length/2,
		                               orig_x + length, orig_y + length/2);
		lines[10]	= new Line2D.Float(orig_x, orig_y + length/2,
		                               orig_x + length/4, orig_y + length/2);

		lines[5]	= new Line2D.Float(orig_x + 3*length/4, orig_y + 3*length/4,
		                               orig_x + length, orig_y + 3*length/4);
		lines[9]	= new Line2D.Float(orig_x, orig_y + 3*length/4,
		                               orig_x + length/4, orig_y + 3*length/4);

		for (int i=0;i<12;i++){
			houses[i] = new House(i+1,orig_x, orig_y, length);
		}
    }

    public void drawHoroscope (Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        //g2.setColor( Color.WHITE );
        //g2.fill3DRect( (int) orig_x, (int) orig_y, (int) length, (int) length, true );
        //g2.draw(o_rect);
        Paint p = g2.getPaint();
        GradientPaint gp = new GradientPaint( orig_x, orig_y, Color.WHITE
        , length*2, length*2, Color.YELLOW );
        g2.setPaint( gp );
        g2.fill( o_rect );
        g2.setPaint( p );
        g2.setColor( Color.BLACK );
        g2.draw(i_rect);
        for (int i=0;i<12;i++){
			g2.draw(lines[i]);
		}

		for (int i=0;i<12;i++){
			houses[i].clearAll();
		}
		// for the ascendant
		houses[this.ascendant-1].setText("Asc", g2);

		for (int i=0;i<12;i++){
			//houses[planets.getPlanet(FJConstants.planetLongNames[i]).getLongitudeAsInt()-1].setText(planets.getPlanet(FJConstants.planetLongNames[i]).getPlanetName(),g2);
            String name;
            if( planetFont.getName().trim().equalsIgnoreCase(
                "AstroGadget" ) ) {
                name = FJConstants.planetGlyphs[ i ];
            } else {
                name = FJConstants.planetLongNames[i];
            }
 
			houses[vargaData.getVargaData()[vargaNumber][i]-1].setText( name, g2);
		}
    }

    /*
        This is the house class which keeps all the positions necessary for
        for the planets to be place in the correct places
     */
    class House {
        private int     noPlanets   =0;     // number of planets currently in this house
        private int     house;              // the house number
        private float   origx;
        private float   origy;
        private float   len;

        public House (int number, float x, float y, float l)
        {
            house = number;
            origx = x;
            origy = y;
            len   = l;
        }

        public void setText(String text, Graphics2D g2)
        {
            float place_y, place_x;
            
            g2.setFont( planetFont );
            if( text.equalsIgnoreCase( "Asc" ) ) {
                g2.setFont( FJGUIDefs.getInstance().getNormalFont() );
            }
            FontMetrics  fm = g2.getFontMetrics();
            g2.setPaint(Color.red);

            int w = fm.stringWidth(text);
            int h = fm.getAscent();

            switch (house) {
                case 1:
                {
					place_x = orig_x + 3*len/8 - w/2;
					place_y = orig_y + len/8;
					g2.drawString(text, place_x, place_y + noPlanets*13 - h/4);
					break;
				}
                case 2:
                {
					place_x = orig_x + 5*len/8 - w/2;
					place_y = orig_y + len/8;
					g2.drawString(text, place_x, place_y + noPlanets*13 - h/4);
					break;
				}
				case 3:
				{
					place_x = orig_x + 7*len/8 - w/2;
					place_y = orig_y + len/8;
					g2.drawString(text, place_x, place_y + noPlanets*13 - h/4);
					break;
				}
				case 4:
				{
					place_x = orig_x + 7*len/8 - w/2;
					place_y = orig_y + 3*len/8;
					g2.drawString(text, place_x, place_y + noPlanets*13 - h/4);
					break;
				}
				case 5:
				{
					place_x = orig_x + 7*len/8 - w/2;
					place_y = orig_y + 5*len/8;
					g2.drawString(text, place_x, place_y + noPlanets*13 - h/4);
					break;
				}
				case 6:
				{
					place_x = orig_x + 7*len/8 - w/2;
					place_y = orig_y + 7*len/8;
					g2.drawString(text, place_x, place_y + noPlanets*13 - h/4);
					break;
				}
				case 7:
				{
					place_x = orig_x + 5*len/8 - w/2;
					place_y = orig_y + 7*len/8;
					g2.drawString(text, place_x, place_y + noPlanets*13 - h/4);
					break;
				}
				case 8:
				{
					place_x = orig_x + 3*len/8 - w/2;
					place_y = orig_y + 7*len/8;
					g2.drawString(text, place_x, place_y + noPlanets*13 - h/4);
					break;
				}
				case 9:
				{
					place_x = orig_x + len/8 - w/2;
					place_y = orig_y + 7*len/8;
					g2.drawString(text, place_x, place_y + noPlanets*13 - h/4);
					break;
				}
				case 10:
				{
					place_x = orig_x + len/8 - w/2;
					place_y = orig_y + 5*len/8;
					g2.drawString(text, place_x, place_y + noPlanets*13 - h/4);
					break;
				}
				case 11:
				{
					place_x = orig_x + len/8 - w/2;
					place_y = orig_y + 3*len/8;
					g2.drawString(text, place_x, place_y + noPlanets*13 - h/4);
					break;
				}
                case 12:
                {
					place_x = orig_x + len/8 - w/2;
					place_y = orig_y + len/8;
					g2.drawString(text, place_x, place_y + noPlanets*13 - h/4);
					break;
                }
            }
            noPlanets++;
        }
        public void clearAll()
        {
            noPlanets = 0;
        }
    }
}
@
