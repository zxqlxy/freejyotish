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
* $Id: FJSplash.java,v 1.2 2003/03/23 23:40:11 michaeltaft Exp $
*
*/
package freejyotish.gui;

import java.awt.*;
import javax.swing.*;

import freejyotish.main.*;

public class FJSplash extends JWindow {
    public FJSplash() {
        super();
        setBackground( Color.white );
        int w = 200;
        int h = 300;
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds( (int)(d.getWidth()-w)/2, (int)(d.getHeight()-h)/2, w, h );
        JLabel jl = new JLabel( "FreeJyotish", SwingConstants.CENTER );
        jl.setFont( FJGUIDefs.getInstance().getBigNormalFont() );
        getContentPane().add( jl, BorderLayout.NORTH );
        getContentPane().add( addBox(), BorderLayout.CENTER );
        botLabel = new JLabel( "", SwingConstants.CENTER );
        botLabel.setBackground( Color.white );
        botLabel.setFont( FJGUIDefs.getInstance().getSmallNormalFont() );
        botLabel.setForeground( Color.red );
        getContentPane().add( botLabel, BorderLayout.SOUTH );
    }
    
    public void setMessage( String s ) {
        botLabel.setText( s );
    }
    
    private JPanel addBox() {
        JPanel jp = new JPanel();
        jp.setBackground( new Color( 255, 255, 200 ) );
        jp.setLayout( new GridLayout( 3, 3 ) );
        JLabel jg = null;
        Font g = FJGUIDefs.getInstance().getBigBoldGlyph();
        
        for( int i=0; i<13; i++ ) {
		if(i==7||i==8||i==9||i==10)continue;
            jg = new JLabel( "", SwingConstants.CENTER );
            jg.setBorder( BorderFactory.createEtchedBorder() );
            jg.setForeground( Color.blue );
            jg.setFont( g );
            jg.setText( FJConstants.planetGlyphs[ i ] );
            jp.add( jg );
        }
        jp.setBorder( BorderFactory.createEtchedBorder() );
        return jp;
    }
    private JLabel botLabel;
}
@
