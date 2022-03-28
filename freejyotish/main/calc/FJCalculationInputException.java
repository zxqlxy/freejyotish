head	1.1;
access;
symbols;
locks; strict;
comment	@# @;


1.1
date	2003.05.17.19.11.40;	author michaeltaft;	state Exp;
branches;
next	;


desc
@@


1.1
log
@Working with Exception Handling.
@
text
@/*
 * Created on May 17, 2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package freejyotish.main.calc;

/**
 * @@author mwt
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class FJCalculationInputException extends Exception
{
	public FJCalculationInputException(){}
	public FJCalculationInputException(String errorMsg)
	{
		super(errorMsg);
	}
}
@
