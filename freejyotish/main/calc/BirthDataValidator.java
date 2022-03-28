head	1.3;
access;
symbols;
locks; strict;
comment	@# @;


1.3
date	2003.05.22.18.02.34;	author michaeltaft;	state Exp;
branches;
next	1.2;

1.2
date	2003.05.17.19.11.40;	author michaeltaft;	state Exp;
branches;
next	1.1;

1.1
date	2003.05.17.09.16.03;	author michaeltaft;	state Exp;
branches;
next	;


desc
@@


1.3
log
@Cleaning up old calculation preferences stuff.
@
text
@package freejyotish.main.calc;
import freejyotish.main.BirthData;
/**
 * @@author Michael W. Taft
 * 16 May, 2003
 * 
 */
public class BirthDataValidator {
	///CURRENTLY CHECKS A BIRTH DATA OBJECT FOR BASIC DATA BOUNDARY VIOLATIONS
	public static void checkBirthDataValidity(BirthData inputData)
		throws FJCalculationInputException {
		try {
			checkLongitude_Deg(inputData.getLongitude_Deg());
			checkLatitude_Deg(inputData.getLatitude_Deg());
			checkMinSec(inputData.getLongitude_Min());
			checkMinSec(inputData.getLatitude_Min());
			checkMinSec(inputData.getLongitude_Sec());
			checkMinSec(inputData.getLatitude_Sec());
			checkDSTOffset(inputData.getDSTOffset());
			checkTimeZoneOffset(inputData.getTimeZoneOffset());
			compareTZandLongitude(
				inputData.getTimeZoneOffset(),
				inputData.getLongitude_Deg());
		} catch (FJCalculationInputException fje) {
			System.out.println(fje);
		}
	}
	private static void checkLongitude_Deg(int longitude_Deg)
		throws FJCalculationInputException {
		String errorMsg = "Longitude Degree value out of bounds.";
		if (-180 > longitude_Deg || longitude_Deg > 180)
			throw new FJCalculationInputException(errorMsg);
	}
	private static void checkLatitude_Deg(int latitude_Deg)
		throws FJCalculationInputException {
		String errorMsg = "Latitude Degree value out of bounds.";
		if (-90 > latitude_Deg || latitude_Deg > 90)
			throw new FJCalculationInputException(errorMsg);
	}
	private static void checkMinSec(int minSec)
		throws FJCalculationInputException {
		String errorMsg =
			"Longitude/Latitude minutes or seconds value out of bounds.";
		if (0 > minSec || minSec > 60)
			throw new FJCalculationInputException(errorMsg);
	}
	private static void checkDSTOffset(int dstOffset)
		throws FJCalculationInputException {
		String errorMsg = "Daylight Savings Time value out of bounds.";
		if (0 > dstOffset && dstOffset > 2)
			throw new FJCalculationInputException(errorMsg);
	}
	private static void checkTimeZoneOffset(double tzOffset)
		throws FJCalculationInputException {
		String errorMsg = "Time Zone value out of bounds.";
		if (-13.0 > tzOffset || tzOffset > 13.0)
			throw new FJCalculationInputException(errorMsg);
	}
	public static void compareTZandLongitude(
		double tzOffset,
		int longitude_Deg)
		throws FJCalculationInputException {
		//Sign on value must be + or - for both. They cannot be different signs.
		String errorMsg = "Time Zone and Longitude are mismatched.";
		if (0 <= longitude_Deg && 0.0 <= tzOffset)
			return;
		else if (0 >= longitude_Deg && 0.0 >= tzOffset)
			return;
		else
			throw new FJCalculationInputException(errorMsg);
	}
	public static void main(String[] args) {
	}
}
@


1.2
log
@Working with Exception Handling.
@
text
@a0 4
/*
 * Created on May 17, 2003
 *
 */
a1 1

a2 2


