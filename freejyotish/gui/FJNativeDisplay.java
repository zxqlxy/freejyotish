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

import freejyotish.main.*;
import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.*;


public class FJNativeDisplay extends JPanel implements ChartCenterEventListener
{
	private IChartCenter c_center;
	private JLabel header;
	private JTextArea text;
	
	public FJNativeDisplay(IChartCenter center)
	{
		c_center = center;
		c_center.addChartCenterEventListener(this);
		JPanel main = new JPanel();
		setLayout(new BorderLayout());
        //setBackground( new Color( 135, 65, 135 ) );
	setBackground( new Color( 0, 153, 153 ) );
		header = new JLabel("Current Chart:");
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
		Chart chart = c_center.getCurrentChart();
		if (chart == null) { text.setText("");}
		else 
		{
		BirthData ni = chart.getNativeInfo2();
		String birthPlace = ni.getBirthCity() + "," + ni.getBirthState() + "," + ni.getBirthCountry();
		Calendar formatCal = new GregorianCalendar(ni.getBirthYear(), ni.getBirthMonth()-1, ni.getBirthDate(), ni.getBirthHour(), ni.getBirthMinute(), ni.getBirthSecond());
		SimpleDateFormat formatter = new SimpleDateFormat( "EEE, d MMM yyyy HH:mm:ss ");
		String formatDate = null;
		try {
            formatDate = formatter.format( formatCal.getTime() );
		} catch( Exception pe ) {
		    System.out.println( "ParseException: " + pe );
		}
		String zoneInfo = "Time Zone: " + ni.getTimeZoneOffset() + "  " + "DST: " + ni.getDSTOffset();
		
		String lonInfo = "Lon: "+ ni.getLongitude_Deg() + "° " + ni.getLongitude_Min() + "\' " + ni.getLongitude_Sec() + "\"";
		String latInfo = "Lat: "+ ni.getLatitude_Deg() + "° " + ni.getLatitude_Min() + "\' " + ni.getLatitude_Sec() + "\"";
		
		String asc = "Asc: " + FJUtility.zodiacDMS(chart.getAscendant());
		String mc = "Mc: " + FJUtility.zodiacDMS(chart.getHouseData().getMidheaven());
		
		String ayan = "Ayanamsa: " +  FJUtility.formatAyanamsa(chart.getPanchangBasics().getAyanamsa());
	
		
			text.setText(ni.toString() + "\n");
			text.append(birthPlace + "\n");
			text.append(formatDate + "\n");
			text.append(zoneInfo + "\n");
			text.append(lonInfo + "\n");
			text.append(latInfo + "\n");
			text.append(asc + "\n");
			text.append(mc + "\n");
			text.append(ayan + "\n");
		}
		
		
		
		}
		
	
	public void chartCenterChanged(ChartCenterEvent e)
	{
		loadText();
	}
}
@
