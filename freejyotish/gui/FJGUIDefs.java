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
* $Id: FJGUIDefs.java,v 1.7 2003/04/13 22:01:05 michaeltaft Exp $
*
*/

package freejyotish.gui;

import java.awt.Font;
import java.io.*;

public class FJGUIDefs {
    private static FJGUIDefs _instance;
    private Font varFont;
    private Font fixedFont;
    private Font glyph, simpleGlyph;
    
    private FJGUIDefs() {
        varFont = new Font( "Times", Font.PLAIN, 12 );
        fixedFont = new Font( "Courier", Font.PLAIN, 11 );
        InputStream is = null;
        try {
            is = new FileInputStream( "lib/AstroGadget.ttf" );
            glyph = Font.createFont( Font.TRUETYPE_FONT, is );
            glyph = glyph.deriveFont( Font.PLAIN, 15 );
            System.out.println( "Glyph name: " + glyph.getName() );
        } catch( Exception e ) {
            System.out.print( "Cant create glyph" );
            glyph = null;
        } finally {
            if( is != null ) {
                try {
                    is.close();
                } catch( Exception e ) {}
            }
            is = null;
        }
	
	 try {
            is = new FileInputStream( "lib/Astro.ttf" );
            simpleGlyph = Font.createFont( Font.TRUETYPE_FONT, is );
            simpleGlyph = simpleGlyph.deriveFont( Font.PLAIN, 15 );
            System.out.println( "Glyph name: " + simpleGlyph.getName() );
        } catch( Exception e ) {
            System.out.print( "Cant create glyph" );
            glyph = null;
        } finally {
            if( is != null ) {
                try {
                    is.close();
                } catch( Exception e ) {}
            }
            is = null;
        }
    }
    
    public static FJGUIDefs getInstance() {
        if( _instance == null ) {
            _instance = new FJGUIDefs();
        }
        return _instance;
    }
    
    public Font getNormalFont() {
        return varFont;
    }
    
    public Font getSmallNormalFont() {
        return varFont.deriveFont( Font.PLAIN, 9 );
    }

    public Font getBigNormalFont() {
        return varFont.deriveFont( Font.PLAIN, 25 );
    }

    public Font getNormalBoldFont() {
        return varFont.deriveFont( Font.BOLD );
    }
    
    public Font getFixedNormalFont() {
        return fixedFont;
    }
    
    public Font getGlyphFont() {
        return glyph;
    }
    
     public Font getSimpleGlyphFont() {
        return simpleGlyph;
    }
    
    public Font getBigBoldGlyph() {
        return glyph.deriveFont( Font.PLAIN, 30 );
         
    }
}
@
