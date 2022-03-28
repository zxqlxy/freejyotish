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

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import freejyotish.main.IChartCenter;


public class FJTabbedPanel extends JPanel
{
	private JPanel main;
	
	public FJTabbedPanel(IChartCenter c_center)
	{
		setLayout(new GridLayout(1,1));
		JTabbedPane tabbedPane = new JTabbedPane();
		
		FJNativeDisplay display = new FJNativeDisplay(c_center);
		//VimPanel vim = new VimPanel(c_center);
		BasicTableModel model = new BasicTableModel(c_center);
		BasicTablePanel table_panel = new BasicTablePanel(model);
		FJHouseDataDisplay houseDataDisplay = new FJHouseDataDisplay(c_center);
		AstakavargaTableModel aVModel = new AstakavargaTableModel(c_center);
		AstakavargaTablePanel aVTablePanel = new AstakavargaTablePanel(aVModel);
		FJListPanel list = new FJListPanel(c_center);
		FJCalcPrefsDisplay prefs = new FJCalcPrefsDisplay(c_center);
		JPanel chartPanel = new JPanel();
		chartPanel.setLayout(new GridLayout(0,3));
		chartPanel.add(list);
		chartPanel.add(display);
		chartPanel.add(prefs);
		
		
		
		
		tabbedPane.addTab("Charts", chartPanel);
		tabbedPane.setSelectedIndex(0);
		tabbedPane.addTab("Positions", table_panel);
		//tabbedPane.addTab("Vim.", vim);
		tabbedPane.addTab("Cusps", houseDataDisplay);
		tabbedPane.addTab("AV", aVTablePanel);
		
		add(tabbedPane);

		
	}
	
	
}
@


1.1
log
@Changed the package name from 'FreeJyotish' to 'freejyotish'
@
text
@d3 5
a7 2
import java.awt.*;
import javax.swing.*;
d28 1
a28 1
		
d30 1
a30 1
		chartPanel.setLayout(new GridLayout(0,2));
d33 1
@

