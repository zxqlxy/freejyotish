head	1.2;
access;
symbols;
locks; strict;
comment	@# @;


1.2
date	2003.05.18.11.59.54;	author santoshs;	state Exp;
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
@Basic I18N support for FJ in the calc prefs dialog.
@
text
@package freejyotish.gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.*;
import javax.swing.border.Border;

public class AstakavargaTablePanel extends JPanel
{
	private JTable avInfo;
	private JScrollPane scroll;
	private JLabel mainLabel;
	private Border etch;
	private AstakavargaTableModel model;
	private Object[] infoNames = { "Ari", "Tau", "Gem", "Can", "Leo", "Vir", "Lib", "Sco", "Sag", "Cap", "Aqu", "Pis" };

	public AstakavargaTablePanel(AstakavargaTableModel model)
	{
		this.model = model;
		avInfo = new JTable(model);

		scroll = new JScrollPane(avInfo);
		etch = BorderFactory.createEtchedBorder();
		setBorder(etch);
		setLayout(new BorderLayout());
		mainLabel = new JLabel("Astakavarga Summary", SwingConstants.CENTER);
		//mainLabel.setFont( FJGUIDefs.getInstance().getNormalBoldFont() );
		setBackground(new Color(0, 0, 0));
		mainLabel.setForeground(Color.WHITE);
		add(mainLabel, BorderLayout.NORTH);
		add(scroll, BorderLayout.CENTER);

	}

	public void setTableModel(AstakavargaTableModel mod)
	{
		avInfo.setModel(mod);

	}

}
@


1.1
log
@Changed the package name from 'FreeJyotish' to 'freejyotish'
@
text
@a8 5





d16 1
a16 2
	private	Object[] infoNames = {(Object) "Ari", (Object) "Tau", 	(Object) "Gem", (Object) "Can", (Object) "Leo", (Object)"Vir", (Object) "Lib", (Object) "Sco", 	(Object) "Sag", (Object) "Cap", (Object) "Aqu", (Object)"Pis"};

d22 1
a22 1
       
d27 4
a30 5
		mainLabel = new JLabel("Astakavarga Summary"
        , SwingConstants.CENTER );
        //mainLabel.setFont( FJGUIDefs.getInstance().getNormalBoldFont() );
        setBackground( new Color( 0, 0, 0 ) );
        mainLabel.setForeground( Color.WHITE );
d33 1
a33 1
 
d36 1
a36 1
	public void setTableModel (AstakavargaTableModel mod)
d39 1
a39 1
		
a40 4




@

