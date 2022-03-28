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
@/*

*

* $Id: HoroPanel.java,v 1.6 2003/05/03 16:39:28 michaeltaft Exp $

*

*/

package freejyotish.gui;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import freejyotish.main.ChartCenterEvent;
import freejyotish.main.ChartCenterEventListener;
import freejyotish.main.IChartCenter;



public class HoroPanel extends JPanel implements ChartCenterEventListener{
	
	 private IChartCenter c_center;

    private HoroscopeCanvas cpLeft;

    private HoroscopeCanvas cpRight;

    private Horoscope h1;

    private Horoscope h2;

    private JPanel mainPanel;

    private JButton compareButton;

    private JButton diffButton; //For future use

    private boolean bOneChart;
    

    public HoroPanel( IChartCenter center ) {

        this.c_center = center;

        setLayout( new BorderLayout() );

        setBackground( Color.WHITE );

        

        // Play around by changing font here :)

        //Font f = FJGUIDefs.getInstance().getGlyphFont();

	Font f = FJGUIDefs.getInstance().getNormalFont();

        

        mainPanel = new JPanel();

        mainPanel.setLayout( new BoxLayout( mainPanel, BoxLayout.X_AXIS ) );

        h1 = new NorthHoroscope( 10, 10, 275, center );

        h1.setPlanetFont( f );

        h1.setSignFont( f );

        

        cpLeft = new HoroscopeCanvas( h1, center );

        cpLeft.setBorder( BorderFactory.createEtchedBorder() );

        h2 = new NorthHoroscope( 10, 10, 275, center );

        cpRight = new HoroscopeCanvas( h2, center );

        cpRight.setBorder( BorderFactory.createEtchedBorder() );

        mainPanel.add( cpLeft );

        mainPanel.add( cpRight );

        add( mainPanel, BorderLayout.CENTER );

        

        bOneChart = true;

    }

    

    public void setHoroscope( Horoscope hLeft, Horoscope hRight ) {

        cpLeft.setHoroscope( hLeft );

        cpRight.setHoroscope( hRight );

    }



	public void chartCenterChanged( ChartCenterEvent e ) {

        repaint();

	}

    

 

   

}

@
