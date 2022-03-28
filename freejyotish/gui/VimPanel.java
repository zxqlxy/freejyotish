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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;
import javax.swing.border.Border;

import freejyotish.main.*;


public class VimPanel extends JPanel implements ChartCenterEventListener
{
	private Border etch;
	private JScrollPane scroll;
	private JTextArea textArea;
	private JLabel header;
	private Vimshottari vimsh;
	private IChartCenter c_center;

	public VimPanel(IChartCenter center)
	{
		c_center = center;
		c_center.addChartCenterEventListener(this);
		setLayout( new BorderLayout() );
        setBackground( new Color( 153, 153, 0 ) );
        
		etch = BorderFactory.createEtchedBorder();
		setBorder(etch);
		textArea = new JTextArea();
        textArea.setEditable( false );
        textArea.setFont( FJGUIDefs.getInstance().getFixedNormalFont() );
		scroll = new JScrollPane( textArea );
		header = new JLabel("Vimshottari" );
        header.setFont( FJGUIDefs.getInstance().getNormalBoldFont() );
        header.setForeground( Color.WHITE );
		this.add( header, BorderLayout.NORTH );
		this.add( scroll, BorderLayout.CENTER );
        setPreferredSize( new Dimension( 200, 300 ) );
	}
	
	public void loadVimshottari()
	{
		if (c_center.getCurrentChart() != null)
		{
		Chart current = c_center.getCurrentChart();
		Vimshottari vimsh = new Vimshottari(current.getPlanetInfo().getPlanet(FJConstants.planetLongNames[1]).getLongitude(), current.getNativeInfo2().getBirthYear(), current.getNativeInfo2().getBirthMonth(), current.getNativeInfo2().getBirthDate());
		String[] dashaArray = vimsh.getDashaString();
		StringBuffer dashaBuffer = new StringBuffer();
		for (int i = 0; i < dashaArray.length; i++)
		{
            if( dashaArray[ i ].trim().equals( "" ) ) continue;
			dashaBuffer.append(dashaArray[i] + '\n');
            if( (i+1)%9 == 0 ) {
                dashaBuffer.append( "-------------------" + '\n' );
            }
		}
		setVimText(dashaBuffer.toString());
		}
		
		else setVimText("");
		
	}
	

	public void setVimText(String text)
	{
		textArea.setText(text);
	}
	
	public void chartCenterChanged(ChartCenterEvent e)
	{
		loadVimshottari();		

	}
}
@
