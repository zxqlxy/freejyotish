head	1.3;
access;
symbols;
locks; strict;
comment	@# @;


1.3
date	2003.05.23.07.55.32;	author michaeltaft;	state Exp;
branches;
next	1.2;

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


1.3
log
@House preferences work.
@
text
@package freejyotish.main.calc;
import swisseph.SwissEph;
import freejyotish.main.FJUtility;
import freejyotish.main.HouseData;

public class HouseDataCalculator {
	public static HouseData calculateHouseData(
		SwissEph sw,
		double tjd_ut,
		double lon,
		double lat,
		int iflag,
		char houseSystem) {
		double[] cusp = new double[13];
		double[] ascmc = new double[10];
		
		System.out.println("House System = " + houseSystem);//////////TESTING ONLY
		
//		the actual calculation
		sw.swe_houses(tjd_ut, iflag, lat, lon, houseSystem, cusp, ascmc);
		
		System.out.println("Ascendant = " + FJUtility.zodiacDMS(ascmc[0]));
		HouseData houseData = new HouseData(ascmc, cusp);
		houseData.setHouseSystem(new Character(houseSystem).toString());
		return houseData;
	}
}
@


1.2
log
@Cleaning up old calculation preferences stuff.
@
text
@d16 4
d21 1
a21 1
		//the actual calculation
d24 1
@


1.1
log
@Changed the package name from 'FreeJyotish' to 'freejyotish'
@
text
@d2 3
d6 8
a13 8
import freejyotish.main.*;
import swisseph.*;

public class HouseDataCalculator
{

public static HouseData calculateHouseData(SwissEph sw, double tjd_ut, double lon, double lat, int iflag, char houseSystem)
	{
d16 2
a17 4
		///eventually these arrays should probably be cloned...
		
		
		sw.swe_houses(tjd_ut, iflag, lat, lon, houseSystem, cusp, ascmc);//the actual calculation
a18 1
		//System.out.println("Midheaven = " + ascmc[1]);
a19 7
		
		/*System.out.println("Calculated HouseData:");
		for (int i=0; i<cusp.length; i++)
		{
			System.out.println("Cusp Number " + i + " is at: " + FJUtility.zodiacDMS(cusp[i]));	
		}*/
		
@

