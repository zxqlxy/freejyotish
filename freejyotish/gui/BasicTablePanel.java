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

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.JTableHeader;






public class BasicTablePanel extends JPanel
{
	private JTable basicInfo;
	private JScrollPane scroll;
	private JLabel mainLabel;
	private Border etch;
	private BasicTableModel model;
	private	Object[] infoNames = {(Object) "Planet", (Object) "Longitude", 	(Object) "Dir", (Object) "Nakshatra", (Object) "Latitude", (Object)"Velocity"};


	public BasicTablePanel(BasicTableModel model)
	{
		this.model = model;
		basicInfo = new JTable(model);
        basicInfo.setFont( FJGUIDefs.getInstance().getNormalFont() );
	
        FJCellRenderer cellRend = new FJCellRenderer();
        basicInfo.setDefaultRenderer( basicInfo.getColumnClass( 0 ), cellRend );
        JTableHeader header = basicInfo.getTableHeader();
        header.setFont( FJGUIDefs.getInstance().getNormalFont() );
	//header.setFont( FJGUIDefs.getInstance().getGlyphFont() );
        header.setBackground( new Color( 135, 62, 62 ) );
        header.setForeground( Color.WHITE );
		scroll = new JScrollPane(basicInfo);
		etch = BorderFactory.createEtchedBorder();
		setBorder(etch);
		setLayout(new BorderLayout());
		mainLabel = new JLabel("Positions, etc."
        , SwingConstants.CENTER );
        mainLabel.setFont( FJGUIDefs.getInstance().getNormalBoldFont() );
        setBackground( new Color( 135, 62, 62 ) );
        mainLabel.setForeground( Color.WHITE );
		add(mainLabel, BorderLayout.NORTH);
		add(scroll, BorderLayout.CENTER);
 
	}

	public void setTableModel (BasicTableModel mod)
	{
		basicInfo.setModel(mod);
		
	}





}
@
