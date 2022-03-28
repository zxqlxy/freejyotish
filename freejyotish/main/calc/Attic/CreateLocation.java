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
import freejyotish.main.BirthData;

/*
*	First created 5 April, 2003 by Michael W. Taft
*/


public class CreateLocation
{
	private double longitude, latitude;
	private BirthData nativeInfo2;
	
	public CreateLocation (BirthData ni)
	{
		nativeInfo2 = ni;
		longitude = getDecimalLongitude();
		latitude = getDecimalLatitude();
	
	}
	
	private double getDecimalLongitude()
		{
			double longitude = (double)Math.abs(nativeInfo2.getLongitude_Deg()) + ((double)nativeInfo2.getLongitude_Min())/60 + ((double)nativeInfo2.getLongitude_Sec())/3600;
			if(nativeInfo2.getLongitude_Deg()<0)longitude = -1*longitude;
			//System.out.println("Longitude = " + longitude);///TEST CODE
			return longitude;
		}
	
	private double getDecimalLatitude()
		{
			double latitude = (double)Math.abs(nativeInfo2.getLatitude_Deg()) + ((double)nativeInfo2.getLatitude_Min())/60 + ((double)nativeInfo2.getLatitude_Sec())/3600;
			if(nativeInfo2.getLatitude_Deg()<0)latitude = -1*latitude;
			//System.out.println("Latitude = " + latitude);///TEST CODE
			return latitude;
		}

	public double getLongitude()
	{ return longitude; }
	
	public double getLatitude()
	{ return latitude; }

}
@


1.1
log
@Changed the package name from 'FreeJyotish' to 'freejyotish'
@
text
@@

