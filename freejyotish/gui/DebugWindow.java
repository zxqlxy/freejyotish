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

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DebugWindow extends JFrame
{
	private JPanel main, control;
	private JScrollPane scroll;
	private JTextArea text;
	private JButton clear, close;
	
	public DebugWindow()
	{
		setSize(300,500);
		setTitle("FJ JVM Output");
        Font f = FJGUIDefs.getInstance().getNormalFont();
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(createMainPanel( f ), BorderLayout.CENTER);
		getContentPane().add(createControlPanel( f ), BorderLayout.SOUTH);
		
	
	}

	public JPanel createMainPanel( Font f )
	{
		main = new JPanel();
		text = new JTextArea();
        text.setEditable( false );
        text.setFont( f );
		scroll = new JScrollPane(text);
		scroll.setPreferredSize( new Dimension( 300, 450 ) );
		main.add(scroll);
		return main;
	}
	
	public JPanel createControlPanel( Font f )
	{
		control = new JPanel();
		clear = new JButton( "Clear" );
		close = new JButton( "Close" );
        clear.setFont( f );
        close.setFont( f );
		control.add(clear);
		control.add(close);
		return control;
	}

    public void show() {
        try {
            FileReader fin = new FileReader( "fj.log" );
            int ch = 0;
            while( ( ch = fin.read() ) != -1 ) {
                text.setText( text.getText() + ( char ) ch );
            }
        } catch( FileNotFoundException fnfe ) {
            System.out.println( "File fj.log not found" );
        } catch( IOException fnfe ) {
            System.out.println( "File fj.log not found" );
        }
    }
}
@
