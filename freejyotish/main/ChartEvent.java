head	1.1;
access;
symbols;
locks; strict;
comment	@# @;


1.1
date	2003.05.17.08.18.57;	author santoshs;	state Exp;
branches;
next	;


desc
@@


1.1
log
@Changed the package name from 'FreeJyotish' to 'freejyotish'
@
text
@package freejyotish.main;

public class ChartEvent extends java.util.EventObject
{
        
    public ChartEvent(Chart chart)
    {
        super(chart);
    }

   
}
@
