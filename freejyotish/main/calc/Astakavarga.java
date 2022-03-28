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
@package freejyotish.main.calc;





// Created 3 May, 2003 by Michael W. Taft



public class Astakavarga

{

	private int[][] avData;
	public Astakavarga()
	{
		
	}

	

	public Astakavarga(int[][] av)

	{

		avData = av;

	}

	public void setAstakavarga(int[][] av)

	{

		avData = av;

	}

	

	public int[][] getAstakavarga()

	{

		return avData;

	}

}@
