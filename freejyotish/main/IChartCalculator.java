head	1.2;
access;
symbols;
locks; strict;
comment	@# @;


1.2
date	2003.05.22.18.02.34;	author michaeltaft;	state Exp;
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
@Cleaning up old calculation preferences stuff.
@
text
@package freejyotish.main;


public interface IChartCalculator
{
	public Chart calculateChart(BirthData nativeInfo2);
	public void setCalculationPreferences();
	

}
@


1.1
log
@Changed the package name from 'FreeJyotish' to 'freejyotish'
@
text
@a2 1
import freejyotish.main.calc.CalculationPreferences;
d7 1
a7 1
	public void setCalculationPreferences(CalculationPreferences cp);
@

