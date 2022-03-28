head	1.1;
access;
symbols;
locks; strict;
comment	@# @;


1.1
date	2003.05.24.01.31.20;	author michaeltaft;	state Exp;
branches;
next	;


desc
@@


1.1
log
@To keep track of calculation preferences.
@
text
@package freejyotish.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.prefs.Preferences;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import freejyotish.main.ChartCenterEvent;
import freejyotish.main.ChartCenterEventListener;
import freejyotish.main.IChartCenter;


public class FJCalcPrefsDisplay extends JPanel implements ChartCenterEventListener
{
	private IChartCenter c_center;
	private JLabel header;
	private JTextArea text;
	
	public  FJCalcPrefsDisplay(IChartCenter center)
	{
		c_center = center;
		c_center.addChartCenterEventListener(this);
		JPanel main = new JPanel();
		setLayout(new BorderLayout());
        //setBackground( new Color( 135, 65, 135 ) );
	setBackground( new Color( 0, 153, 153 ) );
		header = new JLabel("Calculation Preferences:");
        header.setFont( FJGUIDefs.getInstance().getNormalBoldFont() );
        header.setForeground( Color.white );
		text = new JTextArea();
        text.setEditable( false );
        text.setFont( FJGUIDefs.getInstance().getNormalFont() );
		JScrollPane scroll = new JScrollPane(text);
		setPreferredSize( new Dimension( 200, 100 ) );
		add(header, BorderLayout.NORTH);
		add(scroll, BorderLayout.CENTER);
		
	}
	
	public void loadText()
	{
		try
		{
		text.setText("");
		Preferences prefs = Preferences.userRoot().node("/freejyotish");
		String[] keys = prefs.keys();
		for (int i = 0; i < keys.length; i++)
		{
			text.append(keys[i] + ":  " +prefs.get(keys[i], "")  + "\n");
		}
		}
		
		catch (Exception e)
			{}
	
		
			
	
		
		
		
		}
		
	
	public void chartCenterChanged(ChartCenterEvent e)
	{
		loadText();
	}
}
@
