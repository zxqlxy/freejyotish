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
* $Id: FJBaseDialog.java,v 1.2 2003/05/15 03:45:23 michaeltaft Exp $
*
*/
package freejyotish.gui;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

/**
 * Base class for all dialogs. Contains some convinience functions
 * such as to center dialog etc.
 */
public abstract class FJBaseDialog extends JDialog implements ActionListener {

    public FJBaseDialog() {
        this( null, null );
    }
    
    public FJBaseDialog( Frame parent, String title ) {
        super( parent );
        if( title != null ) setTitle( title );
        setModal( true );
        setResizable( false );
        setDefaultCloseOperation( JDialog.DISPOSE_ON_CLOSE );
    }
    
    // Call this method after setting size of the dialog.
    public void centerDialog( Frame parent ) {
        Dimension d = parent.getSize();
        setBounds( (int) (d.getWidth()-getWidth())/2, (int) (d.getHeight()
        -getHeight())/2, getWidth(), getHeight() );
    }
    
}
@
