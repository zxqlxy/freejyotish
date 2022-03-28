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
date	2003.05.17.08.18.58;	author santoshs;	state Exp;
branches;
next	;


desc
@@


1.2
log
@House preferences work.
@
text
@package freejyotish.gui;

import freejyotish.main.*;
import javax.swing.*;
import java.awt.*;
//import java.text.SimpleDateFormat;
//import java.util.*;


public class FJHouseDataDisplay extends JPanel implements ChartCenterEventListener
{
	private IChartCenter c_center;
	private JPanel header;
	private JLabel titleLabel, houseSystemLabel;
	private JTextArea text;
	
	public FJHouseDataDisplay(IChartCenter center)
	{
		c_center = center;
		c_center.addChartCenterEventListener(this);
		JPanel main = new JPanel();
		setLayout(new BorderLayout());
		setBackground( new Color( 0, 0, 0 ) );
		titleLabel = new JLabel("House System:");
		houseSystemLabel = new JLabel("");
		titleLabel.setFont( FJGUIDefs.getInstance().getNormalBoldFont() );
		houseSystemLabel.setFont( FJGUIDefs.getInstance().getNormalBoldFont() );
		titleLabel.setForeground( Color.white );
		houseSystemLabel.setForeground( Color.white );
		text = new JTextArea();
		text.setEditable( false );
		text.setFont( FJGUIDefs.getInstance().getNormalFont() );
		JScrollPane scroll = new JScrollPane(text);
		header = new JPanel();
		header.add(titleLabel);
		header.add(houseSystemLabel);
		header.setBackground(new Color( 0,0,0));
		//setPreferredSize( new Dimension( 200, 100 ) );
		add(header, BorderLayout.NORTH);
		add(scroll, BorderLayout.CENTER);
		
	}
	
	public void loadText()
	{
		Chart chart = c_center.getCurrentChart();
		if (chart == null) { text.setText("");}
		else 
		{
			HouseData hData = chart.getHouseData();
			text.setText("");
			double[] cusps = hData.getAllCusps();
			for(int i = 1; i < 13; i++)
			{
				text.append("Cusp " + i + " :   " + FJUtility.zodiacDMS(cusps[i]) + "\n");
			}
			houseSystemLabel.setText(getFullHouseSystemString(hData.getHouseSystem()));
		}		
		
		}
		
	public String getFullHouseSystemString(String houseSystem)///THIS WILL HAVE TO BE REMOVED 
	{
		String fullHouseSystemString;
		if (houseSystem.equals("O")) fullHouseSystemString = "Shripati";
		else if (houseSystem.equals("K")) fullHouseSystemString = "Koch";
		else if (houseSystem.equals("P")) fullHouseSystemString = "Placidus";
		else if (houseSystem.equals("O")) fullHouseSystemString = "Shripati";
		else if (houseSystem.equals("B")) fullHouseSystemString = "Alcabitus";
		else if (houseSystem.equals("R")) fullHouseSystemString = "Regiomontanus";
		else if (houseSystem.equals("C")) fullHouseSystemString = "Campanus";
		else fullHouseSystemString = "unknown";
		return fullHouseSystemString;
		
		
	}
	
	public void chartCenterChanged(ChartCenterEvent e)
	{
		loadText();
	}
}
@


1.1
log
@Changed the package name from 'FreeJyotish' to 'freejyotish'
@
text
@d13 2
a14 1
	private JLabel header;
d24 6
a29 3
		header = new JLabel("House Data:");
		header.setFont( FJGUIDefs.getInstance().getNormalBoldFont() );
		header.setForeground( Color.white );
d34 4
d51 1
d57 1
a57 1
			
d62 15
@

