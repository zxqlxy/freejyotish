head	1.2;
access;
symbols;
locks; strict;
comment	@# @;


1.2
date	2003.05.22.07.34.07;	author michaeltaft;	state dead;
branches;
next	1.1;

1.1
date	2003.05.17.08.18.57;	author santoshs;	state Exp;
branches;
next	;


desc
@@


1.2
log
@Files no longer required.
@
text
@package freejyotish.main.calc;

public class CalculationInformationRequiredException extends Exception
{
	private static final String MESSAGE = "Calculation cannot be completed.";
	private static final int BIRTH_YEAR_REQUIRED = 1;
	private static final int BIRTH_MONTH_REQUIRED = 2;
	private static final int BIRTH_DATE_REQUIRED = 3;
	private static final int BIRTH_HOUR_REQUIRED = 4;
	private static final int BIRTH_MINUTE_REQUIRED = 5;
	private static final int BIRTH_SECOND_REQUIRED = 6;
	private static final int TIME_ZONE_OFFSET_REQUIRED = 7;
	private static final int DST_OFFSET_REQUIRED = 8;
	private static final int LONGITUDE_DEGREE_REQUIRED = 9;
	private static final int LONGITUDE_MINUTE_REQUIRED = 10;
	private static final int LONGITUDE_SECOND_REQUIRED = 11;
	private static final int LATITUDE_DEGREE_REQUIRED = 12;
	private static final int LATITUDE_MINUTE_REQUIRED = 13;
	private static final int LATITUDE_SECOND_REQUIRED = 14;
	private int calculationInformationRequired;
	
	public CalculationInformationRequiredException(int itemsRequired)
	{
		super(MESSAGE);
		calculationInformationRequired = itemsRequired;
		
	}
	
	public int getInformationRequired()
	{
		return calculationInformationRequired;
	}
}
@


1.1
log
@Changed the package name from 'FreeJyotish' to 'freejyotish'
@
text
@@

