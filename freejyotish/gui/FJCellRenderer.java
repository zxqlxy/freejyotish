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
* $Id: FJCellRenderer.java,v 1.1 2003/03/30 07:36:45 umashalkar Exp $
*
*/
package freejyotish.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import freejyotish.main.FJConstants;

public class FJCellRenderer extends DefaultTableCellRenderer {
    public FJCellRenderer() {
        super();
        glyphFont = FJGUIDefs.getInstance().getGlyphFont();
        textFont = FJGUIDefs.getInstance().getNormalFont();
    }
    public Component getTableCellRendererComponent( JTable table, Object value
    , boolean isSelected, boolean hasFocus, int row, int column ) {
        Component cp;
        if( column == 0 ) {
            JPanel jp = new JPanel();
            JLabel glyphLabel;
            JLabel textLabel;
            jp.setLayout( new FlowLayout( FlowLayout.LEFT, 5, 0 ) );
            jp.setAlignmentX( 0 );
            jp.setAlignmentY( 0 );
            jp.setBackground( Color.white );
            glyphLabel = new JLabel();
            glyphLabel.setFont( glyphFont );
            glyphLabel.setText( FJConstants.planetGlyphs[ row ] );
            glyphLabel.setForeground( Color.blue );
            textLabel = new JLabel();
            textLabel.setText( ( String ) value );
            textLabel.setFont( textFont );
            jp.add( glyphLabel );
            jp.add( textLabel );
            cp = jp;
        } else {
            cp = super.getTableCellRendererComponent( table, value, isSelected
            , hasFocus, row, column );
        }
        return cp;
    }
    private Font glyphFont;
    private Font textFont;
}
@
