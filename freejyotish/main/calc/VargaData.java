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
@package  freejyotish.main.calc;
/**
 *  Description of the Class
 *
 *@@author     Michael W. Taft
 *@@created    June 5, 2002
 */
public class VargaData
{
	private int[][] vargaData;
	
	public VargaData(int[][] data)
	{
		vargaData = data;
	}
	
	public void setVargaData(int [][] data)
	{
		vargaData = data;
	}
	public int[][] getVargaData()
	{
		return vargaData;
	}

}






@