d8 17
a24 21
public class BirthDataValidator
 {

///CURRENTLY CHECKS A BIRTH DATA OBJECT FOR BASIC DATA BOUNDARY VIOLATIONS
	
	
	public static void checkBirthDataValidity(BirthData inputData) throws  FJCalculationInputException
	{
		try
		{
		checkLongitude_Deg(inputData.getLongitude_Deg());
		checkLatitude_Deg(inputData.getLatitude_Deg());
		checkMinSec(inputData.getLongitude_Min());
		checkMinSec(inputData.getLatitude_Min());
		checkMinSec(inputData.getLongitude_Sec());
		checkMinSec(inputData.getLatitude_Sec());
		checkDSTOffset(inputData.getDSTOffset());
		checkTimeZoneOffset(inputData.getTimeZoneOffset());
		compareTZandLongitude(inputData.getTimeZoneOffset(), inputData.getLongitude_Deg());
		} catch (FJCalculationInputException fje)
		{
a26 1
		
d28 2
a29 3
	
	private static  void checkLongitude_Deg(int longitude_Deg)  throws FJCalculationInputException
	{
d31 18
a48 21
		if (-180 > longitude_Deg || longitude_Deg > 180) throw new FJCalculationInputException(errorMsg);
		
		
	}
	
	private static void checkLatitude_Deg(int latitude_Deg)  throws FJCalculationInputException
		{
			String errorMsg = "Latitude Degree value out of bounds.";
			if (-90 > latitude_Deg || latitude_Deg > 90) throw new FJCalculationInputException(errorMsg);
			
		
		}
	private static void checkMinSec(int minSec) throws FJCalculationInputException
	{
		String errorMsg = "Longitude/Latitude minutes or seconds value out of bounds.";
		if (0 > minSec || minSec > 60) throw new FJCalculationInputException(errorMsg);
		
	}
	
	private static void checkDSTOffset(int dstOffset) throws FJCalculationInputException
	{
d50 8
a57 2
		if (0 > dstOffset && dstOffset > 2) throw new FJCalculationInputException(errorMsg);
		
d59 4
a62 9

	private static void checkTimeZoneOffset(double tzOffset) throws FJCalculationInputException
		{
			String errorMsg = "Time Zone value out of bounds.";
			if (-13.0 > tzOffset ||  tzOffset > 13.0) throw new FJCalculationInputException(errorMsg);
			
		}
	public static void compareTZandLongitude(double tzOffset, int longitude_Deg) throws FJCalculationInputException
	{
d65 8
a72 9
		if (0 <= longitude_Deg && 0.0 <= tzOffset) return;
		else if (0 >= longitude_Deg && 0.0 >= tzOffset) return;
		else throw new FJCalculationInputException(errorMsg);
		
		
	}
	
	public static void main(String[] args)
	{
@


1.1
log
@New file.
@
text
@d18 1
d21 1
a21 1
	public static boolean checkBirthDataValidity(BirthData inputData)
d23 16
a38 14
		boolean isValid;
		int sum = 0;
		sum = sum + checkLongitude_Deg(inputData.getLongitude_Deg());
		sum = sum + checkLatitude_Deg(inputData.getLatitude_Deg());
		sum = sum + checkMinSec(inputData.getLongitude_Min());
		sum = sum + checkMinSec(inputData.getLatitude_Min());
		sum = sum + checkMinSec(inputData.getLongitude_Sec());
		sum = sum + checkMinSec(inputData.getLatitude_Sec());
		sum = sum + checkDSTOffset(inputData.getDSTOffset());
		sum = sum + checkTimeZoneOffset(inputData.getTimeZoneOffset());
		sum = sum + compareTZandLongitude(inputData.getLongitude_Deg(), inputData.getTimeZoneOffset());
		if (sum == 0) isValid = true;
		else isValid = false;
		return isValid;
d41 1
a41 1
	private static int checkLongitude_Deg(int longitude_Deg)
d43 3
a45 4
		int x;
		if (-180 <= longitude_Deg && longitude_Deg<= 180) x = 0;
		else x = 1;
		return x;
d49 1
a49 1
	private static int checkLatitude_Deg(int latitude_Deg)
d51 3
a53 4
			int x;
			if (-90 <= latitude_Deg && latitude_Deg<= 90) x = 0;
			else x = 1;
			return x;
d56 1
a56 1
	private static int checkMinSec(int minSec)
d58 2
a59 3
		int x;
		if (0 <= minSec && minSec<= 60) x = 0;
		else x = 1;
a60 1
		return x;
d63 1
a63 1
	private static int checkDSTOffset(int dstOffset)
d65 2
a66 3
		int x;
		if (0 <= dstOffset && dstOffset<= 2) x = 0;
		else x = 1;
a67 1
		return x;
d70 1
a70 1
	private static int checkTimeZoneOffset(double tzOffset)
d72 3
a74 5
			int x;
			if (-13.0 <= tzOffset &&  tzOffset <= 13.0) x = 0;
			else x = 1;
		
			return x;
d76 1
a76 1
	public static int compareTZandLongitude(int longitude_Deg, double tzOffset)
d79 5
a83 5
		int x;
		if (0 <= longitude_Deg && 0.0 <= tzOffset) x = 0;
		else if (0 >= longitude_Deg && 0.0 >= tzOffset) x = 0;
		else x = 1;
		return x;
@

